import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author toanmacbook
 *
 */
public class MancalaView extends JFrame {
	
	/**
	 * 
	 * @param chooseBoardStyle
	 * @param chooseStoneNum
	 * @param width
	 * @param height
	 */
	public MancalaView(String boardStyle, String stoneNumber, int width, int height) {
		
		JFrame frame = new JFrame("Mancala Game");
		frame.setSize(width, height);
		frame.setLayout(new BorderLayout());
		
		//Panels to layout
		JPanel mancalaAPanel = new JPanel();
		JPanel mancalaBPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel pitsPanel = new JPanel();
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		
		JButton pitButtons = new JButton("pit");
		JButton mancalaAButton = new JButton("A");
		JButton mancalaBButton = new JButton("B");
		
		BoardStyleManager board;
		
		if(boardStyle.equals("EarthBoard"))
		{
			
		}
		else if(boardStyle.equals("JupiterBoard"))
		{
			board = new JupiterBoardStyle();
		}
		
		mancalaAPanel.add(mancalaAButton);
		mancalaBPanel.add(mancalaBButton);
		pitsPanel.add(pitButtons);
		
		buttonPanel.add(undoButton);
		buttonPanel.add(doneButton);
		
		frame.add(mancalaAPanel, BorderLayout.EAST);
		frame.add(mancalaBPanel, BorderLayout.WEST);
		frame.add(pitsPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
}
