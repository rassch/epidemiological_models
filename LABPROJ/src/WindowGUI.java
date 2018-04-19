import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class WindowGUI extends JFrame {
	
	SettingsPanel settings = new SettingsPanel();
	AnimationPanel animation = new AnimationPanel();
	
	public void setValuesAnim(){
		animation.states.human.setHeight(settings.sim_size.getValue()); // problem z konstruktorem
		animation.states.human.setWidth(settings.sim_size.getValue()); // problem z konstruktorem
		animation.states.human.setNumOfPatientsX(settings.sick_people_slider.getValue()); 
		animation.states.human.setgetHealthyRate(settings.recovery_rate.getValue()*0.01);//reszta działa
		animation.states.human.settras_rate(settings.transm_rate.getValue()*0.01);
		animation.states.human.setdeath_rate(settings.death_rate.getValue()*0.01);
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
	
	
	
	
	settings.play.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent event) {
			  setValuesAnim();  // nie wiem jak zrobic zeby animacja ruszała od nowa
				
			   
		   }

		});
	//animation.stop();
	this.setVisible(true);
	this.setSize(650, 480);
	//this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	}
}
