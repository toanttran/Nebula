import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;



/**
 * 
 * @author toanmacbook
 *
 */
public class MancalaView extends JFrame implements MouseListener{
	
	private final int PIT_SIZE = 6; 
	private BoardStyleManager board;
	private MancalaGameState gameState;
	private ArrayList<JLabel> mancalaPits;
	
	/**
	 * 
	 * @param chooseBoardStyle
	 * @param chooseStoneNum
	 * @param width
	 * @param height
	 */
	public MancalaView(String boardStyle, int stoneNumber, int width, int height) {
		
		mancalaPits = new ArrayList<JLabel>();
		
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
		
		Icon pitDrawings = new Icon() {
			public int getIconWidth() {
				return 0;
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				board.drawPit(g2);
				
			}

			@Override
			public int getIconHeight() {
				return 0;
			}
		};
		
		Icon stoneDrawing = new Icon() {
			public int getIconWidth() {
				return 0;
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				board.drawStone(g2);
				
			}

			@Override
			public int getIconHeight() {
				return 0;
			}
		};



		for(int i=0; i<PIT_SIZE; i++) {
			JLabel pitLabel = new JLabel(pitDrawings);
			pitLabel.setPreferredSize(new Dimension(60, 60));
			pitAPanel.add(pitLabel);
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
			JLabel pitLabel = new JLabel(pitDrawings);
			pitLabel.setPreferredSize(new Dimension(60, 60));
			pitBPanel.add(pitLabel);
		}
		
		
		pitAPanel.setBackground(board.getColor());
		pitBPanel.setBackground(board.getColor());
		playerPanel.setOpaque(true);

		
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		JLabel mancalaALabel = new JLabel(pitDrawings);
		mancalaALabel.setPreferredSize(new Dimension(60, 60));
		mancalaAPanel.add(mancalaALabel);
		
		JLabel mancalaBLabel = new JLabel(pitDrawings);
		mancalaBLabel.setPreferredSize(new Dimension(60, 60));
		mancalaBPanel.add(mancalaBLabel);
		

		playerPanel.add(pitBPanel, BorderLayout.NORTH);
		playerPanel.add(pitAPanel, BorderLayout.CENTER);
		

		
		buttonPanel.add(undoButton);
		buttonPanel.add(doneButton);
		
		addMouseListener(this);
		
		frame.add(mancalaAPanel, BorderLayout.EAST);
		frame.add(mancalaBPanel, BorderLayout.WEST);
		frame.add(playerPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//want to check which label in the arraylist of labels is pressed? 
		JLabel labelPressed = (JLabel) e.getSource();		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
