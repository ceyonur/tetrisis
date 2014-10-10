package pieces;

import java.awt.Color;

public class OTetriminos extends Tetriminos{

	public OTetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks();
	}

	@Override
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			if (i < NUMBER_OF_PIECES-2)
				currentBlock.move(0, currentBlock.getBlockSize() * i);
			else
				currentBlock.move(currentBlock.getBlockSize(), currentBlock.getBlockSize() * (i-2));
		}
	}

	@Override
	public void rotate() {
		
	}

}
