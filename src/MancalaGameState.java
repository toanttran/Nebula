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
	private boolean gameEnd;
	
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
			if(!isMancalaPit(i))
				mancalaBoard[i] = numOfStones;
			else
				mancalaBoard[i] = 0;
		}
		lastBoard = null;
		currentPlayer = null;
		gameEnd = false;
	}
	
	/**
	 * Takes the stones in a specified Pit of the Mancala board
	 * and adds one to each consecutive pit after the specified Pit
	 * (in counter-clockwise motion). If this is the first move in
	 * the game, then the player that owns the selected Pit gains
	 * sole control of the Mancala Board.
	 * 
	 * The returned Boolean value determines if the outcome after the
	 * move should give a free turn as per game rules.
	 * 
	 * Will do nothing if the player picked their opponent's Pit, or
	 * if the specified Pit is either empty or a Mancala Pit 
	 * of either player
	 * 
	 * @param pos The Pit position from which to move the stones
	 * @return true if the last stone in this move is on the player's
	 * Mancala Pit, or if the selected pit either is empty or
	 * is a Mancala Pit; else, false
	 */
	public boolean movePit(Pit pos) {
		// Do nothing if player chose a Mancala Pit
		// or if the game has already ended
		if(isMancalaPit(pos.getValue()) && gameEnd == true) {
			return true; // Should not consume a turn
		}
		
		// Sets the player if this is the first turn
		if(currentPlayer == null) {
			currentPlayer = pos.getPlayer();
		}
		
		// Check if the player picked their own Pit
		if(currentPlayer.equals(pos.getPlayer())) {
			int stonesTaken = mancalaBoard[pos.getValue()];
			
			// Do nothing if the selected pit has no stones
			if(stonesTaken <= 0) {
				return true;
			}
			lastBoard = mancalaBoard.clone();
		
			// Loops through the board until stones in hand are empty
			int i = pos.getValue() - 1;
			while(stonesTaken > 0) {
				i++;
				if(i >= mancalaBoard.length)
					i = 0;
				if(isNotOpponentPit(i, pos)) {
					mancalaBoard[i]++;
					stonesTaken--;
				}
			}
			
			// If last stone lands on empty Pit of the player, steal
			// all stones from that Pit and the Pit on the opposite side
			// and place those stones in the player's Mancala Pit
			if(mancalaBoard[i] == 0) {
				 // Always one stone on previously-empty Pit
				int capturedStones = 1;
				
				int oppPos = Pit.B_START.getValue() - 1 - i;
				capturedStones += mancalaBoard[oppPos];
				if(pos.getPlayer().equals("A")) {
					mancalaBoard[Pit.A_START.getValue()] = capturedStones;
				}
				else {
					mancalaBoard[Pit.B_START.getValue()] = capturedStones;
				}
				mancalaBoard[oppPos] = 0;
				mancalaBoard[i] = 0;
			}
			
			// Mutation of board is done, so notify any viewers
			notifyViewers();
			
			if(isGameFinished()) {
				gameEnd = true;
				return false;
			}
			
			// If last stone lands on player's Mancala pit, get a free turn
			if(isMancalaPit(i)) {
				return true;
			}
			return false;
		} // Pit chosen is the opponent's pit
		else return true;
	}
	
	// Helper method of movePit() that checks if one side
	// of the Mancala Board is empty, and place the remaining
	// stones to the Mancala Pit of the player with those stones.
	private boolean isGameFinished() {
		boolean gameOverA = true;
		int aStart = Pit.A1.getValue();
		int aEnd = Pit.A_START.getValue();
		for(int i = aStart; i < aEnd && gameOverA; i++) {
			if(mancalaBoard[i] != 0)
				gameOverA = false;
		}
		
		boolean gameOverB = true;
		int bStart = Pit.B1.getValue();
		int bEnd = Pit.B_START.getValue();
		for(int i = bStart; i < bEnd && gameOverB; i++) {
			if(mancalaBoard[i] != 0)
				gameOverB = false;
		}
		
		if(gameOverA || gameOverB)
			return true;
		else
			return false;
	}
	
	/**
	 * Gets the winner of the Mancala Board if the game has ended
	 * @return The String representation of the winning player.
	 */
	public String getGameWinner() {
		if(gameEnd)
			return currentPlayer;
		else
			return null;
	}
	
	/**
	 * Undos the last move in the Mancala Board, returning
	 * it to its previous state
	 */
	public void undoLastMove() {
		if(lastBoard != null && !gameEnd) {
			mancalaBoard = lastBoard;
			lastBoard = null;
			notifyViewers();
		}
	}
	
	/**
	 * Switches the current player's turn to the other player
	 * 
	 * Will do nothing if no player has made their turn yet.
	 */
	public void changePlayers() {
		if(currentPlayer != null && !gameEnd) {
			if(currentPlayer.equals("A"))
				currentPlayer = "B";
			else if(currentPlayer.equals("B"))
				currentPlayer = "A";
		}
	}
	
	/**
	 * Attaches a ChangeListener to this MancalaGameState
	 * @param c The ChangeListener to attach
	 */
	public void attach(ChangeListener c) {
		viewers.add(c);
	}
	
	// Calls stateChanged() for all viewers in this MancalaGameState
	private void notifyViewers() {
		for(ChangeListener cl : viewers) {
			cl.stateChanged(new ChangeEvent(this));
		}
	}
	
	// Checks if the int position represents a Mancala Pit of
	// either player
	// returns true if position is a Mancala pit, else false
	private boolean isMancalaPit(int i) {
		return (i == Pit.A_START.getValue() || i == Pit.B_START.getValue());
	}
	
	// Checks if the int position is the opponent's Mancala
	// pit.
	// returns true if position is the opponent's Mancala pit, else false
	private boolean isNotOpponentPit(int i, Pit playerPit) {
		return ((playerPit.getPlayer().equals("A") && i == Pit.B_START.getValue()) ||
				(playerPit.getPlayer().equals("B") && i == Pit.A_START.getValue()));
	}
	
	/**
	 * Pits in the Mancala Board, starting from A1 Pit and moving
	 * counter-clockwise to B_START Mancala Pit
	 * @author Sage
	 */
	// Can call from other classes using MancalaGameState.Pit
	public enum Pit{
		A1(0,"A"), A2(1,"A"), A3(2,"A"),
		A4(3,"A"), A5(4,"A"), A6(5, "A"),
		A_START(6,"A"),
		
		B1(7,"B"), B2(8,"B"), B3(9,"B"),
		B4(10,"B"), B5(11,"B"), B6(12,"B"),
		B_START(13,"B");
		
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
