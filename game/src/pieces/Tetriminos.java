package pieces;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tetriminos extends Piece{
	protected final int NUMBER_OF_PIECES = 4;
	private ArrayList<Block> blocks;

	public Tetriminos(int x, int y, Color color) {
		super(x, y, color);

		blocks = new ArrayList<Block>();
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			blocks.add(new Block(getUpperLeftCornerX(), getUpperLeftCornerY(), getColor()));
		}
	}

	public void move(int x, int y){
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			blocks.get(i).move(x, y);
		}
	}

	public Block getBlockAt(int index){
		if (index <= NUMBER_OF_PIECES && index >= 1)
			return blocks.get(index-1);
		else
			return null;
	}
	
	public void paint(Graphics g){
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			blocks.get(i).paint(g);
		}
	}
}
