

public class States
{
	public Human human = new Human(100, 100);//konstrkutory dla poszczegolnych typow modeli i oswiezan
	
	private int[] pixels = new int[human.getHeight() * human.getWidth()];//piksele ktore trafia do AnimationPanel i zostana narysowane
	private int[] states = new int[human.getHeight() * human.getWidth()];//stany na ktorych model operuje i updatuje
	private int[] tmp = new int[human.getHeight() * human.getWidth()];
	private boolean sis=true,synchronous=true,sir= false;//wybor modelu i rodzaju odswiezania
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
		
	}
	public int[] statesToPixels()//funckja konwertujaca stany na kolory ktore zostana przypisane pikselom i zwracajaca je do AnimationPanel
	{
		
		update();
		//Random random = new Random();//testowanie czy jest zmienny output
		for(int i=0;i<pixels.length;i++)
		{
			if(states[i] == 0)//Czyli jak stan zdrowy
			{
				pixels[i] = 65280 ;//to bedzie mial zielony kolor
			}
			else if (states[i] == -1)//czyli immune
			{
				pixels[i] = 0x4286F4;//wtedy kolor blu
			}
			else//czyli stan chory
			{
				pixels[i] = 16711680;//wtedy kolor czerwony
			}
		}
		return this.pixels;
	}
	public int[] init()//funkcja inicjalizujaca mape
	{
		human.initGrid();//inicjalizujemy ZIELONA mape
		human.init();//funkcja generujaca poczatkowa liczbe zarazonych losowo rozlozonych po mapie
		this.pixels= human.pixels;
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
}
	


