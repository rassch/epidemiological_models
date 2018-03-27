import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowGUI extends JFrame {{
	
	AnimationPanel animation = new AnimationPanel();
	SettingsPanel settings = new SettingsPanel(); // tworzenie paneli
	this.setTitle("EpidemicsSimulator2000"); //tytuł
	this.setLayout(new BorderLayout());
	this.add(settings, BorderLayout.EAST);
	this.add(animation, BorderLayout.WEST);
	 // dodanie paneli do okna
	
	
	
	this.setVisible(true);
	this.setSize(640, 400);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
}

}
