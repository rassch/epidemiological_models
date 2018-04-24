import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;





//https://www.youtube.com/watch?v=17y2hZWJN0U
@SuppressWarnings("serial")
public class AnimationPanel extends Canvas implements Runnable{
	
	
	
	//Graphics2D g;
	public States states = new States();
	private BufferedImage img = new BufferedImage(states.human.getHeight(), states.human.getWidth(), BufferedImage.TYPE_INT_RGB); //robimy BufferedImage dla 20x20 pikseli(ludzik�w);

	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();	//getRaster daje tablice pikseli ktorym mozemy zmieniac kolory, getData je wyciaga w postaci inta
	private int[] tmp;
	private Thread thread1;

	private int animationSpeed2 = 10;//odswiezanie co x ms

	
	private boolean running = false;//animacja chodzi lub nie
	
	
	public synchronized void start()//funkcja do uruchuomienia procesu animacji
	{
		
		running=true;//odpalanie petli animacji
		thread1 = new Thread(this,"animacja");
		thread1.start();
	}
	
	
	public void stop()	//funckja do zatrzymania procesu animacji
	{
		
		
		
		running=false;
		try{
		 
		thread1.join();//thread.join czeka na az thread1 skonczy sie wykonywac, czyli na ostatnie odswiezenie animacji 
		states.human.initGrid();
		//wylaczenie petli animacji
		}catch(InterruptedException e)
		{
			
			thread1.interrupt();
			e.printStackTrace();//jak sie zwali to wypisze jakiegos tam errora
		}
	}
	
	public synchronized void run()//metoda startujaca i renderujaca na bierzaco animacje
	{
		/*
		long lastTime = System.nanoTime();//czas komputera w ns
		final double ns = 1000000000.0 /animationSpeed;
		double delta = 0;
		*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(running)
		{
			/*
			long now = System.nanoTime();
			delta+=(now -lastTime)/ns;
			lastTime = now ;
			while(delta >=1)
			{
				update();	//odswiezanie parametrow do animacji animationSpeed razy na seuknde
				delta--;
			}
			*/
			try {
				Thread.sleep(animationSpeed2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			render();//odswiezanie samej animacji
		}
		//stop();
	}

	public void init()
	{
		
		tmp = states.init();
	
		for(int i=0;i<pixels.length;i++)
		{
			
			pixels[i]=tmp[i];
		}
		
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
				
			createBufferStrategy(1);//trzymanie 3 klatek w buforze 
			bs = getBufferStrategy();
		}

		Graphics g1 = bs.getDrawGraphics();
		
		g1.drawImage(img,0,0,getWidth(),getHeight(),null);
		bs = getBufferStrategy();
		bs.show();

		return;
		
	}
	public synchronized void  render()
	{
		
		
		BufferStrategy bs = getBufferStrategy();//bufor dla klatek animacji, dzieki temu program zapamietuje poprzedni stan animacji, gdzies obok oblicza 
		if(bs==null)
		{
			
			createBufferStrategy(1);//trzymanie 3 klatek w buforze
			return;
		}
		
		
		int[] tmp = states.statesToPixels();
		
		for(int i=0;i<pixels.length;i++)
		{
		
			pixels[i]=tmp[i];	//przepisanie kolorow z humana na tablice panelu animacji
		}
		
	
		
		Graphics g = bs.getDrawGraphics();//��czy obiekty graficze z BufferStrategy
		
		g.drawImage(img,0,0,getWidth(),getHeight(),null);
		g.dispose();//wyrzuca stary obiekt g robiac miejsce dla kolejnego
		bs.show();//pokazujemy nasz obraz
	
	}
	public AnimationPanel()
	{
		//start();
	}

}
