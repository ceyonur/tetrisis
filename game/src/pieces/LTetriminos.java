package pieces;

import java.awt.Color;

public class LTetriminos extends Tetriminos{

	public LTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks();
	}

	@Override
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			if (i != NUMBER_OF_PIECES-1)
				currentBlock.move(0, currentBlock.getBlockSize() * i);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize() * (i-1));
		}
	}

	@Override
	public void rotate() {
		
	}

}
