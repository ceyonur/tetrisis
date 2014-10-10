package pieces;

import java.awt.*;
import java.util.ArrayList;

/**
* This abstract class is a template for the Triminos pieces.
* @author bedirhancaldir
*/
public abstract class Triminos extends Piece{
	protected final int NUMBER_OF_PIECES = 3; // The number of pieces for triminoses is 3.
	private ArrayList<Block> blocks; // To hold the blocks together

	/**
	 * This is the constructor of the class Triminos. Creates the blocks (there are 3 of them)
	 * @param x The x-position of the upper-left corner of the whole shape
	 * @param y The y-position of the upper-left corner of the whole shape
	 * @param color The color of the shape
	 */
	public Triminos(int x, int y, Color color) {
		super(x, y, color);
		
		blocks = new ArrayList<Block>();
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			blocks.add(new Block(x, y, color));
		}
	}
	
	/**
	 * The default constructor of the class. The location is set to 0  and the color is set to the default color as default.
	 */
	public Triminos(){
		this(0,0,defaultColor);
	}
	
	/**
	 * This method moves the whole piece according to the given x and y.
	 */
	public void move(int x, int y){
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			blocks.get(i).move(x, y);
		}
		setUpperLeftCornerX(getX() + x);
		setUpperLeftCornerY(getY() + y);
	}
	
	/**
	 * This method returns the block whose order in the list is given. The classes extend this abstract class,
	 * which are Triminos pieces, should be able to reach them to obtain their specific shape.
	 * @param index The number of the piece if it is assumed that each of the blocks is given a number from 1 to NUMBER_OF_PIECES.
	 * @return The block corresponding to the given index or null if the given index isn't valid.
	 */
	public Block getBlockAt(int index){
		if (index <= NUMBER_OF_PIECES && index >= 1)
			return blocks.get(index-1);
		else
			return null;
	}
	
	/**
	 * This method paints each block to the screen.
	 * @param g This is the Graphics object to which the triminos will be drawn.
	 */
	public void paint(Graphics g){
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			blocks.get(i).paint(g);
		}
	}
}
