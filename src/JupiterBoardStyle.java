import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class JupiterBoardStyle implements BoardStyleManager{
	
	private int x;
	private int y;
	private int width;

	public void drawPit(Graphics2D g2) {
		Rectangle2D.Double pit = new Rectangle2D.Double(x, y + width, width, width);
		g2.draw(pit);
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Ellipse2D.Double(0,0,10,10);
	}
	
	

}
