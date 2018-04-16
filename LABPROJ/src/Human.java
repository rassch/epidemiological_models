import java.util.Random;

public class Human {
	
	//private int x;
	//private int y;
	private int size;
	//private int state=1; // 1 SUSCEPTIBLE// 2 IMMUNE// 3 DEAD
	private int width, height;
	private int numOfPatientsX = 5;
	public int[] pixels;//zawiera dane do pikseli
	//public final int MAP_SIZE = 64 ;
	//public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	//public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	private Random random = new Random();
	public Human(int width, int height)
	{
		this.width=width;
		this.height=height;
		pixels = new int[width * height];
		/*
		for(int i=0;i<MAP_SIZE*MAP_SIZE;i++)
		{
			tiles[i]= random.nextInt();
			tiles[0]=0x000000;
		}
		*/
	}
	public void init()
	{
		for(int i =0;i<numOfPatientsX;i++)
		{
			pixels[random.nextInt(pixels.length)] = 0x000000;
		}
	}
	
	public void clear()//czyszcenie pikseli
	{
		for(int i= 0 ; i<pixels.length;i++)
		{
			pixels[i]=0;
		}
	}
	public void render()//funckja przypisujaca kolory pikselom
	{
		
			
			for(int y=0;y<height;y++)
			{
				//int yy =y +yOffset;
				//if(yy<0 ||yy>=height) break;//jak wyjdzie poza tablice to break
				for(int x=0;x<width;x++)
				{
					//int xx =x+xOffset ;
					//if(xx<0 || xx>=width) break;
					//ten & powoduje , ¿e xx i yy nigdy nie przekrocz¹ 64 indeksu
					//xx >> 4 czyli innymi slowy xx nigdy nie bedzie wieksze od 4
					//int tileIndex =((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
					pixels[x+y*width] = 0x00FF00;//x+y*width iterowanie od lewego gornego rogu po rzedach
					
				}		
			}
		
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	
	/*
	public Human (int X, int Y, String STATE){
		
		x = X;
		y = Y;
		if (STATE == "susceptible")
			state = 1;
		if (STATE == "immune")
			state = 2;
		if (STATE == "dead")
			state = 3;
		size = 20;
		
		
		
	}
	
	public int getX ()
	{
		return this.x;
	}
	
	
	
	public int getY ()
	{
		return this.y;
	}

	public int getSize ()
	{
		return this.size;
	}

	public int getState ()
	{
		return this.state;
	}
	
	public void setState (int STATE)
	{
		state = STATE;
	}
	*/
}
