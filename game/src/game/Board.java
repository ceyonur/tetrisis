package game;

/**
 * The class Board handles the game board of tetris
 * @author bedirhancaldir
 */
public class Board {
	private int[][] board; // The board represented as 2D integer matrix
	private final static int DEFAULT_ROW_SIZE = settings.BoardSize.getRow(); // The default row size
	private final static int DEFAULT_COLUMN_SIZE = settings.BoardSize.getColumn(); // The default column size

	/**
	 * The constructor of the class Board. Creates the matrix of the given dimensions
	 * @param row The row number of the matrix
	 * @param column The column number of the matrix
	 */
	public Board(int row, int column){
		board = new int[row][column];
	}
	
	/**
	 * The default constructor of the class. Sets the matrix to the default sizes (defined in settings.BoardSize class)
	 */
	public Board(){
		this(DEFAULT_ROW_SIZE, DEFAULT_COLUMN_SIZE);
	}
	
	/**
	 * This method returns the row size of the board.
	 * @return The row number of the game board.
	 */
	public int getRowSize(){
		return board.length;
	}
	
	/**
	 * This method returns the column size of the board.
	 * @return The column number of the game board.
	 */
	public int getColumnSize(){
		return board[0].length;
	}
	
	/**
	 * This method updates the board matrix
	 */
	public void updateBoard(){
		
	}
}
