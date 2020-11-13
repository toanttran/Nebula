import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Model for the Mancala Game
 * @author Sage
 *
 */
public class MancalaGameState {
	private int[] mancalaBoard;
	private int[] lastBoard;
	private String currentPlayer;
	
	private ArrayList<ChangeListener> viewers;
	
	public static final int BOARD_SIZE = 14;
	
	/**
	 * Creates an empty MancalaGameState with a specified
	 * number of stones already placed in all non-starting
	 * pits
	 * @param numOfStones the initial number of stones to place
	 * in each pit
	 */
	public MancalaGameState(int numOfStones) {
		mancalaBoard = new int[BOARD_SIZE];
		for(int i = 0; i < BOARD_SIZE; i++) {
			if(isNotPlayerPit(i))
				mancalaBoard[i] = numOfStones;
			else
				mancalaBoard[i] = 0;
		}
		lastBoard = null;
	}
	
	/**
	 * Takes the stones in a specified Pit of the Mancala board
	 * and adds one to each consecutive pit after the specified Pit
	 * (in counter-clockwise motion).
	 * @param pos The Pit position from which to move the stones
	 */
	public void movePit(Pit pos) {
		if(isNotPlayerPit(pos.getValue())) {
			int stonesTaken = mancalaBoard[pos.getValue()];
			currentPlayer = pos.getPlayer();
			
			lastBoard = mancalaBoard.clone();
			
			// Loops through the board until stones in hand are empty
			int i = pos.getValue();
			while(stonesTaken > 0) {
				if(i >= mancalaBoard.length)
					i = 0;
				if(isNotOpponentPit(i, pos)) {
					mancalaBoard[i]++;
					stonesTaken--;
				}
				i++;
			}
		}
		notifyViewers();
	}
	
	/**
	 * Undos the last move in the Mancala Board, returning
	 * it to its previous state
	 */
	public void undoLastMove() {
		if(lastBoard != null) {
			mancalaBoard = lastBoard;
			lastBoard = null;
			notifyViewers();
		}
	}
	
	/**
	 * Switches the current player's turn to the other player
	 */
	public void changePlayers() {
		if(currentPlayer.equals("A"))
			currentPlayer = "B";
		else if(currentPlayer.equals("B"))
			currentPlayer = "A";
	}
	
	/**
	 * Attaches a ChangeListener to this MancalaGameState
	 * @param c The ChangeListener to attach
	 */
	public void attach(ChangeListener c) {
		viewers.add(c);
	}
	
	private void notifyViewers() {
		for(ChangeListener cl : viewers) {
			cl.stateChanged(new ChangeEvent(this));
		}
	}
	
	// Checks if the int position is not the current player's
	// Mancala pit
	private boolean isNotPlayerPit(int i) {
		return (i != Pit.A_START.getValue() || i != Pit.B_START.getValue());
	}
	
	// Checks if the int position is not the opponent's Mancala
	// pit.
	private boolean isNotOpponentPit(int i, Pit playerPit) {
		return ((playerPit.getPlayer().equals("A") && i != Pit.B_START.getValue()) ||
				(playerPit.getPlayer().equals("B") && i != Pit.A_START.getValue()));
	}
	
	/**
	 * Pits in the Mancala Board
	 * @author Sage
	 */
	// Can call from other classes using MancalaGameState.Pit
	public enum Pit{
		A_START(0,"A"),
		A1(1,"A"), A2(2,"A"), A3(3,"A"),
		A4(4,"A"), A5(5,"A"), A6(6, "A"),
		
		B_START(7,"B"),
		B1(8,"B"), B2(9,"B"), B3(10,"B"),
		B4(11,"B"), B5(12,"B"), B6(13,"B");
		
		private int value;
		private String player;
		
		private Pit(int value, String player) {
			this.value = value;
			this.player = player;
		}
		
		/**
		 * Gets the value of this Pit as an int
		 * @return the int value corresponding to this Pit
		 */
		public int getValue() 
		{ return value; }
		
		/**
		 * Gets the player represented by this Pit as a String
		 * @return the player represented by this Pit
		 */
		public String getPlayer() 
		{ return player; }
	}
	
}
