package pieces;

import java.awt.Color;
import java.awt.Rectangle;

import pieces.Piece.rotationLevel;

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
		return new Rectangle(getX(), getY(), 2* getBlockAt(1).getBlockSize()+1, 2 * getBlockAt(1).getBlockSize()+1);
	}

	@Override
	/**
	 * This method rotates the RTriminos 90 degrees clockwise
	 */
	public void rotate() {
		if (getRotationLevel() == rotationLevel.ZERO){

		} else if (getRotationLevel() == rotationLevel.NINETY){

		} else if (getRotationLevel() == rotationLevel.ONEHUNDREDANDEIGHTY){

		} else {

		}
	}
}
