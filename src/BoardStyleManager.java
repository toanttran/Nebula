import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;


public interface BoardStyleManager {
	
	void drawPit(Graphics2D g2);
	
	Color getColor();
	Shape getShape();
	

}

//draw mancala pit, make it taller than normal pits.
