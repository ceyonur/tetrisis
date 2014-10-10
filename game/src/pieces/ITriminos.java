package pieces;

import java.awt.Color;

public class ITriminos extends Triminos{

	public ITriminos(int x, int y, Color color) {
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
