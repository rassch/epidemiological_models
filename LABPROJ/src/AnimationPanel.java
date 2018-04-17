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
	//robimy BufferedImage dla 20x20 pikseli(ludzików);
	private BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB); ;
	//getRaster daje tablice pikseli ktorym mozemy zmieniac kolory, getData je wyciaga w postaci inta
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	
	private Thread thread1;
	
	private double animationSpeed = 2;
	private int animationSpeed2 = 500;
	
	
	//animacja chodzi lub nie
	private boolean running = false;
	
	//funkcja do uruchuomienia procesu animacji
	public synchronized void start()
	{
		//odpalanie petli animacji
		running=true;
		thread1 = new Thread(this,"animacja");
		thread1.start();
	}
	//funckja do zatrzymania procesu animacji
	public synchronized void stop()
	{
		//wylaczenie petli animacji
		running=false;
		try{
		//thread.join czeka na az thread1 skonczy sie wykonywac, czyli na ostatnie odswiezenie animacji  
		thread1.join();
		}catch(InterruptedException e)
		{
			//jak sie zwali to wypisze jakiegos tam errora 
			e.printStackTrace();
		}
	}
	//metoda startujaca i renderujaca na bierzaco animacje
	public void run()
	{
		long lastTime = System.nanoTime();//czas komputera w ns
		final double ns = 1000000000.0 /animationSpeed;
		double delta = 0;
	
		init();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	public void init()
	{
		System.out.println("Init rusza");
		human.clear();//zerujemy tablice
		human.initGrid();;//rysujemy nowy obraz
		human.init();
		for(int i=0;i<pixels.length;i++)
		{
			//przepisanie kolorow z humana na tablice panelu animacji
			pixels[i]=human.pixels[i];
		}
		BufferStrategy bs = getBufferStrategy();
		createBufferStrategy(1);
		Graphics g1 = bs.getDrawGraphics();
		
		g1.drawImage(img,0,0,getWidth(),getHeight(),null);
		System.out.println("Powinien byæ obraz");
		return;
		
	}
	public void update()
	{
		//y++;
		//x++;
		//y++;
	}
	public void render()
	{
		//bufor dla klatek animacji, dzieki temu program zapamietuje poprzedni stan animacji, gdzies obok oblicza 
		//paramtery dla kolejnego i potem moze narysowac kolejna klatke
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			//tworzenie tego obiektu gdyby tam wyzej sie nie zrobil w czasie animacji
			createBufferStrategy(3);//trzymanie 3 klatek w buforze
			return;
		}
		
		//wypelnianie poszczegolnych pikseli wybranymi kolorami
		human.clear();//czyscimy stary obraz
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

}
