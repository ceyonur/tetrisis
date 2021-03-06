package settings;

import java.io.Serializable;

/**
 * The Class BoardSize. This class holds the selected board type. Also it returns the corresponding Row and Column size.
 * @author ceyonur
 */
public class BoardSize implements Serializable{

	/** The small. */
	private boolean small;
	
	/** The medium. */
	private boolean medium;
	
	/** The large. */
	private boolean large;
	
	private int rowSize = 15; // default row size
	private int columnSize = 10; // default column size

	/**
	 * Default constructor for the BoardSize class. Sets the choice to medium.
	 */
	public BoardSize() {
		setSmall();
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
		
		rowSize = 15;
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
		
		rowSize = 18;
		columnSize = 12;
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
		
		rowSize = 24;
		columnSize = 16;
	}

	/**
	 * Gets the row according to the selected size.
	 *
	 * @return The row size
	 */
	public int getRow() {
		return rowSize;
	}

	/**
	 * Gets the column according to the selected size.
	 *
	 * @return The column size
	 */
	public int getColumn() {
		return columnSize;
	}
	
	/**
	 * Gets the choice string.
	 *
	 * @return String the choice
	 */
	public String getChoice(){
		if(isSmall()){
			return "small";
		}
		
		else if(isLarge()){
			return "large";
		}		
		else {
			return "medium";
		}
		
	}

}
