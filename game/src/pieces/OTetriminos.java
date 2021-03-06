package pieces;

import java.awt.*;

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
			Block currentBlock = getBlockAt(i+1);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(0, currentBlock.getBlockSize() * i);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize() * (i-2));
		}
	}
	
	@Override
	/**
	 * This method gives the rectangle covering the piece.
	 * @return The box covering to the piece's area
	 */
	public Rectangle boundingBox() {
		// Since the total volume of the piece (think as matrix) is equal for any degrees
		boundingBoxWidth = 2;
		boundingBoxHeight = 2;
		
		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method does nothing for OTetriminos since the rotation of this piece gives itself all the time
	 */
	public void rotate() {}
	
	@Override
	/**
	 * This method does nothing for OTetriminos since no need for adjustment
	 */
	public void adjustTheLocation() {}

	/**
	 * This method directly returns the positions of the blocks of the piece itself since OTetriminos' rotation makes no sense
	 * @return The locations of the blocks of the piece itself, which is rotated, as a 2D matrix
	 */
	@Override
	public int[][] cloneRotateAndGetLocationOnMatrix(){
		return getLocationOnMatrix();
	}
}
