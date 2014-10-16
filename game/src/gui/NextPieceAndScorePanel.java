package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import pieces.Piece;

public class NextPieceAndScorePanel extends JPanel {
	private Piece nextPiece;
	private int width;
	private int height;
	private JLabel score;
	private JLabel level;
	private JPanel nextPieceArea;

	public NextPieceAndScorePanel(int width, int height){
		super();
		setBackground(Color.WHITE);
		this.width = width;
		this.height = height;
		setSize(width, height);
		
		nextPieceArea = new JPanel();
		nextPieceArea.setBackground(Color.LIGHT_GRAY);
		add(nextPieceArea);
		
		score = new JLabel("0");
		score.setFont(new Font(score.getFont().getFamily(), score.getFont().getStyle(), 25));
		add(score);
		
		level = new JLabel("");
		level.setFont(new Font(level.getFont().getFamily(), level.getFont().getStyle(), 25));
		add(level);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		nextPiece.paint(g);
		score.setLocation(0, 100);
		level.setLocation(0, 140);
		nextPieceArea.setSize(width/2, height/5);
		nextPieceArea.setLocation((int) width/8, (int)(height - nextPieceArea.getHeight()) /2);
	}
	
	public void setPiece(Piece piece){
		nextPiece = piece;
		repaint(0,0,width,height);
	}
	
	public Piece getPiece(){
		return nextPiece;
	}
	
	public void setCurrentScore(double newScore){
		String scoreString = "Score : " + newScore;
		score.setText(scoreString);
	}
	
	public void setLevel(int currentLevel){
		String levelString = "Level : " + currentLevel;
		level.setText(levelString);
	}
}
