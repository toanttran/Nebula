import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Ye Sol, Toan
 * JupiterBoardStyle represents one concrete board style
 *
 */
public class JupiterBoardStyle implements BoardStyleManager{
	
	private Shape pitShape;
	private Shape stoneShape;


	/**
	 * draws the pits in the middle
	 */
	public void drawPit(Graphics2D g2) {
		Rectangle2D.Double pit = new Rectangle2D.Double(0, 0, 50, 50);
		pitShape = pit;
		g2.draw(pit);
	}

	/**
	 * returns the color of the board
	 */
	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	/**
	 * returns the shape of the board
	 */
	@Override
	public Shape getShape() {
		return pitShape;
	}

	/**
	 * draws stones
	 */
	@Override
	public void drawStone(Graphics2D g2) {
		Ellipse2D.Double stone = new Ellipse2D.Double(0, 0, 8, 8);
		g2.setColor(Color.red);
		stoneShape = stone;
		g2.fill(stone);		
	}

	/**
	 * returns the shape of the stones
	 */
	@Override
	public Shape getStone() {
		return stoneShape;
	}

	/**
	 * draws the mancala pits that are on the sides
	 */
	@Override
	public void drawMancala(Graphics2D g2) {
		Rectangle2D.Double mancala = new Rectangle2D.Double(0, 0, 50, 200);		
		g2.draw(mancala);
	}
	
	

}
