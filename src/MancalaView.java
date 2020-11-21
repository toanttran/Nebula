import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
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
	
	static final int PIT_SIZE = 6; 
	
	/**
	 * 
	 * @param chooseBoardStyle
	 * @param chooseStoneNum
	 * @param width
	 * @param height
	 */
	public MancalaView(String boardStyle, String stoneNumber, int width, int height) {
		
		JFrame frame = new JFrame("Mancala Game");
		frame.setLayout(new BorderLayout());
		
		//Panels to layout
		JPanel mancalaAPanel = new JPanel();
		JPanel mancalaBPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JPanel playerPanel = new JPanel();
		
		
		MancalaPits pit;
		JPanel pitAPanel = new JPanel();
		JButton pitButtons = new JButton("pit");
		pitAPanel.setLayout(new GridLayout(1,6));
		for(int i=0; i<PIT_SIZE; i++) {
			pitAPanel.add(pitButtons);
		}
		


		JPanel pitBPanel = new JPanel();
		pitBPanel.setLayout(new GridLayout(1,6));
		for(int i=0; i<PIT_SIZE; i++) {
			pitBPanel.add(pitButtons);
		}
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		JButton mancalaAButton = new JButton("A");
		JButton mancalaBButton = new JButton("B");
		
		BoardStyleManager board;
		
		if(boardStyle.equals("EarthBoard"))
		{
			board = new EarthBoardStyle();
		}
		else if(boardStyle.equals("JupiterBoard"))
		{
			board = new JupiterBoardStyle();
		}
		
		mancalaAPanel.add(mancalaAButton);
		mancalaBPanel.add(mancalaBButton);

		playerPanel.add(pitAPanel);
		playerPanel.add(pitBPanel);
		
		buttonPanel.add(undoButton);
		buttonPanel.add(doneButton);
		
		frame.add(mancalaAPanel, BorderLayout.EAST);
		frame.add(mancalaBPanel, BorderLayout.WEST);
		frame.add(playerPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
}
