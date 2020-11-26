import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;


/**
 * 
 * @author toanmacbook
 *
 */
public class MancalaView extends JFrame {
	
	private final int PIT_SIZE = 6; 
	private BoardStyleManager board;
	
	/**
	 * 
	 * @param chooseBoardStyle
	 * @param chooseStoneNum
	 * @param width
	 * @param height
	 */
	public MancalaView(String boardStyle, int stoneNumber, int width, int height) {
		
		MancalaGameState initial = new MancalaGameState(stoneNumber);
		
		JFrame frame = new JFrame(boardStyle + " Style Mancala Game");
		
		frame.setLayout(new BorderLayout());
		
		//Panels to layout
		JPanel mancalaAPanel = new JPanel();
		JPanel mancalaBPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout( new BorderLayout());
		
		
		if(boardStyle.equals("EarthBoard"))
		{
			board = new EarthBoardStyle();
		}
		else if(boardStyle.equals("JupiterBoard"))
		{
			board = new JupiterBoardStyle();
		}
		
		JPanel pitAPanel = new JPanel();
		pitAPanel.setLayout(new GridLayout(2,6));

		for(int i=0; i<PIT_SIZE; i++) {
			JButton pitButtons = new JButton();
			pitButtons.setText(stoneNumber+""); //can take this out its just a label
			pitAPanel.add(pitButtons);
		}
		for(int i=1; i<=6; i++) {
			JLabel label = new JLabel("A"+i);
			pitAPanel.add(label);
		
		}
		
		JPanel pitBPanel = new JPanel();
		pitBPanel.setLayout(new GridLayout(2,6));
		
		for(int i=6; i>=1; i--) {
			JLabel label = new JLabel("B"+i);
			pitBPanel.add(label);
		}

		for(int i=0; i<PIT_SIZE; i++) {
			JButton pitButtons = new JButton();
			pitButtons.setText(stoneNumber+""); //can take this out its just a label
			pitBPanel.add(pitButtons);
		}
		
		pitAPanel.setBackground(board.getColor());
		pitBPanel.setBackground(board.getColor());
		playerPanel.setOpaque(true);

		
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		JButton mancalaAButton = new JButton("A");
		mancalaAButton.setHorizontalAlignment(SwingConstants.CENTER);
		JButton mancalaBButton = new JButton("B");
		mancalaBButton.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		mancalaAPanel.add(mancalaAButton);
		mancalaBPanel.add(mancalaBButton);
		

		playerPanel.add(pitBPanel, BorderLayout.NORTH);
		playerPanel.add(pitAPanel, BorderLayout.CENTER);
		

		
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
