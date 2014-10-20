package pieces;

import java.awt.*;

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
		// Since the total volume of the piece (think as matrix) is equal for ZERO and ONEHUNDREDANDEIGHTY degrees
		if (getRotationLevel() == rotationLevel.ZERO || getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			boundingBoxWidth = 1;
			boundingBoxHeight = 4;
		} else { // Since the total volume of the piece (think as matrix) is equal for NINETY and TWOHUNDREDANDSEVENTY degrees
			boundingBoxWidth = 4;
			boundingBoxHeight = 1;
		}
		
		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the ITetriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){
			setAnchorBlock(getBlockAt(4));
			setRotationLevel(rotationLevel.NINETY);
		} else if (getRotationLevel() == rotationLevel.NINETY){
			setAnchorBlock(getBlockAt(1));
			setRotationLevel(rotationLevel.ONEHUNDREDANDEIGHTY);
		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			setAnchorBlock(getBlockAt(1));
			setRotationLevel(rotationLevel.TWOHUNDREDANDSEVENTY);
		} else {
			setAnchorBlock(getBlockAt(4));
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
		if (getRotationLevel() == rotationLevel.ZERO || getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			moveABlockLeft();
			moveABlockLeft();
		} else {
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
		ITetriminos clone = new ITetriminos(getX(), getY(), getColor());
		clone.setBlockList(blocks);
		clone.setRotationLevel(getRotationLevel());
		clone.rotate();
		return clone.getLocationOnMatrix();
	}
}
