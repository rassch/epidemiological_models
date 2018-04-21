import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class WindowGUI extends JFrame {
	
	SettingsPanel settings = new SettingsPanel();
	AnimationPanel animation = new AnimationPanel();
	
	public void setValuesAnim(){
		//animation.states.human.setHeight(settings.sim_size.getValue()); // problem z konstruktorem
		//animation.states.human.setWidth(settings.sim_size.getValue()); // problem z konstruktorem
		animation.states.human.setNumOfPatientsX(settings.sick_people_slider.getValue()); 
		animation.states.human.setgetHealthyRate(settings.recovery_rate.getValue()*0.01);//reszta działa
		animation.states.human.settras_rate(settings.transm_rate.getValue()*0.01);
		animation.states.human.setdeath_rate(settings.death_rate.getValue()*0.01);
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
		   @Override
		   public void actionPerformed(ActionEvent event) {
			  setValuesAnim();  // nie wiem jak zrobic zeby animacja ruszała od nowa
				
			   
		   }

		});
	settings.play.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setValuesAnim();//przypisanie poczatkowych parametrow
			run();//wlaczanie animacji
			
		}
	});
	settings.reset.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			stop();//wylaczanie animacji
			
		}
	});
	//animation.stop();
	this.setVisible(true);
	this.setSize(650, 480);
	//this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
}
