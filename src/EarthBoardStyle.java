import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

public class EarthBoardStyle implements BoardStyleManager {

	@Override
	public void drawPit(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Ellipse2D.Double(0,0,10,10);
	}


}
