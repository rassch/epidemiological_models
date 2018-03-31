import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SettingsPanel extends JPanel { //TO DO dodać labele i actionListenery
	
	JSlider sick_days = new JSlider(0,25,7);
	JSlider transm_rate = new JSlider(0,100,10);
	JSlider death_rate = new JSlider(0,100,0);
	JLabel sick_days_label = new JLabel ("sick(days)");
	JLabel transm_rate_label = new JLabel ("transmission rate (%)");
	JLabel death_rate_label = new JLabel ("death rate (%)");
	JLabel disease_settings_label = new JLabel ("SIMULATION SETTINGS");
	JRadioButton asynchronous = new JRadioButton("asynchronous");
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
		columnsO.addGroup(columnO1);
		//
		objPropertiesPanelLayout.setHorizontalGroup(columnsO);
		objPropertiesPanelLayout.setVerticalGroup(linesO);
		
		lineO1.addComponent(disease_settings_label);
		columnO1.addComponent(disease_settings_label);
		
		lineO2.addComponent(sick_days_label);
		columnO1.addComponent(sick_days_label);
		
		lineO3.addComponent(sick_days);
		columnO1.addComponent(sick_days);
		
		lineO4.addComponent(transm_rate_label);
		columnO1.addComponent(transm_rate_label);
		
		lineO5.addComponent(transm_rate);
		columnO1.addComponent(transm_rate);
		
		lineO6.addComponent(death_rate_label);
		columnO1.addComponent(death_rate_label);
		
		lineO7.addComponent(death_rate);
		columnO1.addComponent(death_rate);
		
		lineO8.addComponent(asynchronous);
		columnO1.addComponent(asynchronous);
		
		lineO9.addComponent(play);
		columnO1.addComponent(play);
		
		
		//suwak do wybierania dni bycia chorym
		//sick_days.setOrientation(JSlider.VERTICAL);
		sick_days.setMajorTickSpacing(5);
		sick_days.setMinorTickSpacing(1);
		sick_days.setPaintTicks(true);
		sick_days.setPaintLabels(true);
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
	
		
		this.setBackground(new Color(204,255,255));
		//this.add(sick_days);
		this.add(transm_rate);
		this.add(death_rate);
		//synchroniczne czy nie 
		this.add(asynchronous);
		

	}	
}
