import java.util.Random;

public class Human  {
	
	
	//private int sickDaysPeriod = 3;//czas trwania choroby
	//private double death_rate = 0;
	private double tras_rate = 0.3; //parametr zarazania
	private double getHealthyRate = 0.4;//parametr zdrowienia
	private int width=100, height=100; // rozmiary siatki
	private int numOfPatientsX = 5;//poczatkowa liczba chorych
	public int[] pixels;//zawiera dane do pikseli
	private int[] states = new int[width*height];
	private int[] sickDays = new int[width*height];
	private int[] initStates = new int[width*height] ;
	private Random random = new Random();
	private boolean[] immunity = new boolean[width*height];
	
	
	
	public Human(int width, int height)
	{
		this.width=width;
		this.height=height;
		pixels = new int[width * height];
	}
	
	public int[] initStates()
	{
		return this.initStates;
	}
	public void sickDays()// STAN 0 UZNAJEMY ZA SUSCEPTIBLE 1 ZA INFECTIOUS
	{		
		for(int i=0 ; i<pixels.length;i++)
		{
			if(states[i] != 0)//jezeli chory, czyli stan pikesla jest 1 to zliczamy liczbe dni jego choroby i przy kazdej iteracji zwieszkam tutaj o jeden 
			{
				sickDays[i]+=1;//sickDays trzymamy tutaj bo ta tablica zapamietuje ilosc dni choroby, a nie stany chory/zdrowy
			}
			else
			{
				this.sickDays[i] = 0;//jak nie byl chory to liczba dni jego choroby wynosi 0
			}
			
			//System.out.println(sickDays[i]);
		}
	
	}
	
