import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class JupiterBoardStyle implements BoardStyleManager{
	
	private Shape pitShape;
	private Shape stoneShape;


	public void drawPit(Graphics2D g2) {
		Rectangle2D.Double pit = new Rectangle2D.Double(0, 0, 50, 50);
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

	@Override
	public void drawStone(Graphics2D g2) {
		Ellipse2D.Double stone = new Ellipse2D.Double(0, 0, 10, 10);
		g2.setColor(Color.red);
		stoneShape = stone;
		g2.fill(stone);		
	}

	@Override
	public Shape getStone() {
		return stoneShape;
	}

	@Override
	public void drawMancala(Graphics2D g2) {
		Rectangle2D.Double mancala = new Rectangle2D.Double(0, 0, 50, 200);		
		g2.draw(mancala);
	}
	
	

}
