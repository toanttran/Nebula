import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MancalaTester {
	
	public static void main(String[] args) {
		
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
