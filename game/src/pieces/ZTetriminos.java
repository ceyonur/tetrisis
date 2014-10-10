package pieces;

import java.awt.Color;

public class ZTetriminos extends Tetriminos{

	public ZTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks();
	}

	@Override
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(currentBlock.getBlockSize() * i, 0);
			else
				currentBlock.move(currentBlock.getBlockSize() * (i-1), currentBlock.getBlockSize());
		}
	}

	@Override
	public void rotate() {
		
	}

}
