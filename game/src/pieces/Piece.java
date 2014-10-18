package pieces;

import gui.SColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import settings.PieceChoice;

/**
 * This abstract class is a template for all of the pieces (both Tetriminoses and Triminoses).
 * @author bedirhancaldir
 */
public abstract class Piece {
	protected final static Color defaultColor = Color.BLACK; // The default color of a piece
	
	private int upperLeftCornerX; // The x-position of the upper-left corner of the whole piece
	private int upperLeftCornerY; // The y-position of the upper-left corner of the whole piece
	private Color color; // The color of the piece
	protected static enum rotationLevel {ZERO, NINETY, ONEHUNDREDANDEIGHTY, TWOHUNDREDANDSEVENTY}; // To indicate four rotation levels of a piece
	private rotationLevel currentRotationLevel; // The current rotation level
	
	protected int boundingBoxWidth;
	protected int boundingBoxHeight;
	
	private Block anchorBlock; // This is the anchor block that the piece rotates around (depends on the rotation level)
	
	/**
	 * The constructor of the class Piece. Sets the color, x and y coordinates of the piece
	 * @param x The x-position of the upper-left corner of the whole piece
	 * @param y The y-position of the upper-left corner of the whole piece
	 * @param color The color of the piece
	 */
	public Piece(int x, int y, Color color){
		setUpperLeftCornerX(x);
		setUpperLeftCornerY(y);
		setColor(color);
		setRotationLevel(rotationLevel.ZERO); // Initial rotation degree is ZERO.
	}
	
	/**
	 * The default constructor of the class. The location is set to 0  and the color is set to the default color as default.
	 */
	public Piece(){
		this(0,0,defaultColor);
	}
	
	/**
	 * This method sets the x coordinate of the upper-left corner of the piece to the given value
	 * @param x The value of the x coordinate of the upper-left corner
	 */
	public void setUpperLeftCornerX(int x){
		upperLeftCornerX = x;
	}
	
	/**
	 * This method sets the y coordinate of the upper-left corner of the piece to the given value
	 * @param y The value of the y coordinate of the upper-left corner
	 */
	public void setUpperLeftCornerY(int y){
		upperLeftCornerY = y;
	}
	
	/**
	 * This method sets the color of the piece to the given color
	 * @param color The color of the piece
	 */
	public void setColor(Color color){
		this.color = color;
	}
	
	/**
	 * This method takes a rotation level and sets it as the current rotation level
	 * @param level The new rotation level as enum format (ZERO, NINETY, ONEHUNDREDANDEIGHTY, or TWOHUNDREDANDSEVENTY)
	 */
	public void setRotationLevel(rotationLevel level){
		currentRotationLevel = level;
	}
	/**
	 * This method takes a block and makes it to be the new anchor block of the piece for rotation
	 * @param newAnchorBlock The block of the piece that is going to be the anchor block
	 */
	public void setAnchorBlock(Block newAnchorBlock){
		anchorBlock = newAnchorBlock;
	}
	
	/**
	 * This method returns the x coordinate of the upper-left corner of the piece
	 * @return The x coordinate of the upper-left corner of the piece
	 */
	public int getX(){
		return upperLeftCornerX;
	}
	
	/**
	 * This method returns the y coordinate of the upper-left corner of the piece
	 * @return The y coordinate of the upper-left corner of the piece
	 */
	public int getY(){
		return upperLeftCornerY;
	}
	
	/**
	 * This method returns the color of the piece
	 * @return The color of the piece
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * This method returns the color of the piece as integer
	 * (1: BLUE, 2: CYAN. 3: DARK_GRAY, 4: GREEN, 5: MAGENTA, 6: ORANGE, 7: PINK, 8: RED, 9: YELLOW)
	 * @return The color of the piece as integer
	 */
	public int getColorAsInteger(){
		if (getColor() == Color.BLUE)
			return 1;
		else if (getColor() == Color.CYAN)
			return 2;
		else if (getColor() == Color.DARK_GRAY)
			return 3;
		else if (getColor() == Color.GREEN)
			return 4;
		else if (getColor() == Color.MAGENTA)
			return 5;
		else if (getColor() == Color.ORANGE)
			return 6;
		else if (getColor() == Color.PINK)
			return 7;
		else if (getColor() == Color.RED)
			return 8;
		else
			return 9;
	}
	
