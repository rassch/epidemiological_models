import java.util.Random;

public class Human  {
	
	States data;
	
	private int sickDaysPeriod = 3;//czas trwania choroby
	private double tras_rate = 1.0; //parametr zarazania
	private int width, height;
	private int numOfPatientsX = 1;//poczatkowa liczba chorych
	public int[] pixels;//zawiera dane do pikseli
	private int[] states = new int[3 * 3];
	private int[] sickDays = new int[3 * 3];
	private int[] initStates = new int[3 * 3] ;
	private Random random = new Random();
	
	
	
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
	private void sickDays()// STAN 0 UZNAJEMY ZA SUSCEPTIBLE 1 ZA INFECTIOUS
	{		
		for(int i=0 ; i<pixels.length;i++)
		{
			if(states[i] != 0)//jezeli chory, czyli stan pikesla jest 1 to zliczamy liczbe dni jego choroby i przy kazdej iteracji zwieszkam tutaj o jeden 
			{
				this.sickDays[i]++;//sickDays trzymamy tutaj bo ta tablica zapamietuje ilosc dni choroby, a nie stany chory/zdrowy
			}
			else
			{
				this.sickDays[i] = 0;//jak nie byl chory to liczba dni jego choroby wynosi 0
			}
			if(sickDays[i]>sickDaysPeriod)//jezeli dany piksel przechorowal juz wiecej dni niz bylo w parametrze programu to uznajemy go za zdrowego i zerujemy jego stan chorych dni
			{
				//System.out.println("HALO!");
				this.sickDays[i] = 0;
			}
			//System.out.println(sickDays[i]);
		}
	
	}
	
	public int[] newStates()//funkcja zwracajaca nowe stany 
	{
		sickDays();
	
		for(int i=0;i<pixels.length;i++)
		{
			
			if(sickDays[i] != 0)//jezeli nasz piksel jest chory to idziemy dalej, bo nie nie da sie go ponownie zarazic, a nasz model nie zaklada resetowania sie dni trwania choroby w jej trakcie
			{
				//System.out.println("omijam chory piksel"); 
				states[i] = 1;
			}
			else//czyli jezeli ten i-ty piksel jest zdrowy to probojemy go zarazic
			{
				System.out.println(i);
				
				if((i-1 >= 0 && (i-1) % width != width-1) && sickDays[i-1] != 0 ) //jezeli piksel obok istnieje, jest w tym samym rzedzie i jest chory to rzucamy kostka, czy nasz zdrowy i-ty piksel sie od niego zarazi 
				{
					System.out.println(i);
					System.out.println(sickDays[i-1]);
					System.out.println("Jest szansa na zara¿enie od lewego");
					if(random.nextFloat()<= tras_rate)// jak liczba wypadnie w przedziale 0-trans_rate to piksel zotaje zarazony
					{
						System.out.println("uda³o siê lewy");
						System.out.println(random.nextFloat());
						states[i] = 1;//zarazanie piksela
						sickDays[i] = 1;//pierwszy dzien choroby
					}
				}
			    if((i+1 < pixels.length && (i+1) % width != 0) && sickDays[i+1] != 0)//sprawdzamy kolejnego sasiada, tym razem z prawej strony
				{
			    	System.out.println("Jest szansa na zara¿enie od prawego");
			    	if(random.nextFloat()<= tras_rate && sickDays[i] != 0)//sprawdzamy dodatkowo czy poprzedni sasiad juz nie zdazyl zarazic naszego piksela
					{
						System.out.println("uda³o siê prawy");
						System.out.println(random.nextFloat());
						states[i] = 1;
						sickDays[i] = 1;
					}
				}
			    if(i-width >= 0 && sickDays[i-width] != 0)//gorny sasiad
			    {
			    	System.out.println("Jest szansa na zara¿enie od gornego");
			    	if(random.nextFloat()<= tras_rate && sickDays[i] != 0)
			    	{
			    		System.out.println("uda³o siê gora");
						System.out.println(random.nextFloat());
			    		states[i] = 1;
						sickDays[i] = 1;
					}
			    }
			    if(i+width < pixels.length && sickDays[i+width] != 0)//dolny sasiad
			    {
			    	System.out.println("Jest szansa na zara¿enie od dolnego");
			    	if(random.nextFloat()<= tras_rate && sickDays[i] != 0)
			    	{
			    		System.out.println("uda³o siê dol" );
						System.out.println(random.nextFloat());
			    		states[i] = 1;
						sickDays[i] = 1;
					}
			    }
			}
			//System.out.println(states[i]);
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
	public void setNumOfPatientsX(int numOfPatietnsX)
	{
		this.numOfPatientsX = numOfPatietnsX;
	}
	
	
	
}
