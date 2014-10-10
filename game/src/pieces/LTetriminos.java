package pieces;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * This class corresponds to the L-Tetriminos shape.
 * @author bedirhancaldir
 */
public class LTetriminos extends Tetriminos{

	/**
	 * The constructor of the class LTetriminos. Sets the color, x and y coordinates of the LTetriminos
	 * @param x The x-position of the upper-left corner of the LTetriminos
	 * @param y The y-position of the upper-left corner of the LTetriminos
	 * @param color The color of the LTetriminos
	 */
	public LTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for LTetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the LTetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i+1);
			if (i != NUMBER_OF_PIECES-1)
				currentBlock.move(0, currentBlock.getBlockSize() * i);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize() * (i-1));
		}
	}
	
	@Override
	/**
	 * This method gives the rectangle covering the piece.
	 * @return The box covering to the piece's area
	 */
	public Rectangle boundingBox() {
		return new Rectangle(getX(), getY(), 2 * getBlockAt(1).getBlockSize()+1, 3 * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the LTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}

}
