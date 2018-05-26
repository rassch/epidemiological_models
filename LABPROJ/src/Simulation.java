import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Simulation 
{
	//ZOBACZ TYLKO KOMENTARZE W FUNKCJI RUN
	public Human human = new Human(100, 100);
	private int counter = 0;
	public int iterationCounter = 0;
	public int simulationIterations = 200;
	private int simulationSteps = 400;
	
	LinkedList<Integer> healthy = new LinkedList<Integer>();
	LinkedList<Integer> sick = new LinkedList<Integer>();
	LinkedList<Integer> immune = new LinkedList<Integer>();
	LinkedList<Integer> periods = new LinkedList<Integer>();
	
	LinkedList<Integer> healthyMean = new LinkedList<Integer>();
	//LinkedList<Integer> healthyMeanTmp = new LinkedList<Integer>();
	LinkedList<Integer> sickMean = new LinkedList<Integer>();
	//LinkedList<Integer> sickMeanTmp = new LinkedList<Integer>();
	LinkedList<Integer> immuneMean = new LinkedList<Integer>();
	//LinkedList<Integer> immuneMeanTmp = new LinkedList<Integer>();
	LinkedList<Integer> periodsMean = new LinkedList<Integer>();
	boolean sir = false;
	int healthyNum;
	int sickNum;
	int immuneNum ;
	int periodsNum = 0;
	int states[] = new int[40000];
	int tmpStates[] = new int[40000];
	//int initStates[];
	
	Double zdrowe = 0.;
	Double chore = 0.5;
	
	public void  mean_values_export (LinkedList<Integer> tab, String filename)
	{
		
		String name = filename + ".txt";
		ListIterator<Integer> litr = null;
		litr=tab.listIterator();
		PrintWriter writer;
		try {
			writer = new PrintWriter(name, "UTF-8");
			while(litr.hasNext()){
				//System.out.println(litr.next());
				writer.println(litr.next());
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	void addToMean()
	{
		if(counter==0)
			;
		else if(counter==1)
			{	
				
				healthyMean.addAll(healthy);
				sickMean.addAll(sick);
				immuneMean.addAll(immune);	
				/*healthyMean.add(healthy.get(i)+healthyMeanTmp.get(i));
				sickMean.add(sick.get(i)+sickMeanTmp.get(i));	
				immuneMean.add(immune.get(i)+immuneMeanTmp.get(i));	
				*/
			}
		else
			{
				for(int i = 0;i<healthyMean.size();i++)
				{	
									
					healthyMean.set(i, healthyMean.get(i)+healthy.get(i));
					sickMean.set(i, sickMean.get(i)+sick.get(i));
					if(sir==true)
					{
						immuneMean.set(i, immuneMean.get(i)+immune.get(i));
					}
				}	
			}	
		
		//healthyMeanTmp.clear();
		//sickMeanTmp.clear();
		//immuneMeanTmp.clear();
	}
	LinkedList<Integer> getHealthyMean()
	{
		for(int i=0;i<healthyMean.size();i++)
		{
			healthyMean.set(i, (int)Math.ceil(healthyMean.get(i)/simulationIterations));
		}
		return healthyMean;
	}
	LinkedList<Integer> getSickMean()
	{
		for(int i=0;i<sickMean.size();i++)
		{
			sickMean.set(i, (int)Math.ceil(sickMean.get(i)/simulationIterations));
		}
		return sickMean;
	}
	LinkedList<Integer> getImmuneMean()
	{
		for(int i=0;i<immuneMean.size();i++)
		{
			immuneMean.set(i, (int)Math.ceil(immuneMean.get(i)/simulationIterations));
		}
		return immuneMean;
	}
	void clear()
	{
		healthyMean.clear();
		sickMean.clear();
		immuneMean.clear();
	}
	void init()//funkcja inicjalizujaca mape
	{
		//addToMean();
		counter++;
		periodsNum = 0;
		sickNum = human.getNumOfPatientsX();
		healthyNum = (human.pixels.length - human.getNumOfPatientsX());
		immuneNum = 0;
		
		sick.clear();
		healthy.clear();
		immune.clear();
		periods.clear();
		
		human.initGrid();//inicjalizujemy ZIELONA mape
		human.init();//funkcja generujaca poczatkowa liczbe zarazonych losowo rozlozonych po mapie
		this.states= human.initStates();
		periodsNum++;
		periods.add(periodsNum);
		sick.add(sickNum);
		healthy.add(healthyNum);
		immune.add(immuneNum);
		System.out.println(healthyMean);
		//System.out.println(healthyMeanTmp);
	}
	void update()
	{
		if(sir == false)
			tmpStates = human.newStates();
		else
			tmpStates = human.newStatesSIR();
		
		sickNum =0;
		healthyNum = 0;
		immuneNum = 0;
		for(int i=0;i<10000;i++)
		{
			states[i] = tmpStates[i];
			if(states[i]==0)
				healthyNum++;
			else if(states[i]==1)
				sickNum++;
			else
				immuneNum++;
		}
		healthy.add(healthyNum);
		sick.add(sickNum);
		if(sir == true)
		{
			immune.add(immuneNum);
		}
	}
	void setModelType(boolean SIS)
	{
		if(SIS==true)
			sir = false;
		else
			sir = true;
	}
	void run()
	{
		
		setModelType(false);//false = SIR , true = SIS
		human.setNumOfPatientsX(10);//liczba poczatkowych chorych
		//To ponizej mozna wstawic w jeszcze jedna petle i sobie w niej zmieniac transrate i healty rate
		//zrob jak chcesz, albo mnostwo plikow kazdy dla innego parametru, albo dopisuj do juz istniejacych
		//tylko pamietaj, ze jak chesz duzo plikow to w petli musi im sie nazwa rowniez zmieniac automatycznie
		//narazie petle zostawiam zakomentowana, przetestuj sobie jak dziala
		//for(double z =0.01;z<1;z+=0.01)
		//{	
			human.setgetHealthyRate(zdrowe);//wspolczynik zdrowienia w sisie/uodparniania sie w sirze
			human.settras_rate(chore);//wspolczynnik zarazania
			for(int j=0;j<simulationIterations;j++)//liczba symulacji dla jednego ukladu parametrow
			{
				init();
				for(int i=0;i<simulationSteps;i++)//liczba krokow na jedna symulacje
				{	
					update();
					System.out.println(i);//takie info ze chodzi
				}
				System.out.println(j);//j/w
				addToMean();
				counter++;
			}
			mean_values_export(getHealthyMean(), "zdrowi_SIR_ch_" + chore.toString() + "_zd_" + zdrowe.toString());//tworzymy plik dla kazdego ukladu parametrow
			mean_values_export(getSickMean(), "chorzy");
			mean_values_export(getImmuneMean(), "odporni");
			counter = 0;
			clear();
			System.out.println("koniec symulacji dla danych parametrow zdrowenia/zarazania");
		//}
	}
	public Simulation()
	{
		run();
	}
}
