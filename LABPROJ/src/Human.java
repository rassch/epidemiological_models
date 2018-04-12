
public class Human {
	
	//private int x;
	//private int y;
	private int size;
	private int state=1; // 1 SUSCEPTIBLE// 2 IMMUNE// 3 DEAD
	private int width, height;
	public int[] pixels;//zawiera dane do pikseli
	
	int xtime = 10,ytime =15;
	int counter =0;
	public Human(int width, int height, int state)
	{
		this.width=width;
		this.height=height;
		this.state=state;
		pixels = new int[width * height];
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
		counter++;//to narazie jest testowe
		if(counter %300 ==0)xtime--;
		if(counter %300 ==0)ytime--;
			for(int y=0;y<height;y++)
			{
				if(ytime<0 ||ytime>=height) break;//jak wyjdzie poza tablice to break
				
				for(int x=0;x<width;x++)
				{
					if(xtime<0 || xtime>=width) break;
					pixels[xtime+ytime*width] = 0xFF00FF;//x+y*width iterowanie od lewego gornego rogu po rzedach
					
				}		
			}
			//throw new ArrayIndexOutOfBoundsException();
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
