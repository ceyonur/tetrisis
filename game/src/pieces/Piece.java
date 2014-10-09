package pieces;

public abstract class Piece {
	
	public boolean isTetriminos(){
		return this.getClass().getName() == Tetriminos.class.getName();
	}
	public boolean isTriminos(){
		return this.getClass().getName() == Triminos.class.getName();
	}

}
