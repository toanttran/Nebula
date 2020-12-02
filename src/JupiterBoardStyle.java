import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class JupiterBoardStyle implements BoardStyleManager{
	
	private Shape pitShape;

	public void drawPit(Graphics2D g2) {
		Ellipse2D.Double pit = new Ellipse2D.Double(0, 0, 50, 50);
		pitShape = pit;
		g2.draw(pit);
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public Shape getShape() {
		return pitShape;
	}
	
	

}