	/**
	 * This method returns the current anchor block of the piece
	 * @return The current anchor block of the piece according to the its current rotation level
	 */
	public Block getAnchorBlock(){
		return anchorBlock;
	}
	
	/**
	 * This method returns the rotation level of the piece in enum format
	 * @return The rotation level as enum format (ZERO, NINETY, ONEHUNDREDANDEIGHTY, or TWOHUNDREDANDSEVENTY)
	 */
	public rotationLevel getRotationLevel(){
		return currentRotationLevel;
	}
	
	public boolean isTetriminos(){
		return this.getClass().getName() == Tetriminos.class.getName();
	}
	public boolean isTriminos(){
		return this.getClass().getName() == Triminos.class.getName();
	}
	
	protected abstract void assembleBlocks(); // The abstract method to assemble the blocks in a specific way by each Piece type
	public abstract void move(int x, int y); // To move the whole piece. Since the blocks aren't implemented in here, this must be abstract
	public abstract void moveABlockDown(); // To move the whole piece down for a block size
	public abstract void moveABlockRight(); // To move the whole piece right for a block size
	public abstract void moveABlockLeft(); // To move the whole piece left for a block size
	protected abstract void rotateWholePiece(); // To rotate the whole piece. Since the blocks aren't implemented in here, this must be abstract
	public abstract void rotate(); // The method used in each tetriminos/triminos to rotate the piece
	public abstract void paint(Graphics g); // To paint the whole piece. Since the blocks aren't implemented in here, this must be abstract
	public abstract Rectangle boundingBox(); // To obtain the rectangle circling the piece.
	protected abstract void adjustTheLocation(); // To adjust the location after the rotation
	public abstract int[][] getLocationOnMatrix(); // Returns the values getX/size and getY/size
	public abstract int[][] cloneRotateAndGetLocationOnMatrix(); // To handle the conflictions while rotating
	public abstract ArrayList<Block> getBlocks(); // Returns the blocks as an ArrayList
	public abstract void moveToAppropriatePositionToRotate(int maximumColumn); // To rotate, sometimes the piece must be located a block right or left
	protected abstract void setBlockList(ArrayList<Block> blocks); // To set the block list to the given special list
	
	public static Piece getRandomPiece(PieceChoice pieceChoice, int xRange) {
		Random rgen = new Random(System.currentTimeMillis());

		Color randomColor = SColor.getRandomPieceColor();
		
		Piece randomPiece = null;
		int randomNumberForPiece = 0;
		if (pieceChoice.hasBoth())
			randomNumberForPiece = rgen.nextInt(10) + 1;
		else if (pieceChoice.hasTetriminos())
			randomNumberForPiece = rgen.nextInt(7) + 1;
		else if (pieceChoice.hasTriminos())
			randomNumberForPiece = rgen.nextInt(3) + 8;

		switch (randomNumberForPiece){
		case 1: randomPiece = new ZTetriminos(0,0,randomColor); break;
		case 2: randomPiece = new STetriminos(0,0,randomColor); break;
		case 3: randomPiece = new OTetriminos(0,0,randomColor); break;
		case 4: randomPiece = new TTetriminos(0,0,randomColor); break;
		case 5: randomPiece = new JTetriminos(0,0,randomColor); break;
		case 6: randomPiece = new LTetriminos(0,0,randomColor); break;
		case 7: randomPiece = new ITetriminos(0,0,randomColor); break;
		case 8: randomPiece = new ITriminos(0,0,randomColor); break;
		case 9: randomPiece = new JTriminos(0,0,randomColor); break;
		default: randomPiece = new RTriminos(0,0,randomColor); break;
		}

		int appearX = ((int) (xRange - randomPiece.boundingBox().width)/2);
		while (appearX % randomPiece.getBlocks().get(1).getBlockSize() != 0)
			++appearX;

		int appearY = -4 * randomPiece.getBlocks().get(1).getBlockSize();
		randomPiece.move(appearX, appearY);

		return randomPiece;
	}
}
