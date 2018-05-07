import java.util.LinkedList;

import org.xml.sax.ext.LexicalHandler;



public class States
{
	public Human human = new Human(100, 100);//konstrkutory dla poszczegolnych typow modeli i oswiezan
	
	private int[] pixels = new int[human.getHeight() * human.getWidth()];//piksele ktore trafia do AnimationPanel i zostana narysowane
	private int[] states = new int[human.getHeight() * human.getWidth()];//stany na ktorych model operuje i updatuje
	private int[] tmp = new int[human.getHeight() * human.getWidth()];
	private boolean sis=true,synchronous=true,sir= false;//wybor modelu i rodzaju odswiezania
	private int counter = 0;
	LinkedList<Integer> healthy = new LinkedList<Integer>();
	LinkedList<Integer> sick = new LinkedList<Integer>();
	LinkedList<Integer> immune = new LinkedList<Integer>();
	LinkedList<Integer> periods = new LinkedList<Integer>();
	
	LinkedList<Integer> healthyMean = new LinkedList<Integer>();
	LinkedList<Integer> healthyMeanTmp = new LinkedList<Integer>();
	LinkedList<Integer> sickMean = new LinkedList<Integer>();
	LinkedList<Integer> sickMeanTmp = new LinkedList<Integer>();
	LinkedList<Integer> immuneMean = new LinkedList<Integer>();
	LinkedList<Integer> immuneMeanTmp = new LinkedList<Integer>();
	LinkedList<Integer> periodsMean = new LinkedList<Integer>();
	
	int healthyNum;
	int sickNum;
	int immuneNum ;
	int periodsNum = 0;
	
	
	void resetCounter()
	{
		this.counter = 0;
	}
	void addToMean()
	{
		if(counter==0)
			;
		else
		for(int i = 0;i<healthy.size();i++)
		{
			if(counter==1)
			{	
				healthyMean.add(healthy.get(i));
				sickMean.add(sick.get(i));
				immuneMean.add(immune.get(i));	
				/*healthyMean.add(healthy.get(i)+healthyMeanTmp.get(i));
				sickMean.add(sick.get(i)+sickMeanTmp.get(i));	
				immuneMean.add(immune.get(i)+immuneMeanTmp.get(i));	
				*/
			}
			else
				healthyMeanTmp.add(healthy.get(i));
				sickMeanTmp.add(sick.get(i));
				immuneMeanTmp.add(immune.get(i));
				
				healthyMean.set(i, healthyMean.get(i)+healthyMeanTmp.get(i));
				sickMean.set(i, sickMean.get(i)+sickMeanTmp.get(i));
				immuneMean.set(i, immuneMean.get(i)+immuneMeanTmp.get(i));
				
		}
		healthyMeanTmp.clear();
		sickMeanTmp.clear();
		immuneMeanTmp.clear();
	}
	LinkedList<Integer> getHealthyMean()
	{
		for(int i=0;i<healthyMean.size();i++)
		{
			healthyMean.set(i, healthyMean.get(i)/10);
		}
		return healthyMean;
	}
	LinkedList<Integer> getSickMean()
	{
		for(int i=0;i<sickMean.size();i++)
		{
			sickMean.set(i, sickMean.get(i)/10);
		}
		return sickMean;
	}
	LinkedList<Integer> getImmuneMean()
	{
		for(int i=0;i<immuneMean.size();i++)
		{
			immuneMean.set(i, immuneMean.get(i)/10);
		}
		return immuneMean;
	}
	void clear()
	{
		healthyMean.clear();
		sickMean.clear();
		immuneMean.clear();
	}
	private void update()//wybor modelu i odswiezania
	{

		
		if(sis && synchronous )
		{
			
			tmp = human.newStates();//wziecie z humana nowych stanow i przepisanie ich do tmp
			
			for(int i = 0;i<pixels.length;i++)
			{
				this.states[i] = tmp[i]; // przepisanie z tmp do stanow tutaj, ktore beda przepisane w funkcji statesToPixels na piksele
			}
		}else if(sir && synchronous)
		{
			tmp = human.newStatesSIR();//wziecie z humana nowych stanow i przepisanie ich do tmp
			
			for(int i = 0;i<pixels.length;i++)
			{
				this.states[i] = tmp[i]; // przepisanie z tmp do stanow tutaj, ktore beda przepisane w funkcji statesToPixels na piksele
			}
		}else 
		{
		
		}	
		//System.out.println(periodsNum);
	}
	public int[] statesToPixels()//funckja konwertujaca stany na kolory ktore zostana przypisane pikselom i zwracajaca je do AnimationPanel
	{
		
		healthyNum = 0;
		sickNum = 0;
		immuneNum = 0;
		update();
		periodsNum++;
		periods.add(periodsNum);
		//Random random = new Random();//testowanie czy jest zmienny output
		for(int i=0;i<pixels.length;i++)
		{
			if(states[i] == 0)//Czyli jak stan zdrowy
			{
				healthyNum++;
				pixels[i] = 65280 ;//to bedzie mial zielony kolor
			}
			else if (states[i] == -1)//czyli immune
			{
				immuneNum++;
				pixels[i] = 0x4286F4;//wtedy kolor blu
			}
			else//czyli stan chory
			{
				sickNum++;
				pixels[i] = 16711680;//wtedy kolor czerwony
			}
		
		}
		sick.add(sickNum);
		healthy.add(healthyNum);
		if(sir == true)
		{
			immune.add(immuneNum);
		}
		
		//System.out.println(sick);
		return this.pixels;
	}
	public int[] init()//funkcja inicjalizujaca mape
	{
		addToMean();
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
		this.pixels= human.pixels;
		periodsNum++;
		periods.add(periodsNum);
		sick.add(sickNum);
		healthy.add(healthyNum);
		immune.add(immuneNum);
		return this.pixels;
	}
	public States()
	{
		
	}
	void setSIRtrue()
	{
		sir = true;
		sis =false;
		synchronous = true;
	}
	void setSISTrue()
	{
		sir = false;
		sis = true;
		synchronous = true;
	}
}
	


