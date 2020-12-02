import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



/**
 * 
 * @author Toan & Ye Sol
 *
 */
public class MancalaView extends JFrame {
	
	private final int PIT_SIZE = 6; 
	private BoardStyleManager board;
	private MancalaGameState gameState;
	private ArrayList<JButton> pitButtons;
	private final int ICON_HEIGHT = 50;
	private final int ICON_WIDTH = 50;
	/**
	 * 
	 * @param boardStyle
	 * @param stoneNumber
	 * @param width
	 * @param height
	 */
	public MancalaView(String boardStyle, int stoneNumber) {
		

		
		pitButtons = new ArrayList<JButton>();
		
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
				return ICON_WIDTH;
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				board.drawPit(g2);
				
			}

			@Override
			public int getIconHeight() {
				return ICON_HEIGHT;
			}
		};


		for(int i=0; i<PIT_SIZE; i++) {
			JLabel pitLabel = new JLabel(pitDrawings);
			pitLabel.setPreferredSize(new Dimension(50, 50));
			pitAPanel.add(pitLabel);
		}

		MancalaGameState.Pit[] pits = MancalaGameState.Pit.values();
		for(int i=0; i<pits.length/2-1;i++)
		{
			JLabel label = new JLabel(""+pits[i], SwingConstants.CENTER);
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
			pitLabel.setPreferredSize(new Dimension(50, 50));
			pitBPanel.add(pitLabel);
		}
		
		
		pitAPanel.setBackground(board.getColor());
		pitBPanel.setBackground(board.getColor());
		playerPanel.setOpaque(true);

		
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		JButton mancalaAButton = new JButton("A");
		mancalaAButton.setPreferredSize(new Dimension(50, 50));
		JButton mancalaBButton = new JButton("B");
		mancalaBButton.setPreferredSize(new Dimension(50, 50));
		
		
	
		
		mancalaAPanel.add(mancalaAButton);
		mancalaBPanel.add(mancalaBButton);

		

		playerPanel.add(pitBPanel, BorderLayout.NORTH);
		playerPanel.add(pitAPanel, BorderLayout.CENTER);
		playerPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/ICON_WIDTH;
				int y = e.getY()/ICON_HEIGHT;
				System.out.println(x + "," + y);
			}
		});
		

		
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
