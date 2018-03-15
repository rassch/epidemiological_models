import javax.swing.JFrame;

public class EpidemicsSimulator2000 {

	public static void main(String[] args) {
		
		Human czlek = new Human(2,1, "susceptible");
		
		JFrame frame = new JFrame("EpidemicsSimulator2000"); //Tworzenie nowego obiektu klasy JFrame
		frame.setSize(640, 480); // Ustawienie rozmiaru okna
		frame.setVisible(true); // Wyświetlenie ramki

	}

}
