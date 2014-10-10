package settings;

/**
 * The Class BoardSize. This class holds the selected board type. Also it returns the corresponding Row and Column size.
 * @author ceyonur
 */
public class BoardSize {

	/** The small. */
	private boolean small;
	
	/** The medium. */
	private boolean medium;
	
	/** The large. */
	private boolean large;
	
	private static int rowSize = 10;
	private static int columnSize = 15;

	/**
	 * Default constructor for the BoardSize class. Sets the choice to medium.
	 */
	public BoardSize() {
		setMedium();
	}

	/**
	 * Checks if the size is small.
	 *
	 * @return true, if the size is small
	 */
	public boolean isSmall() {
		return small;
	}

	/**
	 * Sets the size to small.
	 */
	public void setSmall() {
		small = true;
		medium = false;
		large = false;
		
		rowSize = 5;
		columnSize = 10;
	}

	/**
	 * Checks if the size is medium.
	 *
	 * @return true, if the size is medium
	 */
	public boolean isMedium() {
		return medium;
	}

	/**
	 * Sets the size to medium.
	 */
	public void setMedium() {
		medium = true;
		small = false;
		large = false;
		
		rowSize = 10;
		columnSize = 15;
	}

	/**
	 * Checks if the size is large.
	 *
	 * @return true, if the size is large
	 */
	public boolean isLarge() {
		return large;
	}

	/**
	 * Sets the size to large.
	 */
	public void setLarge() {
		large = true;
		small = false;
		medium = false;
		
		rowSize = 15;
		columnSize = 20;
	}

	/**
	 * Gets the row according to the selected size.
	 *
	 * @return The row size
	 */
	public static int getRow() {
		return rowSize;
	}

	/**
	 * Gets the column according to the selected size.
	 *
	 * @return The column size
	 */
	public static int getColumn() {
		return columnSize;
	}

}
