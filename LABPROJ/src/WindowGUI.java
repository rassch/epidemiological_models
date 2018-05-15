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

	
	
	public void  mean_values_export (LinkedList<Integer> tab, String filename)
	{
		
		String name = filename + ".txt";
		ListIterator<Integer> litr = null;
		litr=tab.listIterator();
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
			
			Simulation sim = new Simulation();
			/*
			for (int i = 0; i <animation.states.simulationIterations; i++)
			{
				settings.play.doClick();
			
				if(animation.states.iterationCounter == 3000)
				{
					
					settings.reset.doClick();
					animation.states.addToMean();
				}
			}
			animation.states.resetCounter();//czyszczenie licznika
			//animation.states.getHealthyMean();//zwraca usredniona linkedliste zdrowych analogiczne funkcje dla sick i immune
			mean_values_export(animation.states.getHealthyMean(),"liczba zdrowych");
			animation.states.clear();//czyszczenie tablicy po zapisaniu do pliku
			
		*/	
		}
		
	});
	
	//animation.stop();
	this.setVisible(true);
	this.setSize(650, 480);
	//this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
}
