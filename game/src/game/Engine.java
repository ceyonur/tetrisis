package game;

import gui.*;
import highscores.HighScores;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Random;

import pieces.*;
import settings.*;

public class Engine {
	private Board boardMatrix; // The field for board object of the game
	private KeyConfigure keys; // The field for keys of the actions in the game
	private PieceChoice pieceChoice; // The field for the tetriminos/triminos choice
	private int levelNo; // The field for the number of the level to display
	private int speedInMilliseconds; // The field for the speed of the game (in milliseconds)
	private Piece currentPiece; // The current piece
	private BoardPanel boardPanel; // The panel for the board
	private NextPieceAndScorePanel nextPiecePanel;
	private HighScores highScores;
	private double score;

	/**
	 * The constructor of the Engine class. Creates the board and takes the settings (if nothing changed, default ones will be used)
	 * @param settings The settings of the game (can be default)
	 */
	public Engine(Settings settings){
		if (settings.getBoardSizeChoice().isLarge()){
			Block.setSize(30);
		} else if (settings.getBoardSizeChoice().isMedium()){
			Block.setSize(40);
		} else {
			Block.setSize(45);
		}
		boardMatrix = new Board(settings.getRow(), settings.getColumn(), this);
		levelNo = settings.getLevelChoice().getLevel();
		speedInMilliseconds = (int) (1000 * settings.getLevelChoice().getSpeed());
		keys = settings.getKeyConfigure();
		pieceChoice = settings.getPieceChoice();

		currentPiece = null;
		score = 0;

		nextPiecePanel = new NextPieceAndScorePanel(getBoardColumnLength() , getBoardRowLength());
		nextPiecePanel.setLevel(levelNo);
		
		boardPanel = new BoardPanel(keys, speedInMilliseconds, this, boardMatrix, nextPiecePanel);

		play();
	}

	/**
	 * The default constructor of the game. It creates a new Settings object, so everything will be default.
	 * @throws FileNotFoundException 
	 */
	public Engine(){
		this(new Settings());
	}

	public void play(){
		if (currentPiece !=null){
			boardMatrix.updateBoard(currentPiece.getLocationOnMatrix(), currentPiece.getColorAsInteger());
		}

		if (!isGameOver()){
			if (boardMatrix.isEmpty()){
				currentPiece = Piece.getRandomPiece(pieceChoice, getBoardColumnLength());
			} else {
				currentPiece = nextPiecePanel.getPiece();
			}
			Piece randomPiece = Piece.getRandomPiece(pieceChoice, getBoardColumnLength());
			nextPiecePanel.setPiece(randomPiece);
			while (currentPiece.getY() * -1 > currentPiece.boundingBox().getHeight())
				currentPiece.moveABlockDown();
			boardPanel.addPiece(currentPiece);
		} else {
			boardPanel.setMode(false);
		}
		nextPiecePanel.setCurrentScore(score);
	}

	public BoardPanel getBoardPanel(){
		return boardPanel;
	}
	
	public boolean isGameOver(){
		return boardMatrix.isGameOver();
	}
	
	public NextPieceAndScorePanel getNextPieceAndScorePanel(){
		return nextPiecePanel;
	}

	public int getLevelNo(){
		return levelNo;
	}

	public int getBoardColumnLength(){
		return boardMatrix.getColumnLength();
	}

	public int getBoardRowLength(){
		return boardMatrix.getRowLength();
	}
	
	public Board getBoardMatrix(){
		return boardMatrix;
	}

	public void eliminatedLine(int lineNo){
		boardPanel.clearEliminatedLine(lineNo);
		nextPiecePanel.increaseDeletedLineNo();
		boardPanel.delayPieceSelection();
	}
	
	public boolean isScoreHighEnough(double score){
		highScores = new HighScores();
		return highScores.isScoreHighEnough(score);
	}

	public void increaseScore(int howManyLinesAreDeleted){

		double speedInSeconds = (speedInMilliseconds / 1000.0)/1;

		if (howManyLinesAreDeleted == 1)
			score += 1.00/speedInSeconds;
		else if (howManyLinesAreDeleted == 2)
			score += (3 * 1.00/speedInSeconds);
		else if (howManyLinesAreDeleted == 3)
			score += (6 * 1.00/speedInSeconds);
		else
			score += (10 * 1.00/speedInSeconds);
	}

	public double getScore(){
		return score;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
