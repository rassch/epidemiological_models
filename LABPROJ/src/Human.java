import java.util.Random;

public class Human  {
	
	
	private int width, height;
	private int numOfPatientsX = 10000;
	public int[] pixels;//zawiera dane do pikseli
	private int[] states;
	private int[] SIS;
	private Random random = new Random();
	public Human(int width, int height)
	{
		this.width=width;
		this.height=height;
		pixels = new int[width * height];
	}
	public int[] SIS()
	{
		//tutaj trzeba wklepaæ równania modelu SIS i zapisaæ nowe stany do this.SIS , gdzie Zielony piksel ma mieæ wartoœæ 65280 , a czerwony 16711680
		return this.SIS;
	}
	public void init()
	{
		for(int i =0;i<numOfPatientsX;i++)
		{
			//System.out.println(pixels.length); rozmiar width * height
			//System.out.println(random.nextInt(pixels.length));
			pixels[random.nextInt(pixels.length)] = 0xFF0000;
			//System.out.println(pixels[random.nextInt(pixels.length)]);
		}
	}
	public void initGrid()
	{
		for(int i= 0 ; i<pixels.length;i++)
		{
			pixels[i]=0x00FF00;
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
		
		pixels = SIS;	
		/*
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
					pixels[x+y*width] = random.nextInt();//x+y*width iterowanie od lewego gornego rogu po rzedach
					
				}		
			}
			*/
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
