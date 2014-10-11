package game;

/**
 * The class Board handles the game board of tetris
 * @author bedirhancaldir
 */
public class Board {
	private int[][] board; // The board represented as 2D integer matrix

	private int rowSize; // The field holding the row size of the board
	private int columnSize; // The field holding the column size of the board
	
	private Engine callerEngine;

	/**
	 * The constructor of the class Board. Creates the matrix of the given dimensions
	 * @param row The row number of the matrix
	 * @param column The column number of the matrix
	 */
	public Board(int row, int column, Engine engine){
		board = new int[row][column];
		rowSize = row;
		columnSize = column;
		
		callerEngine = engine;
	}
	
	/**
	 * This method returns the row size of the board in terms of the block number fit inside
	 * @return The row number of the game board.
	 */
	public int getRowSize(){
		return rowSize;
	}

	/**
	 * This method returns the column size of the board in terms of the block number fit inside
	 * @return The column number of the game board.
	 */
	public int getColumnSize(){
		return columnSize;
	}

	/**
	 * This method returns the row size of the board in terms of pixel
	 * @return The row length of the game board.
	 */
	public int getRowLength(){
		return rowSize * pieces.Block.SIZE;
	}

	/**
	 * This method returns the column size of the board in terms of pixel
	 * @return The column length of the game board.
	 */
	public int getColumnLength(){
		return columnSize * pieces.Block.SIZE;
	}

	/**
	 * This method updates the board matrix with the given piece's location and color
	 * Colors -> 1: BLUE, 2: CYAN. 3: DARK_GRAY, 4: GREEN, 5: MAGENTA, 6: ORANGE, 7: PINK, 8:ERD, 9: YELLOW
	 */
	public void updateBoard(int[][] locations, int color){
		for (int i=0; i<locations.length; i++){
			board[locations[i][1]][locations[i][0]] = color;
		}
		checkLinesForCompletion();
	}

	/**
	 * This method checks whether the below of the piece is empty or not.
	 * @param locations Returns true if the block can go further or false if vice versa
	 * @return
	 */
	public boolean checkCollisionsToGoBelow(int[][] locations){
		for (int i=0; i<locations.length; i++){
			if (locations[i][1] < board.length - 1){
				if (board[locations[i][1] + 1][locations[i][0]] != 0)
					return false;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method checks whether the right of the piece is empty or not.
	 * @param locations Returns true if the block can go further or false if vice versa
	 * @return
	 */
	public boolean checkCollisionsToGoRight(int[][] locations){
		for (int i=0; i<locations.length; i++){
			if (locations[i][0] < board[0].length - 1){
				if (board[locations[i][1]][locations[i][0] + 1] != 0)
					return false;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method checks whether the left of the piece is empty or not.
	 * @param locations Returns true if the block can go further or false if vice versa
	 * @return
	 */
	public boolean checkCollisionsToGoLeft(int[][] locations){
		for (int i=0; i<locations.length; i++){
			if (locations[i][0] > 0){
				if (board[locations[i][1]][locations[i][0] - 1] != 0)
					return false;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks all of the rows of the matrix to determine whether a line has been completed
	 * or not. If a line is determined as completed, then the removeCompletedLine method is called to
	 * to remove the line completely.
	 */
	public void checkLinesForCompletion(){
		int filledSpaceCounter;
		for (int i=0; i<rowSize; i++){
			filledSpaceCounter = 0;
			for (int j=0; j<columnSize; j++){
				if (board[i][j] != 0)
					++filledSpaceCounter;
			}
			if (filledSpaceCounter == columnSize)
				removeCompletedLine(i);
		}
	}

	/**
	 * This method takes a row number and deletes that line completely and shifts the upper lines
	 * to their one row below one by one.
	 * @param deletedRow The row that will be deleted.
	 */
	private void removeCompletedLine(int deletedRow){
		for (int i=deletedRow-1; i>=0; i--){
			for (int j=0; j<columnSize; j++){
				board[i+1][j] = board[i][j];
			}
		}
		callerEngine.eliminatedLine(deletedRow);
	}
}
