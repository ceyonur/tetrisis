package pieces;

import java.awt.Color;
import java.awt.Rectangle;

import pieces.Piece.rotationLevel;

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
		setAnchorBlock(getBlockAt(2));
		assembleBlocks(); // To assemble the blocks in a specific way for TTetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the TTetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i+1);
			if (i < NUMBER_OF_PIECES-1)
				currentBlock.move(currentBlock.getBlockSize() * i, 0);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize());
		}
	}

	@Override
	/**
	 * This method gives the rectangle covering the piece.
	 * @return The box covering to the piece's area
	 */
	public Rectangle boundingBox() {
		if (getRotationLevel() == rotationLevel.ZERO || getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			boundingBoxWidth = 3;
			boundingBoxHeight = 2;
		} else {
			boundingBoxWidth = 2;
			boundingBoxHeight = 3;
		}

		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the TTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){
			setAnchorBlock(getBlockAt(2));
			setRotationLevel(rotationLevel.NINETY);
		} else if (getRotationLevel() == rotationLevel.NINETY){
			setAnchorBlock(getBlockAt(3));
			setRotationLevel(rotationLevel.ONEHUNDREDANDEIGHTY);
		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			setAnchorBlock(getBlockAt(4));
			setRotationLevel(rotationLevel.TWOHUNDREDANDSEVENTY);
		} else {
			setAnchorBlock(getBlockAt(1));
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
			moveABlockLeft();
		}
	}
}
