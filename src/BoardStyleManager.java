import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.ImageIcon;


public interface BoardStyleManager {
	
	void drawPit(Graphics2D g2);
	void drawMancala(Graphics2D g2);
	void drawStone(Graphics2D g2);
	
	Color getColor();
	Shape getShape();
	Shape getStone();
	

}

//draw mancala pit, make it taller than normal pits.
