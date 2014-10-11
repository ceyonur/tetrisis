package pieces;

import java.awt.Color;
import java.awt.Rectangle;

import pieces.Piece.rotationLevel;

/**
 * This class corresponds to the J-Tetriminos shape.
 * @author bedirhancaldir
 */
public class JTetriminos extends Tetriminos{

	/**
	 * The constructor of the class JTetriminos. Sets the color, x and y coordinates of the JTetriminos
	 * @param x The x-position of the upper-left corner of the JTetriminos
	 * @param y The y-position of the upper-left corner of the JTetriminos
	 * @param color The color of the JTetriminos
	 */
	public JTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks(); // To assemble the blocks in a specific way for JTetriminos
	}

	@Override
	/**
	 * This method orders the blocks of the JTetriminos to obtain its specific shape
	 */
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i+1);
			if (i != NUMBER_OF_PIECES-1)
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize() * i);
			else
				currentBlock.move(0, currentBlock.getBlockSize() * (i-1));
		}
	}

	@Override
	/**
	 * This method gives the rectangle covering the piece.
	 * @return The box covering to the piece's area
	 */
	public Rectangle boundingBox() {
		if (getRotationLevel() == rotationLevel.ZERO || getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			boundingBoxWidth = 2;
			boundingBoxHeight = 3;
		} else {
			boundingBoxWidth = 3;
			boundingBoxHeight = 2;
		}
		
		return new Rectangle(getX(), getY(), boundingBoxWidth * getBlockAt(1).getBlockSize()+1, boundingBoxHeight * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the JTetriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){
			setAnchorBlock(getBlockAt(3));
			setRotationLevel(rotationLevel.NINETY);
		} else if (getRotationLevel() == rotationLevel.NINETY){
			setAnchorBlock(getBlockAt(1));
			setRotationLevel(rotationLevel.ONEHUNDREDANDEIGHTY);
		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){
			setAnchorBlock(getBlockAt(2));
			setRotationLevel(rotationLevel.TWOHUNDREDANDSEVENTY);
		} else {
			setAnchorBlock(getBlockAt(3));
			setRotationLevel(rotationLevel.ZERO);
		}
		rotateWholePiece();
	}
}
