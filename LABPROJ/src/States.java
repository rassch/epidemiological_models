import java.util.Random;

public class States
{
	Human human = new Human(200, 200);//konstrkutory dla poszczegolnych typow modeli i oswiezan
	
	private int[] pixels;//piksele ktore trafia do AnimationPanel i zostana narysowane
	private int[] states;//stany na ktorych model operuje i updatuje

	private int[] statesToColors;
	private int[] render(boolean sis,boolean sir, boolean synchronous, boolean asynchronous)//wybor modelu i odswiezania
	{
			
		if(sis & synchronous )
		{
			
		}else if(sis & asynchronous)
		{
		
		}else if(sir & synchronous)
		{
		
		}else 
		{
		
		}	
		return states;
	}
	private void statesToPixels(int[] states)//funckja konwertujaca stany na kolory ktore zostana przypisane pikselom
	{
		statesToColors = null;
		Random random = new Random();//testowanie czy jest zmienny output
		for(int i=0;i<40000;i++)
		{
			statesToColors[i]=random.nextInt();
		}
		
		this.pixels = statesToColors;
	}
	public int[] pixels()//funkcja zwracajaca piksele gotowe do narysowania
	{
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
		//init();
	}

}
