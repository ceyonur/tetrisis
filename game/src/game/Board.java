package game;

import gui.AudioPlayers;
import gui.PlayGUI;
import gui.BoardPanel;

/**
 * The class Board handles the game board of tetris
 * 
 * @author bedirhancaldir
 */
public class Board {
	private int[][] board; // The board represented as 2D integer matrix

	private int rowSize; // The field holding the row size of the board
	private int actualRowSize; // This gives the real row size which is rowSize+4
	private int columnSize; // The field holding the column size of the board
	private boolean emptyness; // True if the board is empty

	private Engine callerEngine; // The Engine object generating this Board object
	private PlayGUI playGUI; // The PlayGUI frame object generating this Board object
	private boolean firstKill = true; // FirstBlood indicator
	private AudioPlayers audioPlayers;

	/**
	 * The constructor of the class Board. Creates the matrix of the given dimensions
	 * @param row The row number of the matrix
	 * @param column The column number of the matrix
	 */
	public Board(int row, int column, Engine engine) {
		initializeBoard(row, column, engine);
	}
	
	private void initializeBoard(int row, int column, Engine engine){
		board = new int[row + 4][column];
		rowSize = row;
		actualRowSize = rowSize + 4; // +4 is becaue that there are actually 4 more rows to make the pieces coming from the above of the top wall
		columnSize = column;
		emptyness = true;
		callerEngine = engine;
		audioPlayers = engine.getAudioPlayers();
	}

	/**
	 * This method returns the row size of the board in terms of the block
	 * number fit inside
	 * 
	 * @return The row number of the game board.
	 */
	public int getRowSize() {
		return rowSize;
	}

	/**
	 * This method returns the column size of the board in terms of the block
	 * number fit inside
	 * 
	 * @return The column number of the game board.
	 */
	public int getColumnSize() {
		return columnSize;
	}

	/**
	 * This method returns the row size of the board in terms of pixel
	 * 
	 * @return The row length of the game board.
	 */
	public int getRowLength() {
		return (rowSize - 1) * pieces.Block.SIZE;
	}

	/**
	 * This method returns the column size of the board in terms of pixel
	 * 
	 * @return The column length of the game board.
	 */
	public int getColumnLength() {
		return columnSize * pieces.Block.SIZE;
	}

	/**
	 * This method gives the information of including at least a piece
	 * @return true if it the board is empty; false otherwise
	 */
	public boolean isEmpty() {
		return emptyness;
	}

	/**
	 * This method updates the board matrix at the given piece's location with 1
	 */
	public void updateBoard(int[][] locations) {
		if (emptyness) // If the emptyness is true, it must be converted to false since a piece has been put
			emptyness = false;
		for (int i = 0; i < locations.length; i++) {
			board[locations[i][1] + 4][locations[i][0]] = 1; // The given locations for the piece's blocks is set as 1 (not empty; filled)
		}
		checkLinesForCompletion(); // After the update, the checking of the completed lines must be done
	}
	
	/**
	 * This method provides the current JFrame creating this Board object.
	 * @param playGUI
	 */
	public void setCurrentPlayGUI(PlayGUI playGUI) {
		this.playGUI = playGUI;
	}

	/**
	 * This method checks whether the below of the piece is empty or not.
	 * @param locations The locations of the block as (x,y) in a 2D array
	 * @return Returns true if the block can go further or false if vice versa
	 */
	public boolean checkCollisionsToGoBelow(int[][] locations) {
		for (int i = 0; i < locations.length; i++) {
			if (locations[i][1] < board.length - 5) {
				if (board[locations[i][1] + 5][locations[i][0]] != 0)
					return false;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks whether the right of the piece is empty or not.
	 * @param locations The locations of the block as (x,y) in a 2D array
	 * @return Returns true if the block can go further; false otherwise
	 */
	public boolean checkCollisionsToGoRight(int[][] locations) {
		for (int i = 0; i < locations.length; i++) {
			if (locations[i][0] > 0) {
				if (locations[i][0] < board[0].length - 1) {
					if (board[locations[i][1] + 4][locations[i][0] + 1] != 0)
						return false;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method checks whether the left of the piece is empty or not.
	 * @param locations The locations of the block as (x,y) in a 2D array
	 * @return Returns true if the block can go further; false otherwise
	 */
	public boolean checkCollisionsToGoLeft(int[][] locations) {
		for (int i = 0; i < locations.length; i++) {
			if (locations[i][0] < board[0].length - 1) {
				if (locations[i][0] > 0) {
					if (board[locations[i][1] + 4][locations[i][0] - 1] != 0)
						return false;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method checks whether the rotation of the piece causes any confliction or not.
	 * @param locations The locations of the block when it is rotated as (x,y) in a 2D array
	 * @return "CONTINUE" -> No confliction, continue; "NOROTATE" -> Rotate is impossible; "FIX" -> move to an appropriate location, then rotate
	 */
	public String checkCollisionsWhenRotating(int[][] locations) {
		for (int i = 0; i < locations.length; i++)
			locations[i][0]++;

		boolean leftConfliction = checkCollisionsToGoLeft(locations);

		for (int i = 0; i < locations.length; i++)
			locations[i][0] -= 2;

		boolean rightConfliction = checkCollisionsToGoRight(locations);

		if (leftConfliction == true && rightConfliction == true)
			return "CONTINUE";
		else if (leftConfliction == false && rightConfliction == false)
			return "NOROTATE";
		else
			return "FIX";
	}

	/**
	 * This method checks all of the rows of the matrix to determine whether a
	 * line has been completed or not. If a line is determined as completed,
	 * then the removeCompletedLine method is called to remove that line
	 * completely.
	 */
	public void checkLinesForCompletion() {
		int filledSpaceCounter;
		int deletedLineCounter = 0;
		for (int i = 0; i < actualRowSize; i++) {
			filledSpaceCounter = 0;
			for (int j = 0; j < columnSize; j++) {
				if (board[i][j] != 0)
					++filledSpaceCounter;
			}
			if (filledSpaceCounter == columnSize) {
				removeCompletedLine(i);
				deletedLineCounter++;
			}
		}
		if (deletedLineCounter != 0) {
			callerEngine.increaseScore(deletedLineCounter);
			audioPlayers.playEffects(true, AudioPlayers.JUCKJUCK);
			audioPlayers.playDotaEffects(true, deletedLineCounter, firstKill);
			
			if(firstKill){
				firstKill = false;
			}
		}
	}

	/**
	 * This method takes a row number and deletes that line completely and shifts the upper lines to their one row below one by one.
	 * @param deletedRow The row that will be deleted.
	 */
	private void removeCompletedLine(int deletedRow) {
		for (int i = deletedRow - 1; i >= 0; i--) {
			for (int j = 0; j < columnSize; j++) {
				board[i + 1][j] = board[i][j];
			}
		}
		callerEngine.eliminatedLine(deletedRow);
	}

	/**
	 * This method determines whether the game is over or not.
	 * @return True if the game is over, false if the game can continue
	 */
	public boolean isGameOver() {
		for (int i = 0; i < board[0].length; i++) {
			if (board[5][i] != 0)
				return true;
		}
		return false;
	}
}
