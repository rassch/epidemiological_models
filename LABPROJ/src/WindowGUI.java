import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowGUI extends JFrame {{
	
	SettingsPanel settings = new SettingsPanel(); // tworzenie paneli
	AnimationPanel animation = new AnimationPanel();
	
	this.setTitle("EpidemicsSimulator2000"); //tytuł
	this.setLayout(new BorderLayout());
	this.add(settings, BorderLayout.EAST);
	this.add(animation);
	this.pack();
	 // dodanie paneli do okna
	
	animation.start();
	
	this.setVisible(true);
	this.setSize(650, 410);
	//this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}

}
