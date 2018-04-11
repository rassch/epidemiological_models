import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


//https://www.youtube.com/watch?v=17y2hZWJN0U
public class AnimationPanel extends JPanel {
	
	Graphics2D g;
	BufferedImage img;
	
	
	AnimationPanel()
	{
		img = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) img.getGraphics();
		g.setColor(Color.pink);
		g.fillRect(0, 0, 40, 40);
		
		
	}
	

}
