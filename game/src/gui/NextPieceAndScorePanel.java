package gui;

import java.awt.*;
import javax.swing.*;
import pieces.Piece;

public class NextPieceAndScorePanel extends JPanel {
	private Piece nextPiece;
	private int width;
	private int height;

	public NextPieceAndScorePanel(int width, int height){
		super();
		setBackground(Color.BLACK);
		this.width = width;
		this.height = height;
		setSize(width, height);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		nextPiece.paint(g);
	}
	
	public void setPiece(Piece piece){
		nextPiece = piece;
		repaint(0,0,width,height);
	}
	
	public Piece getPiece(){
		return nextPiece;
	}
}
