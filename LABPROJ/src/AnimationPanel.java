import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class AnimationPanel extends JPanel implements ActionListener {
	
	Human[] people = new Human[400];
	AnimationPanel (){ 	// konstruktor inicjalizuje obiekty human 
		this.setBackground(Color.red);	
		int n = 0;
		for (int i=0; i < 20; i++) //kreacja kwadratów i - OX; y - OY
		{
			for (int j = 0; j<20;j++)
			{
				people[n] = new Human(i*20,j*20,"susceptible");
				n++;
			}
		}
	}

	public void drawSquares(Graphics g) { // zrobiæ t¹ funkcjê z whilem bo przy zmianie liczby kwadratów przypa³
		
		for (int i  = 0; i <400; i++)
		{
			g.drawRect(people[i].getX(), people[i].getY(),people[i].getSize(),people[i].getSize());
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
