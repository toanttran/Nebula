import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

public class MancalaPits extends JButton {

	Color color;
	Shape shape;
	
	public MancalaPits(Color c, Shape s) {
		color = c;
		shape = s;
	}
	
	public void paintPit(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double pit = new Ellipse2D.Double(0,0,10,10);
		g2.draw(pit);
	}

}
