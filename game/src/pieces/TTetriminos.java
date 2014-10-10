package pieces;

import java.awt.Color;

/**
 * This class corresponds to the T-Tetriminos shape.
 * @author bedirhancaldir
 */
public class TTetriminos extends Tetriminos{

	/**
	 * The constructor of the class TTetriminos. Sets the color, x and y coordinates of the TTetriminos
	 * @param x The x-position of the upper-left corner of the TTetriminos
	 * @param y The y-position of the upper-left corner of the TTetriminos
	 * @param color The color of the TTetriminos
	 */
	public TTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for TTetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the TTetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			if (i < NUMBER_OF_PIECES-1)
				currentBlock.move(currentBlock.getBlockSize() * i, 0);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize());
		}
	}

	@Override
	/**
	 * This method rotates the TTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}

}
