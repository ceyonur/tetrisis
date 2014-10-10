package settings;

// TODO: Auto-generated Javadoc
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

	/**
	 * Default constructor for the BoardSize class. Sets the choice to medium.
	 */
	public BoardSize() {
		medium = true;
		small = false;
		large = false;
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
	}

	/**
	 * Gets the row according to the selected size.
	 *
	 * @return the row
	 */
	public int getRow() {
		if (isSmall()) {
			return 5;
		} else if (isMedium()) {
			return 10;
		} else {
			return 15;
		}
	}

	/**
	 * Gets the column according to the selected size.
	 *
	 * @return the column
	 */
	public int getColumn() {
		if (isSmall()) {
			return 10;
		} else if (isMedium()) {
			return 15;
		} else {
			return 20;
		}
	}

}
