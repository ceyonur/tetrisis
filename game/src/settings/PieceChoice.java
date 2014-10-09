package settings;

import pieces.Piece;
import pieces.Tetriminos;

// TODO: Auto-generated Javadoc
/**
 * The Class PieceChoice. This class stores the choice of pieces.(Tetriminos or
 * triminos)
 * 
 * @author ceyonur
 */
public class PieceChoice {

	/** The choice. */
	private Piece choice1;
	
	/** The choice2. */
	private Piece choice2;

	/**
	 * Default constructor for the PieceChoice class. Sets the choice to Tetriminos
	 */
	public PieceChoice() {
		setPieceChoice(new Tetriminos());
	}

	/**
	 * Sets the choice. Checks whether the given Piece is Tetriminos or Triminos, otherwise sets the choice to Tetriminos.
	 *
	 * @param choice the new choice
	 */
	public void setPieceChoice(Piece choice) {
		if (choice.isTetriminos() || choice.isTriminos()) {
			this.choice1 = choice;
			this.choice2 = null;
		} else {
			this.choice1 = new Tetriminos();
		}
	}

	/**
	 * Sets the choice.
	 *
	 * @param choice1 the choice1
	 * @param choice2 the choice2
	 */
	public void setPieceChoice(Piece choice1, Piece choice2) {
		setPieceChoice(choice1);
		if(choice2.isTetriminos() || choice2.isTriminos()){			
			this.choice2 = choice2;
		}
		else {			
			this.choice2 = null;
		}
	}

	
	/**
	 * Checks for triminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTriminos(){
		return choice1.isTriminos() || choice2.isTriminos();
	}
	
	/**
	 * Checks for tetriminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTetriminos(){
		return choice1.isTetriminos() || choice2.isTetriminos();
	}
	
	/**
	 * Checks for both tetriminos and triminos.	
	 *
	 * @return true, if successful
	 */
	public boolean hasBoth(){
		return hasTetriminos() && hasTriminos();
	}
}
