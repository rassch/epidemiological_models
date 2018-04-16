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
	
	private double animationSpeed = 60;
	
	
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
		double delta =0;
		
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
		
			render();//odswiezanie samej animacji
		}
		stop();
	}
	int x =0, y =0;
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
	}
	AnimationPanel()
	{
		//img = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
		//g = (Graphics2D) img.getGraphics();
		//g.setColor(Color.pink);
		//g.fillRect(0, 0, 40, 40);
		human = new Human(200,200);
		human.init();
	}
	void setAnimationSpeed(int n)
	{
		this.animationSpeed = n;
	}

}
