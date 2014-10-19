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
	// The abstract methods
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
	//-----
	
	protected final static Color defaultColor = Color.BLACK; // The default color of a piece
	private int upperLeftCornerX; // The x-position of the upper-left corner of the whole piece
	private int upperLeftCornerY; // The y-position of the upper-left corner of the whole piece
	private Color color; // The color of the piece
	protected static enum rotationLevel {ZERO, NINETY, ONEHUNDREDANDEIGHTY, TWOHUNDREDANDSEVENTY}; // To indicate four rotation levels of a piece
	private rotationLevel currentRotationLevel; // The current rotation level
	
	protected int boundingBoxWidth; // The bounding box width of the piece
	protected int boundingBoxHeight; // The bounding box width of the piece
	
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
	
	/**
	 * This method checks whether the Piece instance is a Tetriminos or not
	 * @return true if the piece is Tetriminos; false otherwise
	 */
	public boolean isTetriminos(){
		return this.getClass().getName() == Tetriminos.class.getName();
	}
	
	/**
	 * This method checks whether the Piece instance is a Triminps or not
	 * @return true if the piece is Triminps; false otherwise
	 */
	public boolean isTriminos(){
		return this.getClass().getName() == Triminos.class.getName();
	}
	
	/**
	 * This method creates a random piece with random color
	 * @param pieceChoice The piece choice which has been set from the settings
	 * @param xRange The maximum x value of the screen in which the piece is going to be put
	 * @return A random Piece object; it can be Tetriminos or Triminos
	 */
	public static Piece getRandomPiece(PieceChoice pieceChoice, int xRange) {
		Random rgen = new Random(System.currentTimeMillis()); // Random generator

		Color randomColor = SColor.getRandomPieceColor(); // Random color generated by SColor class
		
		Piece randomPiece = null;
		int randomNumberForPiece = 0;
		if (pieceChoice.hasBoth())
			randomNumberForPiece = rgen.nextInt(10) + 1; // The piece can be both Tetriminos and Triminos (10 pieces in total (from 1 to 10))
		else if (pieceChoice.hasTetriminos())
			randomNumberForPiece = rgen.nextInt(7) + 1; // The piece can be only Tetriminos (10 pieces in total (from 1 to 7))
		else if (pieceChoice.hasTriminos())
			randomNumberForPiece = rgen.nextInt(3) + 8; // The piece can be only Triminos (3 pieces in total (8, 9 and 10))

		// According to the selected random integer, the piece is chosen with the random color generated above
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

		// Location adjustments of the piece to make it appear at the upper center of the game board
		int appearX = ((int) (xRange - randomPiece.boundingBox().getWidth())/2);
		while (appearX % randomPiece.getBlocks().get(1).getBlockSize() != 0)
			++appearX;

		int appearY = -4 * randomPiece.getBlocks().get(1).getBlockSize();
		randomPiece.move(appearX, appearY);

		return randomPiece;
	}
}
