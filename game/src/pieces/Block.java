package pieces;

import java.awt.*;

/**
 * This class is the building stone of all of the pieces (both Tetriminoses and Triminoses).
 * @author bedirhancaldir
 */
public class Block {
	private static int SIZE = 25; // The size (width and height) of a block
	private final static Color defaultColor = Color.BLACK; // The default color of a block
	
	private int x; // The x-position of the upper-left corner of the block
	private int y; // The y-position of the upper-left corner of the block
	private Color color; // The color of the block

	/**
	 * The constructor of the class Block. Sets the color, x and y coordinates of the block
	 * @param x The x-position of the upper-left corner of the block
	 * @param y The y-position of the upper-left corner of the block
	 * @param color The color of the block
	 */
	public Block(int x, int y, Color color){
		setLocation(x,y);
		setColor(color);
	}
	
	/**
	 * The default constructor of the class. The location is set to 0  and the color is set to the default color as default.
	 */
	public Block(){
		this(0,0,defaultColor);
	}

	/**
	 * This method paints the block to the screen.
	 * @param g This is the Graphics object to which the block will be drawn.
	 */
	public void paint(Graphics g) {
		Rectangle clipRect = g.getClipBounds();

		if (clipRect.intersects(this.boundingBox())) {
			g.setColor(color);
			g.fill3DRect(x, y, SIZE, SIZE,true);
		}
	}

	/**
	 * This method gives the rectangle covering the block.
	 * @return The box covering to the block's area
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, SIZE+1, SIZE+1);
	}
	
	/**
	 * This method moves the block in the given way
	 * @param x The distance that the block is traveling on the x axis
	 * @param y The distance that the block is traveling on the y axis
	 */
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}

	/**
	 * This method changes the color of the block to the given color
	 * @param color The new color of the block
	 */
	public void setColor(Color color){
		this.color = color;
	}

	/**
	 * This method changes the location of the block
	 * @param x The new location on the x-axis
	 * @param y The new location on the y-axis
	 */
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * This method changes the size of the block
	 * @param size The new size of the block
	 */
	public void setSize(int size){
		if (size >= 1)
			SIZE = size;
		else
			SIZE = 1;
	}
	
	/**
	 * This method returns the color of the block
	 * @return The color of the block
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * This method returns the size of the block
	 * @return The size of the block
	 */
	public int getBlockSize(){
		return SIZE;
	}
	
	/**
	 * This method returns the x-position of the block (the upper-left corner)
	 * @return The x-position of the block
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * This method returns the y-position of the block (the upper-left corner)
	 * @return The y-position of the block
	 */
	public int getY(){
		return y;
	}
}
