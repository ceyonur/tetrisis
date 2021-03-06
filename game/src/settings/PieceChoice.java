package settings;

import java.io.Serializable;

/**
 * The Class PieceChoice. This class stores the choice of pieces.(Tetriminos or
 * triminos)
 * 
 * @author ceyonur
 */
public class PieceChoice implements Serializable{

	/** The choice. */
	private boolean triminos;
	
	/** The choice2. */
	private boolean tetriminos;

	/**
	 * Default constructor for the PieceChoice class. Sets the choice to Tetriminos
	 */
	public PieceChoice() {
		tetriminos = true;
		triminos = false;		
	}

	/**
	 * Sets the Tetriminos.
	 *
	 * @param choice the new tetriminos boolean
	 */
	public void setTetriminos(boolean choice) {
		tetriminos = choice;
	}

	/**
	 * Sets the  triminos.
	 *
	 * @param choice the new triminos boolean
	 */
	public void setTriminos(boolean choice) {
		triminos = choice;
	}

	
	/**
	 * Checks for triminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTriminos(){
		return triminos;
	}
	
	/**
	 * Checks for tetriminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTetriminos(){
		return tetriminos;
	}
	
	/**
	 * Checks for both tetriminos and triminos.	
	 *
	 * @return true, if successful
	 */
	public boolean hasBoth(){
		return hasTetriminos() && hasTriminos();
	}
	
	/**
	 * Sets the both tetriminos and triminos to true.
	 *
	 * @param 
	 */
	public void setBoth(){
		setTetriminos(true);
		setTriminos(true);
	}
	
	/**
	 * Gets the choice string.
	 *
	 * @return String the choice
	 */
	public String getChoice(){
		return "tetriminos: " + hasTetriminos() + " triminos: " + hasTriminos();
		
	}
}
