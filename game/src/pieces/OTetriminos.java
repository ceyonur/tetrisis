package pieces;

import java.awt.Color;

/**
 * This class corresponds to the O-Tetriminos shape.
 * @author bedirhancaldir
 */
public class OTetriminos extends Tetriminos{

	/**
	 * The constructor of the class OTetriminos. Sets the color, x and y coordinates of the OTetriminos
	 * @param x The x-position of the upper-left corner of the OTetriminos
	 * @param y The y-position of the upper-left corner of the OTetriminos
	 * @param color The color of the OTetriminos
	 */
	public OTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for OTetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the OTetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(0, currentBlock.getBlockSize() * i);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize() * (i-2));
		}
	}

	@Override
	/**
	 * This method rotates the OTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}

}
