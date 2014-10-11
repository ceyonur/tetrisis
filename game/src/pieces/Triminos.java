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
	 * This method moves the whole piece a block size down
	 */
	public void moveABlockDown(){
		move(0, blocks.get(0).getBlockSize());
	}

	/**
	 * This method moves the whole piece a block size right
	 */
	public void moveABlockRight(){
		move(blocks.get(0).getBlockSize(), 0);
	}

	/**
	 * This method moves the whole piece a block size left
	 */
	public void moveABlockLeft(){
		move(-1 * blocks.get(0).getBlockSize(), 0);
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

	/**
	 * This method rotates the whole triminos by changing the positions of the blocks according to its anchor block
	 */
	protected void rotateWholePiece(){
		int minXForBoundingBox = 10000;
		int minYForBoundingBox = 10000;

		for (int i=0; i<NUMBER_OF_PIECES; i++){
			int anchorX = getAnchorBlock().getX();
			int anchorY = getAnchorBlock().getY();

			int blockSize = getAnchorBlock().getBlockSize();

			if (blocks.get(i) != getAnchorBlock()){
				int currentBlockX = blocks.get(i).getX();
				int currentBlockY = blocks.get(i).getY();

				int differenceBetweenXCoordinates = currentBlockX - anchorX;
				int differenceLevelX = differenceBetweenXCoordinates / blockSize;

				int differenceBetweenYCoordinates = currentBlockY - anchorY;
				int differenceLevelY = differenceBetweenYCoordinates / blockSize;


				blocks.get(i).move( -1 * (differenceLevelX + differenceLevelY) * blockSize , (differenceLevelX - differenceLevelY) * blockSize);
			}

			if (blocks.get(i).getX() < minXForBoundingBox)
				minXForBoundingBox = blocks.get(i).getX();

			if (blocks.get(i).getY() < minYForBoundingBox)
				minYForBoundingBox = blocks.get(i).getY();			
		}
		
		setUpperLeftCornerX(minXForBoundingBox);
		setUpperLeftCornerY(minYForBoundingBox);
	}
	
	/**
	 * This method returns the locations of the blocks of the piece
	 */
	public int[][] getLocationOnMatrix(){
		int[][] locations = new int[NUMBER_OF_PIECES][2];
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			locations[i][0] = blocks.get(i).getX() / blocks.get(i).getBlockSize();
			locations[i][1] = blocks.get(i).getY() / blocks.get(i).getBlockSize() + 1;
		}
		return locations;
	}
	
	/**
	 * This method returns the blocks as an ArrayList
	 */
	public ArrayList<Block> getBlocks(){
		return blocks;
	}
}
