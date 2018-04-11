import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowGUI extends JFrame {{
	
	SettingsPanel settings = new SettingsPanel(); // tworzenie paneli
	this.setTitle("EpidemicsSimulator2000"); //tytuł
	this.setLayout(new BorderLayout());
	this.add(settings, BorderLayout.EAST);
	
	 // dodanie paneli do okna
	
	
	
	this.setVisible(true);
	this.setSize(650, 410);
	this.setResizable(false);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
}

}
