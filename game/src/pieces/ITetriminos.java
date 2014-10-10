package pieces;

import java.awt.Color;

public class ITetriminos extends Tetriminos{

	public ITetriminos(int x, int y, Color color) {
		super(x, y, color);
		assembleBlocks();
	}

	@Override
	protected void assembleBlocks() {
		for (int i=0; i<NUMBER_OF_PIECES; i++){
			Block currentBlock = getBlockAt(i);
			currentBlock.move(0, currentBlock.getBlockSize() * i);
		}
	}

	@Override
	public void rotate() {
		
	}
}
