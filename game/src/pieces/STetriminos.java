package pieces;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * This class corresponds to the S-Tetriminos shape.
 * @author bedirhancaldir
 */
public class STetriminos extends Tetriminos{

	/**
	 * The constructor of the class STetriminos. Sets the color, x and y coordinates of the STetriminos
	 * @param x The x-position of the upper-left corner of the STetriminos
	 * @param y The y-position of the upper-left corner of the STetriminos
	 * @param color The color of the STetriminos
	 */
	public STetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for STetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the STetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i+1);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(currentBlock.getBlockSize() * (i+1), 0);
			else
				currentBlock.move(currentBlock.getBlockSize() * (i-2), currentBlock.getBlockSize());
		}
	}
	
	@Override
	/**
	 * This method gives the rectangle covering the piece.
	 * @return The box covering to the piece's area
	 */
	public Rectangle boundingBox() {
		return new Rectangle(getX(), getY(), 3 * getBlockAt(1).getBlockSize()+1, 2 * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the STetriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}

}
