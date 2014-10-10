package pieces;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Piece {
	private int upperLeftCornerX;
	private int upperLeftCornerY;
	private Color color;
	
	public Piece(int x, int y, Color color){
		setUpperLeftCornerX(x);
		setUpperLeftCornerY(y);
		setColor(color);
	}
	
	public void setUpperLeftCornerX(int x){
		upperLeftCornerX = x;
	}
	
	public void setUpperLeftCornerY(int y){
		upperLeftCornerY = y;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public int getUpperLeftCornerX(){
		return upperLeftCornerX;
	}
	
	public int getUpperLeftCornerY(){
		return upperLeftCornerY;
	}
	
	public Color getColor(){
		return color;
	}
	
	protected abstract void assembleBlocks();
	public abstract void move(int x, int y);
	public abstract void rotate();
	public abstract void paint(Graphics g);
}
