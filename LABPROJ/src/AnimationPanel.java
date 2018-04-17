import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;




//https://www.youtube.com/watch?v=17y2hZWJN0U
@SuppressWarnings("serial")
public class AnimationPanel extends Canvas implements Runnable{
	
	
	private Human human;
	//Graphics2D g;
	
	private BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB); //robimy BufferedImage dla 20x20 pikseli(ludzików);

	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();	//getRaster daje tablice pikseli ktorym mozemy zmieniac kolory, getData je wyciaga w postaci inta
	
	private Thread thread1;
	
	private double animationSpeed = 2;
	private int animationSpeed2 = 1000;
	private int[] states;

	
	private boolean running = false;//animacja chodzi lub nie
	
	
	public synchronized void start()//funkcja do uruchuomienia procesu animacji
	{
		//odpalanie petli animacji
		running=true;
		thread1 = new Thread(this,"animacja");
		thread1.start();
	}

	public synchronized void stop()	//funckja do zatrzymania procesu animacji
	{
		
		running=false;//wylaczenie petli animacji
		try{
		 
		thread1.join();//thread.join czeka na az thread1 skonczy sie wykonywac, czyli na ostatnie odswiezenie animacji 
		}catch(InterruptedException e)
		{
			 
			e.printStackTrace();//jak sie zwali to wypisze jakiegos tam errora
		}
	}
	
	public synchronized void run()//metoda startujaca i renderujaca na bierzaco animacje
	{
		long lastTime = System.nanoTime();//czas komputera w ns
		final double ns = 1000000000.0 /animationSpeed;
		double delta = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init();
		while(running)
		{
			
			long now = System.nanoTime();
			delta+=(now -lastTime)/ns;
			lastTime = now ;
			while(delta >=1)
			{
				update();	//odswiezanie parametrow do animacji animationSpeed razy na seuknde
				delta--;
			}
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
	int x =0, y =0;
	public synchronized void init()
	{
		System.out.println("Init rusza");
		
		human.initGrid();;//rysujemy nowy obraz
		human.init();
		for(int i=0;i<pixels.length;i++)
		{
			//przepisanie kolorow z humana na tablice panelu animacji
			pixels[i]=human.pixels[i];
		}
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
				
			createBufferStrategy(1);//trzymanie 3 klatek w buforze 
			bs = getBufferStrategy();
		}
		
		Graphics g1 = bs.getDrawGraphics();
		
		g1.drawImage(img,0,0,getWidth(),getHeight(),null);
		bs.show();
		System.out.println("Powinien byæ obraz");
		return;
		
	}
	public synchronized void update()
	{
		//y++;
		//x++;
		//y++;
	}
	public synchronized void  render()
	{
		//bufor dla klatek animacji, dzieki temu program zapamietuje poprzedni stan animacji, gdzies obok oblicza 
		//paramtery dla kolejnego i potem moze narysowac kolejna klatke
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			//tworzenie tego obiektu gdyby tam wyzej sie nie zrobil w czasie animacji
			createBufferStrategy(1);//trzymanie 3 klatek w buforze
			return;
		}
		
		//wypelnianie poszczegolnych pikseli wybranymi kolorami
		human.render();//rysujemy nowy obraz
		
		for(int i=0;i<pixels.length;i++)
		{
			//przepisanie kolorow z humana na tablice panelu animacji
			pixels[i]=human.pixels[i];
		}
		
		//³¹czy obiekty graficze z BufferStrategy
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(img,0,0,getWidth(),getHeight(),null);
		g.dispose();//wyrzuca stary obiekt g robiac miejsce dla kolejnego
		bs.show();//pokazujemy nasz obraz
		System.out.println("A TU DZIA£A !!!");
	}
	AnimationPanel()
	{
		human = new Human(200,200);
		
	}
	void setAnimationSpeed(int n)
	{
		this.animationSpeed = n;
	}
	public int[] states()
	{
		for(int i=0;i<pixels.length;i++)
		{
			if(this.pixels[i] == 65280)
			{
				states[i]=1;//ZIELONY
			}
			else
			states[i] =0;//CZERWONY
		}
		return this.states;
	}
}
