import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Model that represents a physical Mancala Board, with Pits
 * and stones.
 * @author Sage
 *
 */
public class MancalaGameState {
	private int[] mancalaBoard;
	private int[] lastBoard;
	private String currentPlayer;
	private boolean gameEnd;
	private boolean canMakeTurn;
	
	private ArrayList<ChangeListener> viewers;
	
	public static final int BOARD_SIZE = 14;
	
	/**
	 * Creates an empty MancalaGameState with a specified
	 * number of stones already placed in all non-Mancala
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
		lastBoard		= null;
		currentPlayer	= null;
		gameEnd			= false;
		canMakeTurn		= true;
		viewers 		= new ArrayList<ChangeListener>();
	}
	
	/**
	 * Gets the number of stones in a specified Pit position
	 * @param pos The Pit to check the number of stones
	 * @return The number of stones in the given Pit
	 */
	public int getNumOfStones(Pit pos) {
		return mancalaBoard[pos.getValue()];
	}
	
	/**
	 * Gets the number of stones in a specified index of the Mancala
	 * Board.
	 * @param i The index to check for stones
	 * @return The number of stones in the given index
	 */
	public int getNumOfStones(int i) {
		return mancalaBoard[i];
	}
	
	/**
	 * Gets the winner of the Mancala Board if the game has ended
	 * @return The String representation of the winning player,
	 * "TIE" if both players have same number of stones, or null
	 * if game has not yet ended.
	 */
	public String getGameWinner() {
		if(gameEnd) {
			int mancalaA = mancalaBoard[Pit.A_START.getValue()];
			int mancalaB = mancalaBoard[Pit.B_START.getValue()];
			if(mancalaA > mancalaB) {
				return "A";
			}
			else if(mancalaB > mancalaA) {
				return "B";
			}
			else
				return "TIE";
		}
		else
			return null;
	}
	
	/**
	 * Gets the Mancala Board as an Integer Array.
	 * @return the whole Mancala Board data set
	 */
	public int[] getMancalaBoardData()
	{
		return mancalaBoard;
	}
	
	/**
	 * Checks if the current player can make a turn and select a pit.
	 * @return true if the current player can make a turn, else false
	 */
	public boolean canPlayerMakeTurn() {
		return canMakeTurn;
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
	public void movePit(Pit pos) {
		// Do nothing if player chose a Mancala Pit
		// or if the game has already ended
		if(	canMakeTurn == false || pos == null 
			|| isMancalaPit(pos.getValue()) || gameEnd == true) {
			return;
		}
		
		// First turn
		if(currentPlayer == null) {
			currentPlayer = pos.getPlayer();
		}
		
		// Player's own Pit is picked
		if(currentPlayer.equals(pos.getPlayer())) {
			int stonesTaken = mancalaBoard[pos.getValue()];
			
			// Do nothing if the selected pit has no stones
			if(stonesTaken <= 0) {
				return;
			}
			
			lastBoard = mancalaBoard.clone();
		
			// Takes away all stones in selected Pit and
			// loops through the board until stones in hand are empty
			int i = pos.getValue();
			mancalaBoard[i] = 0;
			while(stonesTaken > 0) {
				i++;
				if(i >= mancalaBoard.length)
					i = 0;
				
				if(!isOpponentMancalaPit(i, pos)) {
					mancalaBoard[i]++;
					stonesTaken--;
				}
			}
			
			// If last stone lands on empty non-Mancala Pit of the player,
			// steal all stones from that Pit and the Pit on the opposite
			//side and place those stones in the player's Mancala Pit.
			// Note: Always one stone on previously-empty Pit
			if( mancalaBoard[i] == 1 &&
				currentPlayer.equals(pos.getPlayer()) &&
				!isMancalaPit(i))
			{
				int oppPos			= Pit.B_START.getValue() - 1 - i;
				int capturedStones	= mancalaBoard[oppPos] + 1;
				if(pos.getPlayer().equals("A")) {
					mancalaBoard[Pit.A_START.getValue()] += capturedStones;
				}
				else {
					mancalaBoard[Pit.B_START.getValue()] += capturedStones;
				}
				mancalaBoard[oppPos]	= 0;
				mancalaBoard[i]			= 0;
			}
			
			// If last stone lands on player's Mancala pit, get a free turn
			if(isMancalaPit(i))
				canMakeTurn = true;
			else
				canMakeTurn = false;
			
			if(isGameFinished()) {
				gameEnd		= true;
				canMakeTurn = false;
			}
			
			// Mutation of board is done, so notify any viewers
			notifyViewers();
		}
	}
	
	/**
	 * Undos the last move in the Mancala Board, returning
	 * it to its previous state
	 */
	public void undoLastMove() {
		if(lastBoard != null && !gameEnd) {
			mancalaBoard = lastBoard;
			lastBoard = null;
			canMakeTurn = true;
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
			canMakeTurn = true;
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
	
	// Helper method of movePit() that checks if one side
	// of the Mancala Board is empty, and place the remaining
	// stones to the Mancala Pit of the player with those stones.
	private boolean isGameFinished() {
		// Assume A has no stones left in their side
		boolean gameOverA	= true;
		int aStart			= Pit.A1.getValue();
		int aEnd			= Pit.A_START.getValue();
		for(int i = aStart; i < aEnd && gameOverA; i++) {
			if(mancalaBoard[i] != 0) // Assumption is false
				gameOverA = false;
		}
		
		// Assume B has no stones left in their side
		boolean gameOverB	= true;
		int bStart			= Pit.B1.getValue();
		int bEnd			= Pit.B_START.getValue();
		for(int i = bStart; i < bEnd && gameOverB; i++) {
			if(mancalaBoard[i] != 0) // Assumption is false
				gameOverB = false;
		}
		
		// If either A or B run out of stones in their side, the
		// opposite player with remaining stones captures them to 
		// their own MancalaPit.
		if(gameOverA) {
			int remainingStones = 0;
			for(int i = bStart; i < bEnd; i++) {
				remainingStones += mancalaBoard[i];
				mancalaBoard[i] = 0;
			}
			mancalaBoard[Pit.B_START.getValue()] += remainingStones;
			return true;
		}
		else if(gameOverB) {
			int remainingStones = 0;
			for(int i = aStart; i < aEnd; i++) {
				remainingStones += mancalaBoard[i];
				mancalaBoard[i] = 0;
			}
			mancalaBoard[Pit.A_START.getValue()] += remainingStones;
			return true;
		}
		else
			return false;
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
	private boolean isOpponentMancalaPit(int i, Pit playerPit) {
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
