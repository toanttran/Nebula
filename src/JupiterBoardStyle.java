import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class JupiterBoardStyle implements BoardStyleManager{
	
	private int x;
	private int y;
	private int width;

	public void drawPit(Graphics2D g2) {
		Rectangle2D.Double pit = new Rectangle2D.Double(x, y + width, width, width);
		g2.draw(pit);
	}
	
	

}
