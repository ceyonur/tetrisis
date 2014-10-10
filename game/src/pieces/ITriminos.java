package pieces;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * This class corresponds to the I-Triminos shape.
 * @author bedirhancaldir
 */
public class ITriminos extends Triminos{

	/**
	 * The constructor of the class ITriminos. Sets the color, x and y coordinates of the ITriminos
	 * @param x The x-position of the upper-left corner of the ITriminos
	 * @param y The y-position of the upper-left corner of the ITriminos
	 * @param color The color of the ITriminos
	 */
	public ITriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for ITriminos
	}

	@Override
	/**
	 * This method orders the blocks of the ITriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i+1);
			currentBlock.move(0, currentBlock.getBlockSize() * i);
		}
	}
	
	@Override
	/**
	 * This method gives the rectangle covering the piece.
	 * @return The box covering to the piece's area
	 */
	public Rectangle boundingBox() {
		return new Rectangle(getX(), getY(), getBlockAt(1).getBlockSize()+1, NUMBER_OF_PIECES * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the ITriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}

}
