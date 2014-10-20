package pieces;

import java.awt.*;

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
		// Since the total volume of the piece (think as matrix) is equal for ZERO and ONEHUNDREDANDEIGHTY degrees
		if (getRotationLevel() == rotationLevel.ZERO || getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			boundingBoxWidth = 2;
			boundingBoxHeight = 3;
		} else { // Since the total volume of the piece (think as matrix) is equal for NINETY and TWOHUNDREDANDSEVENTY degrees
			boundingBoxWidth = 3;
			boundingBoxHeight = 2;
		}
		
		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the LTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){
			setAnchorBlock(getBlockAt(2));
			setRotationLevel(rotationLevel.NINETY);
		} else if (getRotationLevel() == rotationLevel.NINETY){
			setAnchorBlock(getBlockAt(2));
			setRotationLevel(rotationLevel.ONEHUNDREDANDEIGHTY);
		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			setAnchorBlock(getBlockAt(1));
			setRotationLevel(rotationLevel.TWOHUNDREDANDSEVENTY);
		} else {
			setAnchorBlock(getBlockAt(3));
			setRotationLevel(rotationLevel.ZERO);
		}
		rotateWholePiece();
		adjustTheLocation();
	}
	
	@Override
	/**
	 * This method adjusts the position of the piece after the rotation
	 */
	protected void adjustTheLocation(){
		if (getRotationLevel() == rotationLevel.NINETY)
			moveABlockRight();
		else if (getRotationLevel() == rotationLevel.TWOHUNDREDANDSEVENTY)
			moveABlockLeft();
		else if (getRotationLevel() == rotationLevel.ZERO){
			moveABlockLeft();
			moveABlockLeft();
		}
	}
	
	/**
	 * This method, firstly, clones the piece, and then rotates it and return its locations to determine 
	 * whether it can rotate or not.
	 * @return The locations of the blocks of the piece's clone, which is rotated, as a 2D matrix
	 */
	@Override
	public int[][] cloneRotateAndGetLocationOnMatrix(){
		LTetriminos clone = new LTetriminos(getX(), getY(), getColor());
		clone.setBlockList(blocks);
		clone.setRotationLevel(getRotationLevel());
		clone.rotate();
		return clone.getLocationOnMatrix();
	}
}
