package pieces;

import java.awt.Color;

public class STetriminos extends Tetriminos{

	public STetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks();
	}

	@Override
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(currentBlock.getBlockSize() * (i+1), 0);
			else
				currentBlock.move(currentBlock.getBlockSize() * (i-2), currentBlock.getBlockSize());
		}
	}

	@Override
	public void rotate() {
		
	}

}
