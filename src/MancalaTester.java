import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Acts as the initial screen for the whole application 
 * @author Ye Sol, Toan
 *
 */
public class MancalaTester {
	
	public static void main(String[] args) {
		
		
		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 200;
		JFrame initialScreen = new JFrame("Game Setup");
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel direction = new JLabel("Select Board Style and Number of Stones");
		JButton startGame = new JButton("Start Game");

		
		Font f = direction.getFont();
		// bold
		direction.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
		
		String[] stoneChoices = { "3", "4"};
		String[] boardChoices = { "EarthBoard", "JupiterBoard"};

		JComboBox chooseStoneNum = new JComboBox(stoneChoices);
		JComboBox chooseBoardStyle = new JComboBox(boardChoices);
		
		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String numberOfStones = chooseStoneNum.getSelectedItem().toString();
				String boardStyle = chooseBoardStyle.getSelectedItem().toString();
				
				JFrame frame = new MancalaView(boardStyle, numberOfStones, FRAME_WIDTH, FRAME_HEIGHT);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//need to close initialScreen after this button is activated.
				initialScreen.dispose();
			}
			
		});
		
		panel.add(direction);
		buttonPanel.add(chooseBoardStyle);
		buttonPanel.add(chooseStoneNum);
		buttonPanel.add(startGame);
		
		initialScreen.setLayout(new BorderLayout());
		initialScreen.add(panel, BorderLayout.NORTH);
		initialScreen.add(buttonPanel,BorderLayout.CENTER);
		initialScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialScreen.pack();
		initialScreen.setVisible(true);
		
		
	}

}