	public int[] newStates()//funkcja zwracajaca nowe stany 
	{
		sickDays();
		int[] tmp = new int[width * height];
		for(int i=0;i<pixels.length;i++)
		{
			tmp[i] = sickDays[i];
		}
		for(int i=0;i<pixels.length;i++)
		{
			
			if(sickDays[i] != 0)//jezeli nasz piksel jest chory to idziemy dalej, bo nie nie da sie go ponownie zarazic, a nasz model nie zaklada resetowania sie dni trwania choroby w jej trakcie
			{
				//System.out.println("omijam chory piksel"); 
				states[i] = 1;
			}
			else//czyli jezeli ten i-ty piksel jest zdrowy to probojemy go zarazic
			{
				
				
				if(((i-1 >= 0 && (i-1) % width != width-1) && tmp[i-1] != 0) ||  (i % width == 0 && tmp[i+width-1] != 0) ) //jezeli piksel obok istnieje, jest w tym samym rzedzie i jest chory to rzucamy kostka, czy nasz zdrowy i-ty piksel sie od niego zarazi 
				//lub jezeli piksel jest na lewym brzegu, a piksel w tym samym rzedzie z prawego brzegu jest chory to mozemy od niego zarazic
				{
					if(random.nextFloat()<= tras_rate)// jak liczba wypadnie w przedziale 0-trans_rate to piksel zotaje zarazony
					{
						states[i] = 1;//zarazanie piksela
						sickDays[i] = 1;//pierwszy dzien choroby
					}
				}
			    if(((i+1 < pixels.length && (i+1) % width != 0) && tmp[i+1] != 0) || ((i % width == width -1) && tmp[i-width +1] != 0))//sprawdzamy kolejnego sasiada, tym razem z prawej strony
				//lub jezeli piksel jest na prawym brzegu, a piksel z lewegu brzegu z tego samego rzedu jest zarazony to mozna od niego zarazic
			    {
			    	if(random.nextFloat()<= tras_rate && sickDays[i] == 0)//sprawdzamy dodatkowo czy poprzedni sasiad juz nie zdazyl zarazic naszego piksela
					{
						
						states[i] = 1;
						sickDays[i] = 1;
					}
				}
			    if((i-width >= 0 && tmp[i-width] != 0) || ((i < width) && tmp[(width*height)-width +i] != 0))//gorny sasiad + warunek perdodycznosci
			    {
			    	
			    	if(random.nextFloat()<= tras_rate && sickDays[i] == 0)
			    	{
			    		states[i] = 1;
						sickDays[i] = 1;
					}
			    }
			    if((i+width < pixels.length && tmp[i+width] != 0) || ((i>=(width * height - width)) && (tmp[i-(width * height -height)] != 0)))//dolny sasiad
			    {
			    	
			    	if(random.nextFloat()<= tras_rate && sickDays[i] == 0)
			    	{
			    		states[i] = 1;
						sickDays[i] = 1;
					}
			    }
			}
			//System.out.println(states[i]);
		}		
		for(int i=0;i<pixels.length;i++)
		{
			if(sickDays[i]>0 && random.nextFloat() < getHealthyRate)//sprawdzamy czy piksel byl juz chory, jezeli tak to ma szanse wyzdrowiec jak wyzdrowiec 
			{
				sickDays[i]=0;//jezeli tak to zerujemy jego liczbe dni choroby
				states[i]=0;// i uznajemy go za zdrowego z tej turze do wyswietlenia
			}
		}
		return this.states;// gotowe states, ktore bedziemy przeliczac na piksele w klasie States
		
	}
	public int[] newStatesSIR()//funkcja zwracajaca nowe stany 
	{
		sickDays();
		int[] tmp = new int[width * height];
		for(int i=0;i<pixels.length;i++)
		{
			tmp[i] = sickDays[i];
		}
		for(int i=0;i<pixels.length;i++)
		{
			
			if(tmp[i] == 1)//jezeli nasz piksel jest chory to idziemy dalej, bo nie nie da sie go ponownie zarazic, a nasz model nie zaklada resetowania sie dni trwania choroby w jej trakcie
			{
				//System.out.println("omijam chory piksel"); 
				states[i] = 1;
			}
			else if(immunity[i])
			{
				states[i] = -1; //odporny
				sickDays[i] =-69;
			}
			else if (tmp[i] == 0)//czyli jezeli ten i-ty piksel jest zdrowy to probojemy go zarazic
			{
				
				
				if(((i-1 >= 0 && (i-1) % width != width-1) && tmp[i-1] > 0) ||  (i % width == 0 && tmp[i+width-1] > 0) ) //jezeli piksel obok istnieje, jest w tym samym rzedzie i jest chory to rzucamy kostka, czy nasz zdrowy i-ty piksel sie od niego zarazi 
				//lub jezeli piksel jest na lewym brzegu, a piksel w tym samym rzedzie z prawego brzegu jest chory to mozemy od niego zarazic
				{
					if(random.nextFloat()<= tras_rate)// jak liczba wypadnie w przedziale 0-trans_rate to piksel zotaje zarazony
					{
						states[i] = 1;//zarazanie piksela
						sickDays[i] = 1;//pierwszy dzien choroby
					}
				}
			    if(((i+1 < pixels.length && (i+1) % width != 0) && tmp[i+1] > 0) || ((i % width == width -1) && tmp[i-width +1] > 0))//sprawdzamy kolejnego sasiada, tym razem z prawej strony
				//lub jezeli piksel jest na prawym brzegu, a piksel z lewegu brzegu z tego samego rzedu jest zarazony to mozna od niego zarazic
			    {
			    	if(random.nextFloat()<= tras_rate && sickDays[i] == 0)//sprawdzamy dodatkowo czy poprzedni sasiad juz nie zdazyl zarazic naszego piksela
					{
						
						states[i] = 1;
						sickDays[i] = 1;
					}
				}
			    if((i-width >= 0 && tmp[i-width] > 0) || ((i < width) && tmp[(width*height)-width +i] > 0))//gorny sasiad + warunek perdodycznosci
			    {
			    	
			    	if(random.nextFloat()<= tras_rate && sickDays[i] == 0)
			    	{
			    		states[i] = 1;
						sickDays[i] = 1;
					}
			    }
			    if((i+width < pixels.length && tmp[i+width] > 0) || ((i>=(width * height - width)) && (tmp[i-(width * height -height)] > 0)))//dolny sasiad
			    {
			    	
			    	if(random.nextFloat()<= tras_rate && sickDays[i] == 0)
			    	{
			    		states[i] = 1;
						sickDays[i] = 1;
					}
			    }
			}
			//System.out.println(states[i]);
		}		
		for(int i=0;i<pixels.length;i++)
		{
			if(sickDays[i]>0 && random.nextFloat() < getHealthyRate)//sprawdzamy czy piksel byl juz chory, jezeli tak to ma szanse siê uodporniæ 
			{
				//states[i] = -1;
				immunity[i] = true;
				sickDays[i] =-69;
			}
		}
		return this.states;// gotowe states, ktore bedziemy przeliczac na piksele w klasie States
		
	}
	public void init()//inicjalizowanie poczatkowych chorych
	{
		for(int i =0;i<numOfPatientsX;i++)
		{
			pixels[random.nextInt(pixels.length)] = 0xFF0000;	
		}
		for(int i=0;i<pixels.length;i++)
		{
			
			
			if(pixels[i] == 65280)//Czyli jak stan zdrowy
			{
				initStates[i] = 0 ;//to bedzie mial zielony kolor
			}
			else//czyli stan chory
			{
				initStates[i] = 1;//wtedy kolor czerwony
			}
		}
		this.states = this.initStates;
		
	}
	public void initGrid()//inicjalizacji siatki
	{
		for(int i= 0 ; i<pixels.length;i++)
		{
			pixels[i]=0x00FF00;
			
		}
		
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	public void setWidth(int Width)
	{
		width = Width;
	}
	public void setHeight(int Height)
	{
		height=Height;
	}
	
	public void setNumOfPatientsX(int numOfPatietnsX)
	{
		this.numOfPatientsX = numOfPatietnsX;
	}
	
	
	public void setgetHealthyRate(double Rate)
	{
		this.getHealthyRate = Rate;
	}
	
	
	public void settras_rate(double Rate)
	{
		this.tras_rate = Rate;
	}
	
		
	public int getNumOfPatientsX()
	{
		return numOfPatientsX;
	}
}
