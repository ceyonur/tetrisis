package pieces;

import java.awt.*;

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
		// Since the total volume of the piece (think as matrix) is equal for ZERO and ONEHUNDREDANDEIGHTY degrees
		if (getRotationLevel() == rotationLevel.ZERO || getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			boundingBoxWidth = 3;
			boundingBoxHeight = 2;
		} else { // Since the total volume of the piece (think as matrix) is equal for NINETY and TWOHUNDREDANDSEVENTY degrees
			boundingBoxWidth = 2;
			boundingBoxHeight = 3;
		}
		
		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the ZTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){
			setAnchorBlock(getBlockAt(2));
			setRotationLevel(rotationLevel.NINETY);
		} else if (getRotationLevel() == rotationLevel.NINETY){
			setAnchorBlock(getBlockAt(3));
			setRotationLevel(rotationLevel.ONEHUNDREDANDEIGHTY);
		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			setAnchorBlock(getBlockAt(3));
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
		if (getRotationLevel() != rotationLevel.ONEHUNDREDANDEIGHTY && getRotationLevel() != rotationLevel.ZERO)
			moveABlockRight();
	}
	
	/**
	 * This method, firstly, clones the piece, and then rotates it and return its locations to determine 
	 * whether it can rotate or not.
	 * @return The locations of the blocks of the piece's clone, which is rotated, as a 2D matrix
	 */
	@Override
	public int[][] cloneRotateAndGetLocationOnMatrix(){
		ZTetriminos clone = new ZTetriminos(getX(), getY(), getColor());
		clone.setBlockList(blocks);
		clone.setRotationLevel(getRotationLevel());
		clone.rotate();
		return clone.getLocationOnMatrix();
	}
}
