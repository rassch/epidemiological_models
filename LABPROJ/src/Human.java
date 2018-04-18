import java.util.Random;

public class Human  {
	
	
	
	private int sickDaysPeriod = 20;
	private double tras_rate = 0.6; 
	private int width, height;
	private int numOfPatientsX = 5000;
	public int[] pixels;//zawiera dane do pikseli
	private int[] states;
	private int[] SIS;
	private int[] sickDays;
	private Random random = new Random();
	
	
	
	public Human(int width, int height)
	{
		this.width=width;
		this.height=height;
		pixels = new int[width * height];
	}
	
	
	public int[] sickDays(int[] states)
	{		
		for(int i=0 ; i<pixels.length;i++)
		{
			if(states[i]==0)
			{
				sickDays[i]++;
			}
			else
			{
				sickDays[i] = 0;
			}
			if(states[i]>sickDaysPeriod)
			{
				sickDays[i] = 0;
			}
		}
		return sickDays;
	}
	/*
	public int[] newStates(int[] sickDays)
	{
		for(int i=0;i<pixels.length;i++)
		{
			if(sickDays[i] == 1)
			{
				continue;
			}
			else
			{
				if(i-1 >= 0 && sickDays[i-1] == 1 )
				{
					if(random.nextFloat()<= tras_rate)
					{
						
					}
				}
			}
			
		}		
	}
	*/
	public int[] SIS()
	{
		
		
		
		//tutaj trzeba wklepaæ równania modelu SIS i zapisaæ nowe stany do this.SIS , gdzie Zielony piksel ma mieæ wartoœæ 65280 , a czerwony 16711680
		return this.SIS;
	}
	public void init()//inicjalizowanie poczatkowych chorych
	{
		for(int i =0;i<numOfPatientsX;i++)
		{
			pixels[random.nextInt(pixels.length)] = 0xFF0000;	
		}
	}
	public void initGrid()//inicjalizacji siatki
	{
		for(int i= 0 ; i<pixels.length;i++)
		{
			pixels[i]=0x00FF00;
		}
	}
	/* to raczej nie bedzie potrzebne
	public void clear()//czyszcenie pikseli
	{
		for(int i= 0 ; i<pixels.length;i++)
		{
			pixels[i]=0;
		}
	}
	*/
	public void render()//funckja przypisujaca kolory pikselom
	{
		pixels = SIS;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public void setNumOfPatientsX(int numOfPatietnsX)
	{
		this.numOfPatientsX = numOfPatietnsX;
	}
	
	
	
}
