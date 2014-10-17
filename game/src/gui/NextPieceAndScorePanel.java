package gui;

import java.awt.*;

import javax.swing.*;
import pieces.Piece;

public class NextPieceAndScorePanel extends JPanel {
	private Piece nextPiece;
	private int width;
	private int height;
	private JLabel score;
	private JLabel level;
	private JLabel deletedLines;
	private JPanel info;
	
	private int deletedLineNo = -1;
	
	private boolean currentState = false;
	
	private int nextPieceAreaWidth;
	private int nextPieceAreaHeight;
	private int nextPieceAreaX;
	private int nextPieceAreaY;
	private double pieceOldPositionX;
	private double pieceOldPositionY;

	public NextPieceAndScorePanel(int width, int height){
		super();
		setBackground(Color.LIGHT_GRAY);
		this.width = width;
		this.height = height;
		setSize(width, height);
		
		initializeNextPieceAreaValues();
		
		info = new JPanel();
		info.setBackground(getBackground());
		info.setLayout(new GridLayout(3,1));
		
		deletedLines = new JLabel();
		deletedLines.setFont(new Font(deletedLines.getFont().getFamily(), deletedLines.getFont().getStyle(), 25));
		info.add(deletedLines);
		
		score = new JLabel();
		score.setFont(new Font(score.getFont().getFamily(), score.getFont().getStyle(), 25));
		info.add(score);
		
		level = new JLabel();
		level.setFont(new Font(level.getFont().getFamily(), level.getFont().getStyle(), 25));
		info.add(level);
		
		add(info);
		
		increaseDeletedLineNo();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		info.setLocation(0,nextPieceAreaY + nextPieceAreaHeight + 20);
		g.drawRect(nextPieceAreaX, nextPieceAreaY, nextPieceAreaWidth, nextPieceAreaHeight);
		g.drawRect(nextPieceAreaX-1, nextPieceAreaY-1, nextPieceAreaWidth+2, nextPieceAreaHeight+2);
		g.drawRect(nextPieceAreaX-2, nextPieceAreaY-2, nextPieceAreaWidth+4, nextPieceAreaHeight+4);
		g.setColor(Color.WHITE);
		g.fillRect(nextPieceAreaX, nextPieceAreaY, nextPieceAreaWidth, nextPieceAreaHeight);
		if (currentState){
			nextPiece.paint(g);
		}
	}
	
	public void setPiece(Piece piece){
		nextPiece = piece;
		pieceOldPositionX = nextPiece.boundingBox().getX();
		pieceOldPositionY = nextPiece.boundingBox().getY();
		int pieceNewXValue = (int) (nextPieceAreaX + (int) ((nextPieceAreaWidth - nextPiece.boundingBox().getWidth()) /2) - pieceOldPositionX);
		int pieceNewYValue = (int) (nextPieceAreaY + (int) ((nextPieceAreaHeight - nextPiece.boundingBox().getHeight()) /2) - pieceOldPositionY);
		nextPiece.move(pieceNewXValue, pieceNewYValue);
		repaint();
	}
	
	public Piece getPiece(){
		int pieceOldXValue = -1 * ((int) (nextPieceAreaX + (int) ((nextPieceAreaWidth - nextPiece.boundingBox().getWidth()) /2) - pieceOldPositionX));
		int pieceOldYValue = -1 * ((int) (nextPieceAreaY + (int) ((nextPieceAreaHeight - nextPiece.boundingBox().getHeight()) /2) - pieceOldPositionY));
		nextPiece.move(pieceOldXValue,pieceOldYValue);
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
	
	public void setDeletedLine(){
		String deletedLineString = "Lines : " + deletedLineNo;
		deletedLines.setText(deletedLineString);
	}
	
	private void initializeNextPieceAreaValues(){
		nextPieceAreaWidth = (int) (width / 2.5);
		nextPieceAreaHeight = (int) (height / 3.25);
		nextPieceAreaX = (width - nextPieceAreaWidth) / 2;
		nextPieceAreaY = (height - nextPieceAreaHeight) / 10;
	}
	
	public void setVisibility(boolean state){
		currentState = state;
		repaint();
	}
	
	public void increaseDeletedLineNo(){
		deletedLineNo++;
		setDeletedLine();
	}
}
