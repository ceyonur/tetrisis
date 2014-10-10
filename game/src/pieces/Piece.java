package pieces;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This abstract class is a template for all of the pieces (both Tetriminoses and Triminoses).
 * @author bedirhancaldir
 */
public abstract class Piece {
	protected final static Color defaultColor = Color.BLACK; // The default color of a piece
	
	private int upperLeftCornerX; // The x-position of the upper-left corner of the whole piece
	private int upperLeftCornerY; // The y-position of the upper-left corner of the whole piece
	private Color color; // The color of the piece
	
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
	 * This method returns the x coordinate of the upper-left corner of the piece
	 * @return The x coordinate of the upper-left corner of the piece
	 */
	public int geX(){
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
	
	public boolean isTetriminos(){
		return this.getClass().getName() == Tetriminos.class.getName();
	}
	public boolean isTriminos(){
		return this.getClass().getName() == Triminos.class.getName();
	}
	
	protected abstract void assembleBlocks(); // The abstract method to assemble the blocks in a specific way by each Piece type
	public abstract void move(int x, int y); // To move the whole piece. Since the blocks aren't implemented in here, this must be abstract
	public abstract void rotate(); // To rotate the whole piece. Since the blocks aren't implemented in here, this must be abstract
	public abstract void paint(Graphics g); // To paint the whole piece. Since the blocks aren't implemented in here, this must be abstract
}
