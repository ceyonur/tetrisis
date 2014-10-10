package pieces;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Block {
	private int SIZE = 10;
	private int x;
	private int y;
	private Color color;

	public Block(int x, int y, Color color){
		setLocation(x,y);
		setColor(color);
	}

	public void paint(Graphics g) {
		Rectangle clipRect = g.getClipBounds();

		if (clipRect.intersects(this.boundingBox())) {
			g.setColor(color);
			g.fillRect(x, y, SIZE, SIZE);
		}
	}

	public Rectangle boundingBox() {
		return new Rectangle(x, y, SIZE+1, SIZE+1);
	}
	
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setSize(int size){
		SIZE = size;
	}
	
	public Color getColor(){
		return color;
	}
	
	public int getBlockSize(){
		return SIZE;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
