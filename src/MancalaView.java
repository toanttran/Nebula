import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * MancalaView acts as the view for the application
 *
 */
public class MancalaView extends JFrame {
	
	private final int PIT_SIZE = 6; 
	private BoardStyleManager board;
	private MancalaGameState gameState;
	private final int ICON_HEIGHT = 50;
	private final int ICON_WIDTH = 50;
	/**
	 * 
	 * @param boardStyle - the board style chosen from combo box
	 * @param stoneNumber - the number of stones chosen from combo box
	 */
	public MancalaView(String boardStyle, int stoneNumber) {
		
		
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
		
		Icon mancalaDrawing = new Icon() {
			public int getIconWidth() {
				return 0;
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				board.drawMancala(g2);
				
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
			pitLabel.setLayout(new GridLayout(3, 3));
			pitLabel.setPreferredSize(new Dimension(50, 50));
			for(int j=0; j<stoneNumber;j++)
			{
				JLabel stoneLabel = new JLabel(stoneDrawing);
				stoneLabel.setPreferredSize(new Dimension(8,8));
				pitLabel.add(stoneLabel);
			}
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
			pitLabel.setLayout(new GridLayout(3, 3));
			pitLabel.setPreferredSize(new Dimension(50, 50));
			for(int j=0; j<stoneNumber;j++)
			{
				JLabel stoneLabel = new JLabel(stoneDrawing);
				stoneLabel.setPreferredSize(new Dimension(8,8));
				pitLabel.add(stoneLabel);
			}
			pitBPanel.add(pitLabel);
		}
		
		
		pitAPanel.setBackground(board.getColor());
		pitBPanel.setBackground(board.getColor());
		playerPanel.setOpaque(true);

		
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		undoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				gameState.undoLastMove();
			}
		});
		
		JLabel mancalaALabel = new JLabel(mancalaDrawing);
		mancalaALabel.setPreferredSize(new Dimension(50, 200));
		mancalaAPanel.add(mancalaALabel);
		
		JLabel mancalaBLabel = new JLabel(mancalaDrawing);
		mancalaBLabel.setPreferredSize(new Dimension(50, 200));
		mancalaBPanel.add(mancalaBLabel);
		

		playerPanel.add(pitBPanel, BorderLayout.NORTH);
		playerPanel.add(pitAPanel, BorderLayout.CENTER);
		playerPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/ICON_WIDTH;
				int y = e.getY()/ICON_HEIGHT;
				System.out.println(x + "," + y);
				MancalaGameState.Pit pitEnum = convertPitLocationToEnum(x,y);
				gameState.movePit(pitEnum);
				System.out.println("pitEnum: "+pitEnum);
				repaint();
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
	
	/**
	 * @param x - x coordinate of pit
	 * @param y - y coordinate of pit
	 * @return the pit enum associated with the pit on screen
	 */
	public MancalaGameState.Pit convertPitLocationToEnum(int x, int y)
	{
		MancalaGameState.Pit pit = null;
		if(y == 2)
		{
			pit = MancalaGameState.Pit.valueOf("A"+(x+1));
		}
		else if(y == 1)
		{
			pit = MancalaGameState.Pit.valueOf("B"+(6-x));
		}
		return pit;
		
	}
	
}
