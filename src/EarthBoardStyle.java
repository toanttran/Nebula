import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class EarthBoardStyle implements BoardStyleManager {
	
	private Shape pitShape;
	private Shape stoneShape;


	@Override
	public void drawPit(Graphics2D g2) {
		Ellipse2D.Double pit = new Ellipse2D.Double(0,0,50,50);
		pitShape = pit;
		g2.draw(pit);		
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public Shape getShape() {
		return pitShape;
	}

	@Override
	public void drawStone(Graphics2D g2) {
		Rectangle2D.Double stone = new Rectangle2D.Double(0, 0, 8, 8);
		g2.setColor(Color.blue);
		stoneShape = stone;
		g2.fill(stone);				
	}

	@Override
	public Shape getStone() {
		return stoneShape;
	}

	@Override
	public void drawMancala(Graphics2D g2) {
		Ellipse2D.Double mancala = new Ellipse2D.Double(0, 0, 50, 200);		
		g2.draw(mancala);		
	}


}
