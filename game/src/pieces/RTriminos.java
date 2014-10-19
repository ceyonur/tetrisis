package pieces;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * This class corresponds to the R-Triminos shape.
 * @author bedirhancaldir
 */
public class RTriminos extends Triminos{

	/**
	 * The constructor of the class RTriminos. Sets the color, x and y coordinates of the RTriminos
	 * @param x The x-position of the upper-left corner of the RTriminos
	 * @param y The y-position of the upper-left corner of the RTriminos
	 * @param color The color of the RTriminos
	 */
	public RTriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for RTriminos
	}

	@Override
	/**
	 * This method orders the blocks of the RTriminos to obtain its specific shape
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
		// Since the total volume of the piece (think as matrix) is equal for any degrees
		boundingBoxWidth = 2;
		boundingBoxHeight = 2;

		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the RTriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){
			setAnchorBlock(getBlockAt(3));
			setRotationLevel(rotationLevel.NINETY);
		} else if (getRotationLevel() == rotationLevel.NINETY){
			setAnchorBlock(getBlockAt(2));
			setRotationLevel(rotationLevel.ONEHUNDREDANDEIGHTY);
		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			setAnchorBlock(getBlockAt(1));
			setRotationLevel(rotationLevel.TWOHUNDREDANDSEVENTY);
		} else {
			setAnchorBlock(getBlockAt(2));
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
		if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			moveABlockRight();
		} else
			moveABlockLeft();
	}
	
	/**
	 * This method, firstly, clones the piece, and then rotates it and return its locations to determine 
	 * whether it can rotate or not.
	 * @return The locations of the blocks of the piece's clone, which is rotated, as a 2D matrix
	 */
	@Override
	public int[][] cloneRotateAndGetLocationOnMatrix(){
		RTriminos clone = new RTriminos(getX(), getY(), getColor());
		clone.setBlockList(blocks);
		clone.setRotationLevel(getRotationLevel());
		clone.rotate();
		return clone.getLocationOnMatrix();
	}
}
