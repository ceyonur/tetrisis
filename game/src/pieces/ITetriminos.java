package pieces;

import java.awt.Color;

/**
 * This class corresponds to the I-Tetriminos shape.
 * @author bedirhancaldir
 */
public class ITetriminos extends Tetriminos{

	/**
	 * The constructor of the class ITetriminos. Sets the color, x and y coordinates of the ITetriminos
	 * @param x The x-position of the upper-left corner of the ITetriminos
	 * @param y The y-position of the upper-left corner of the ITetriminos
	 * @param color The color of the ITetriminos
	 */
	public ITetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for ITetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the ITetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			currentBlock.move(0, currentBlock.getBlockSize() * i);
		}
	}

	@Override
	/**
	 * This method rotates the ITetriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}
}
