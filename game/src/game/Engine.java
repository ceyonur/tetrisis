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
	private BoardPanel boardPanel; // The panel for the board (the left one)
	private NextPieceAndScorePanel nextPiecePanel; // The next piece panel (the right one)
	private HighScores highScores; // The HighScores object to update it when necessary
	private double score;

	/**
	 * The constructor of the Engine class. Creates the board and takes the settings (if nothing changed, default ones will be used)
	 * @param settings The settings of the game (can be default)
	 */
	public Engine(Settings settings){
		if (settings.getBoardSizeChoice().isLarge()){
			Block.setSize(25);
		} else if (settings.getBoardSizeChoice().isMedium()){
			Block.setSize(30);
		} else {
			Block.setSize(35);
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

	/**
	 * This is the main piece generating method of the game. It sets both the current piece and the next piece.
	 */
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
	}

	/**
	 * This method returns the board panel of the game
	 * @return The BoardPanel object containing the whole pieces
	 */
	public BoardPanel getBoardPanel(){
		return boardPanel;
	}
	
	/**
	 * This method checks whether the game is over or not.
	 * @return true if the game is over; false otherwise
	 */
	public boolean isGameOver(){
		return boardMatrix.isGameOver();
	}
	
	/**
	 * This method returns the next piece panel of the game
	 * @return The NextPieceAndScorePanel object containing the score, level informations and the next piece
	 */
	public NextPieceAndScorePanel getNextPieceAndScorePanel(){
		return nextPiecePanel;
	}

	/**
	 * This method gives the current level number of the game
	 * @return The level number of the game
	 */
	public int getLevelNo(){
		return levelNo;
	}

	/**
	 * This method returns the board's column length in pixels
	 * @return The length if the whole column in terms of pixels
	 */
	public int getBoardColumnLength(){
		return boardMatrix.getColumnLength();
	}

	/**
	 *This method returns the board's row length in pixels
	 * @return The length if the whole row in terms of pixels
	 */
	public int getBoardRowLength(){
		return boardMatrix.getRowLength();
	}
	
	/**
	 * This method returns the logical board (matrix) of the game
	 * @return The matrix (Board class object) holding the game information.
	 */
	public Board getBoardMatrix(){
		return boardMatrix;
	}

	/**
	 * This method takes the line number which is deleted from the logical game and will be deleted from the GUI as well.
	 * It ensures that the whole line will be deleted, and the score and the deleted line numbers will be renewed.
	 * @param lineNo The line number which will be deleted from the GUI
	 */
	public void eliminatedLine(int lineNo){
		boardPanel.clearEliminatedLine(lineNo);
		nextPiecePanel.increaseDeletedLineNo();
		nextPiecePanel.setCurrentScore(score);  // The score written in the next piece panel must always be renewed
		boardPanel.delayPieceSelection(); // This ensures that the next piece creation is delayed until the whole line is disappeared
	}
	
	/**
	 * This method checks whether the score of the player is enough to enter the highscore list or not
	 * @param score The score which will be checked
	 * @return true if the score is enough; false otherwise
	 */
	public boolean isScoreHighEnough(double score){
		highScores = new HighScores();
		return highScores.isScoreHighEnough(score);
	}

	/**
	 * This method increases the current score of the player by checking the total deleted line number.
	 * @param howManyLinesAreDeleted The number of lines deleted at a time.
	 */
	public void increaseScore(int howManyLinesAreDeleted){
		double speedInSeconds = speedInMilliseconds / 1000.0;

		if (howManyLinesAreDeleted == 1)
			score += 1.00/speedInSeconds;
		else if (howManyLinesAreDeleted == 2)
			score += (3 * 1.00/speedInSeconds);
		else if (howManyLinesAreDeleted == 3)
			score += (6 * 1.00/speedInSeconds);
		else
			score += (10 * 1.00/speedInSeconds);
	}

	/**
	 * This method gives the current score of the player
	 * @return The score of the player
	 */
	public double getScore(){
		return score;
	}
	
	/**
	 * This method ensures the mode of the game board is turned off
	 */
	public void shutDown(){
		boardPanel.setMode(false);
	}
	
	/**
	 * This static method takes a double and set its decimal places to the given number
	 * @param value The double whose decimal places will be set
	 * @param places The number of places
	 * @return The desired number with the given number of decimal places
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
