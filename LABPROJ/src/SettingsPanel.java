import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SettingsPanel extends JPanel { //TO DO dodać labele i actionListenery
	
	int width_length = 20;
	
	JSlider recovery_rate = new JSlider(0,100,10);
	JSlider transm_rate = new JSlider(0,100,10);
	JSlider death_rate = new JSlider(0,100,0);
	JLabel recovery_rate_label = new JLabel ("recovery rate (%)");
	JLabel transm_rate_label = new JLabel ("transmission rate (%)");
	JLabel death_rate_label = new JLabel ("death rate (%)");
	JLabel disease_settings_label = new JLabel ("SIMULATION SETTINGS");
	JLabel sick_people = new JLabel ("People starting sick");
	JSlider sick_people_slider = new JSlider(0,(int) (width_length*width_length*0.1),0);
	JLabel sim_size_label = new JLabel ("Simulation size");
	JSlider sim_size = new JSlider(10,100,100);
	JButton play = new JButton("GO!");
	
	
	SettingsPanel(){
		//group Layout
		GroupLayout objPropertiesPanelLayout = new GroupLayout(this);
		this.setLayout(objPropertiesPanelLayout);
		objPropertiesPanelLayout.setAutoCreateGaps(true);
		objPropertiesPanelLayout.setAutoCreateContainerGaps(true);
		//
		GroupLayout.Group columnO1 = objPropertiesPanelLayout.createParallelGroup(Alignment.LEADING);
		GroupLayout.Group lineO1 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO2 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO3 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO4 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO5 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO6 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO7 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO8 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group lineO9 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group line10 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group line11 = objPropertiesPanelLayout.createParallelGroup();
		GroupLayout.Group line12 = objPropertiesPanelLayout.createParallelGroup();
		//
		GroupLayout.SequentialGroup linesO = objPropertiesPanelLayout.createSequentialGroup();
		GroupLayout.SequentialGroup columnsO = objPropertiesPanelLayout.createSequentialGroup();
		linesO.addGroup(lineO1);
		linesO.addGroup(lineO2);
		linesO.addGroup(lineO3);
		linesO.addGroup(lineO4);
		linesO.addGroup(lineO5);
		linesO.addGroup(lineO6);
		linesO.addGroup(lineO7);
		linesO.addGroup(lineO8);
		linesO.addGroup(lineO9);
		linesO.addGroup(line10);
		linesO.addGroup(line11);
		linesO.addGroup(line12);
		columnsO.addGroup(columnO1);
		//
		objPropertiesPanelLayout.setHorizontalGroup(columnsO);
		objPropertiesPanelLayout.setVerticalGroup(linesO);
		
		lineO1.addComponent(disease_settings_label);
		columnO1.addComponent(disease_settings_label);
		
		lineO2.addComponent(recovery_rate_label);
		columnO1.addComponent(recovery_rate_label);
		
		lineO3.addComponent(recovery_rate);
		columnO1.addComponent(recovery_rate);
		
		lineO4.addComponent(transm_rate_label);
		columnO1.addComponent(transm_rate_label);
		
		lineO5.addComponent(transm_rate);
		columnO1.addComponent(transm_rate);
		
		lineO6.addComponent(death_rate_label);
		columnO1.addComponent(death_rate_label);
		
		lineO7.addComponent(death_rate);
		columnO1.addComponent(death_rate);
		
		lineO8.addComponent(sick_people);
		columnO1.addComponent(sick_people);
		
		lineO9.addComponent(sick_people_slider);
		columnO1.addComponent(sick_people_slider);
		
		line10.addComponent(sim_size_label);
		columnO1.addComponent(sim_size_label);
		
		line11.addComponent(sim_size);
		columnO1.addComponent(sim_size);
		
		line12.addComponent(play);
		columnO1.addComponent(play);
		
		
		//suwak do prawd. wyzdrowienia
		//recovery_rate.setOrientation(JSlider.VERTICAL);
		recovery_rate.setMajorTickSpacing(20);
		recovery_rate.setMinorTickSpacing(10);
		recovery_rate.setPaintTicks(true);
		recovery_rate.setPaintLabels(true);
		//do wybierania prawdopodobieństwa zarażenia
		//transm_rate.setOrientation(JSlider.VERTICAL);
		transm_rate.setMajorTickSpacing(20);
		transm_rate.setMinorTickSpacing(10);
		transm_rate.setPaintTicks(true);
		transm_rate.setPaintLabels(true);
		//do procenta śmiertelności
		//death_rate.setOrientation(JSlider.VERTICAL);
		death_rate.setMajorTickSpacing(20);
		death_rate.setMinorTickSpacing(10);
		death_rate.setPaintTicks(true);
		death_rate.setPaintLabels(true);
		//do liczby chorych
		sick_people_slider.setMajorTickSpacing((int) (width_length*width_length*0.02));
		sick_people_slider.setPaintTicks(true);
		sick_people_slider.setPaintLabels(true);
		//do boku kwadrata
		sim_size.setMajorTickSpacing(20);
		sim_size.setMinorTickSpacing(10);
		sim_size.setPaintTicks(true);
		sim_size.setPaintLabels(true);
		
		
		
		this.setBackground(new Color(204,255,255));
		//this.add(recovery_rate);
		this.add(transm_rate);
		this.add(death_rate);
		//synchroniczne czy nie 
		//this.add(asynchronous);
		

	}	
}
