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
public class MancalaView extends JFrame implements ChangeListener{
	
	private final int PIT_SIZE = 6; 
	private BoardStyleManager board;
	private MancalaGameState gameState;
	private final int ICON_HEIGHT = 50;
	private final int ICON_WIDTH = 50;
	
	private int[] mancalaBoard;
	/**
	 * 
	 * @param boardStyle - the board style chosen from combo box
	 * @param stoneNumber - the number of stones chosen from combo box
	 */
	public MancalaView(String boardStyle, int stoneNumber, MancalaGameState data) {
		
		gameState = data;
		mancalaBoard = gameState.getMancalaBoardData();
		
		this.setTitle(boardStyle + " Style Mancala Game");
		
		this.setLayout(new BorderLayout());
		
		//Panels to layout
		JPanel mancalaAPanel = new JPanel();
		JPanel mancalaBPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JPanel playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(300,200));
		
		
		if(boardStyle.equals("EarthBoard"))
		{
			board = new EarthBoardStyle();
		}
		else if(boardStyle.equals("JupiterBoard"))
		{
			board = new JupiterBoardStyle();
		}
		
		//Buttons to layout
		JButton undoButton = new JButton("Undo");
		JButton doneButton = new JButton("Done");
		
		undoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				gameState.undoLastMove();
			}
		});
		
		doneButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				gameState.changePlayers();
			}
		});
		
		
		//add mouse listener to pits
		playerPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX()/ICON_WIDTH;
				int y = e.getY()/ICON_HEIGHT;
				System.out.println(x + "," + y);
				MancalaGameState.Pit pitEnum = convertPitLocationToEnum(x,y);
				gameState.movePit(pitEnum);
				//mancalaBoard = gameState.getMancalaBoardData();
				System.out.println("pitEnum: "+pitEnum);
				repaint();
			}
		});
		
		Icon mancalaDrawing = new mancalaIcon();
		
		//draw the side mancala pits and label
		JLabel mancalaALabel = new JLabel("A");
		mancalaALabel.setPreferredSize(new Dimension(10, 10));
		JLabel mancalaA = new JLabel(mancalaDrawing);
		mancalaA.setPreferredSize(new Dimension(50, 200));
		mancalaAPanel.add(mancalaA);
		mancalaAPanel.add(mancalaALabel);
		
		JLabel mancalaBLabel = new JLabel("B");
		mancalaBLabel.setPreferredSize(new Dimension(10, 10));
		JLabel mancalaB = new JLabel(mancalaDrawing);
		mancalaB.setPreferredSize(new Dimension(50, 200));
		mancalaBPanel.add(mancalaBLabel);
		mancalaBPanel.add(mancalaB);

		//add buttons to panel
		buttonPanel.add(undoButton);
		buttonPanel.add(doneButton);
		
		//add a and b pits in one pit
		playerPanel.add(new pitBPanel(), BorderLayout.NORTH);
		playerPanel.add(new pitAPanel(), BorderLayout.CENTER);
		playerPanel.setBackground(board.getColor());
		playerPanel.setOpaque(true);
		
		//add all the panels into the jframe
		this.add(mancalaAPanel, BorderLayout.EAST);
		this.add(mancalaBPanel, BorderLayout.WEST);
		this.add(playerPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private class pitIcon implements Icon{

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
		
	}
	
	private class mancalaIcon implements Icon{

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
	}
	
	private class stoneIcon implements Icon{

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
	}
	
	private class pitAPanel extends JPanel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			//JPanel pitAPanel = new JPanel();
			this.setLayout(new GridLayout(2,6));

			Icon pitDrawings = new pitIcon();
			Icon stoneDrawing = new stoneIcon();

			for(int i=0; i<PIT_SIZE; i++) {
				JLabel pitLabel = new JLabel(pitDrawings);
				pitLabel.setLayout(new GridLayout(3, 3));
				pitLabel.setPreferredSize(new Dimension(50, 50));
				for(int j=0; j<mancalaBoard[i];j++)
				{
					JLabel stoneLabel = new JLabel(stoneDrawing);
					stoneLabel.setPreferredSize(new Dimension(8,8));
					pitLabel.add(stoneLabel);
				}
				this.add(pitLabel);
			}
			
			MancalaGameState.Pit[] pits = MancalaGameState.Pit.values();
			for(int i=0; i<pits.length/2-1;i++)
			{
				JLabel label = new JLabel(""+pits[i], SwingConstants.CENTER);
				label.setPreferredSize(new Dimension(50, 5));
				this.add(label);
			}
			
		}
	}
	
	private class pitBPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g)
		{
			MancalaGameState.Pit[] pits = MancalaGameState.Pit.values();
			
			Icon pitDrawings = new pitIcon();
			Icon stoneDrawing = new stoneIcon();
			
			//JPanel pitBPanel = new JPanel();
			this.setLayout(new GridLayout(2,6));
			
			for(int i=pits.length-2; i>=pits.length/2;i--)
			{
				JLabel label = new JLabel(""+pits[i],SwingConstants.CENTER);
				label.setPreferredSize(new Dimension(50, 5));
				this.add(label);
			}

			for(int i=0; i<PIT_SIZE; i++) {
				JLabel pitLabel = new JLabel(pitDrawings);
				pitLabel.setLayout(new GridLayout(3, 3));
				pitLabel.setPreferredSize(new Dimension(50, 50));
				for(int j=0; j<mancalaBoard[i];j++)
				{
					JLabel stoneLabel = new JLabel(stoneDrawing);
					stoneLabel.setPreferredSize(new Dimension(8,8));
					pitLabel.add(stoneLabel);
				}
				this.add(pitLabel);
			}
			

		}
		
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

	@Override
	public void stateChanged(ChangeEvent e) {
		mancalaBoard = gameState.getMancalaBoardData();
		repaint();
	}
	
}
