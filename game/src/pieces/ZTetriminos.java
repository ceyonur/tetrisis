package pieces;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * This class corresponds to the Z-Tetriminos shape.
 * @author bedirhancaldir
 */
public class ZTetriminos extends Tetriminos{

	/**
	 * The constructor of the class ZTetriminos. Sets the color, x and y coordinates of the ZTetriminos
	 * @param x The x-position of the upper-left corner of the ZTetriminos
	 * @param y The y-position of the upper-left corner of the ZTetriminos
	 * @param color The color of the ZTetriminos
	 */
	public ZTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for ZTetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the ZTetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i+1);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(currentBlock.getBlockSize() * i, 0);
			else
				currentBlock.move(currentBlock.getBlockSize() * (i-1), currentBlock.getBlockSize());
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
	 * This method rotates the ZTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		
	}
}
