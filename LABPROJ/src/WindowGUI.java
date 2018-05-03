import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class WindowGUI extends JFrame {
	
	SettingsPanel settings = new SettingsPanel();
	AnimationPanel animation = new AnimationPanel();
	LinkedList<Integer>[] healthy_tab = new LinkedList[10];
	LinkedList<Integer>[] sick_tab = new LinkedList[10];
	LinkedList<Integer>[] immune_tab = new LinkedList[10];
	LinkedList<Integer>[] periods_tab = new LinkedList[10];
			
	public void  mean_values_export (LinkedList<Integer>[] tab, String filename)
	{
		
		String name = filename + ".txt";
		ListIterator<Integer> litr = null;
		litr=tab[1].listIterator();
		PrintWriter writer;
		try {
			writer = new PrintWriter(name, "UTF-8");
			while(litr.hasNext()){
				//System.out.println(litr.next());
				writer.println(litr.next());
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void setValuesAnim(){
		//animation.states.human.setHeight(settings.sim_size.getValue()); // problem z konstruktorem
		//animation.states.human.setWidth(settings.sim_size.getValue()); // problem z konstruktorem
		animation.states.human.setNumOfPatientsX(settings.sick_people_slider.getValue()); 
		animation.states.human.setgetHealthyRate(settings.recovery_rate.getValue()*0.01);//reszta działa
		animation.states.human.settras_rate(settings.transm_rate.getValue()*0.01);
		animation.animationSpeed2 = (int) 100/settings.sim_speed.getValue();
		
	}
	public void run()
	{
		animation.start();
	}
	public void stop()
	{
		animation.stop();
	}
	{
	 // tworzenie paneli
	
	//ButtonActionListener go_listener = new ButtonActionListener();
	this.setTitle("EpidemicsSimulator2000"); //tytuł
	this.setLayout(new BorderLayout());
	this.add(settings, BorderLayout.EAST);
	this.add(animation);
	this.pack();
	
	
	
	 // dodanie paneli do okna
	//animation.start();
	
	
	
	
	settings.update.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent event) {
			  setValuesAnim();  // nie wiem jak zrobic zeby animacja ruszała od nowa
				
			   
		   }

		});
	settings.play.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			animation.states.setSISTrue();
			settings.play_sir.setEnabled(false);
			settings.play.setEnabled(false);
			setValuesAnim();//przypisanie poczatkowych parametrow
			run();//wlaczanie animacji
			
		}
	});
	
	settings.play_sir.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			animation.states.setSIRtrue();
			settings.play_sir.setEnabled(false);
			settings.play.setEnabled(false);
			setValuesAnim();//przypisanie poczatkowych parametrow
			run();//wlaczanie animacji
			
		}
	});
	settings.reset.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			stop();//wylaczanie animacji
			settings.play_sir.setEnabled(true);
			settings.play.setEnabled(true);
		}
	});
	
	
	settings.repeat.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			for (int i = 0; i < 10; i++)
			{
				
				
				if (healthy_tab[i] == null) {
					healthy_tab[i] = new LinkedList<Integer>();
				  }
				
				if (sick_tab[i] == null) {
					sick_tab[i] = new LinkedList<Integer>();
				  }
				
				if (immune_tab[i] == null) {
					immune_tab[i] = new LinkedList<Integer>();
				  }
				
				if (periods_tab[i] == null) {
					periods_tab[i] = new LinkedList<Integer>();
				  }
				
				
				settings.play.doClick();
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				animation.states.addToMean();//dodawanie kazdej iteracji
				healthy_tab[i]= animation.states.healthyMean;
				settings.reset.doClick();
			}
			//animation.states.getHealthyMean();//zwraca usredniona linkedliste zdrowych analogiczne funkcje dla sick i immune
			mean_values_export(healthy_tab,"liczba zdrowych");
			
			
			
		}
	});
	
	//animation.stop();
	this.setVisible(true);
	this.setSize(650, 480);
	//this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
}
