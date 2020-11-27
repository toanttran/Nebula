import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;



/**
 * 
 * @author toanmacbook
 *
 */
public class MancalaView extends JFrame{
	
	private final int PIT_SIZE = 6; 
	private BoardStyleManager board;
	private MancalaGameState gameState;
	private ArrayList<JButton> pitButtons;
	
	/**
	 * 
	 * @param chooseBoardStyle
	 * @param chooseStoneNum
	 * @param width
	 * @param height
	 */
	public MancalaView(String boardStyle, int stoneNumber, int width, int height) {
		
		gameState = new MancalaGameState(stoneNumber);
		
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
			JButton button = new JButton();
			pitButtons.add(button);
			button.setPreferredSize(new Dimension(50, 50));
			button.setText(stoneNumber+""); //can take this out its just a label
			pitAPanel.add(button);
		}

		MancalaGameState.Pit[] pits = MancalaGameState.Pit.values();
		for(int i=0; i<pits.length/2-1;i++)
		{
			JLabel label = new JLabel(""+pits[i],SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(50, 5));
			pitAPanel.add(label);
		}
		
		JPanel pitBPanel = new JPanel();
		pitBPanel.setLayout(new GridLayout(2,6));
		
		for(int i=pits.length-2; i>=pits.length/2;i--)
		{
			JLabel label = new JLabel(""+pits[i],SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(50, 5));
			pitBPanel.add(label);
		}

		for(int i=0; i<PIT_SIZE; i++) {
			JButton button = new JButton();
			pitButtons.add(button);
			button.setPreferredSize(new Dimension(50, 50));
			button.setText(stoneNumber+""); //can take this out its just a label
			pitBPanel.add(button);
		}
		
		
		pitAPanel.setBackground(board.getColor());
		pitBPanel.setBackground(board.getColor());
		playerPanel.setOpaque(true);

		
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		JButton mancalaAButton = new JButton("A");
		mancalaAButton.setPreferredSize(new Dimension(50, 110));
		JButton mancalaBButton = new JButton("B");
		mancalaBButton.setPreferredSize(new Dimension(50, 110));
		
		
	
		
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
