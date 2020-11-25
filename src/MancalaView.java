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
		
		JPanel pitAPanel = new JPanel();


		for(int i=0; i<PIT_SIZE; i++) {
			JButton pitButtons = new JButton();
			pitButtons.setText(stoneNumber+""); //can take this out its just a label
			pitAPanel.add(pitButtons);
		}
		
		JPanel pitBPanel = new JPanel();

		for(int i=0; i<PIT_SIZE; i++) {
			JButton pitButtons = new JButton();
			pitButtons.setText(stoneNumber+""); //can take this out its just a label
			pitBPanel.add(pitButtons);
			
		}
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		JButton mancalaAButton = new JButton("A");
		mancalaAButton.setHorizontalAlignment(SwingConstants.CENTER);
		JButton mancalaBButton = new JButton("B");
		mancalaBButton.setHorizontalAlignment(SwingConstants.CENTER);
		

		
		if(boardStyle.equals("EarthBoard"))
		{
			BoardStyleManager board = new EarthBoardStyle();
		}
		else if(boardStyle.equals("JupiterBoard"))
		{
			BoardStyleManager board = new JupiterBoardStyle();
		}
		
		mancalaAPanel.add(mancalaAButton);
		mancalaBPanel.add(mancalaBButton);

		playerPanel.add(pitAPanel, BorderLayout.NORTH);
		playerPanel.add(pitBPanel, BorderLayout.CENTER);
		
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
