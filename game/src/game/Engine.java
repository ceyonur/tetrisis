package game;

import gui.BoardPanel;
import gui.NextPieceAndScorePanel;
import highscores.HighScores;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Random;

import pieces.ITetriminos;
import pieces.ITriminos;
import pieces.JTetriminos;
import pieces.JTriminos;
import pieces.LTetriminos;
import pieces.OTetriminos;
import pieces.Piece;
import pieces.RTriminos;
import pieces.STetriminos;
import pieces.TTetriminos;
import pieces.ZTetriminos;
import settings.KeyConfigure;
import settings.PieceChoice;
import settings.Settings;

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
				currentPiece = chooseRandomPiece();
			} else {
				currentPiece = nextPiecePanel.getPiece();
			}
			Piece randomPiece = chooseRandomPiece();
			nextPiecePanel.setPiece(randomPiece);
			while (currentPiece.getY() * -1 > currentPiece.boundingBox().getHeight())
				currentPiece.moveABlockDown();
			boardPanel.addPiece(currentPiece);
		} else {
			boardPanel.setMode(false);
		}
		nextPiecePanel.setCurrentScore(score);
	}

	private Piece chooseRandomPiece(){
		Random randomGenerator = new Random(System.currentTimeMillis());

		Color randomColor = null;
		int randomNumberForColor = randomGenerator.nextInt(9) + 1;
		switch (randomNumberForColor){
		case 1: randomColor = Color.BLUE; break;
		case 2: randomColor = Color.CYAN; break;
		case 3: randomColor = Color.DARK_GRAY; break;
		case 4: randomColor = Color.GREEN; break;
		case 5: randomColor = Color.MAGENTA; break;
		case 6: randomColor = Color.ORANGE; break;
		case 7: randomColor = Color.PINK; break;
		case 8: randomColor = Color.RED; break;
		default: randomColor = Color.YELLOW; break;
		}

		Piece randomPiece = null;
		int randomNumberForPiece = 0;
		if (pieceChoice.hasBoth())
			randomNumberForPiece = randomGenerator.nextInt(10) + 1;
		else if (pieceChoice.hasTetriminos())
			randomNumberForPiece = randomGenerator.nextInt(7) + 1;
		else if (pieceChoice.hasTriminos())
			randomNumberForPiece = randomGenerator.nextInt(3) + 8;
		
		switch (randomNumberForPiece){
		case 1: randomPiece = new ZTetriminos(0,0,randomColor); break;
		case 2: randomPiece = new STetriminos(0,0,randomColor); break;
		case 3: randomPiece = new OTetriminos(0,0,randomColor); break;
		case 4: randomPiece = new TTetriminos(0,0,randomColor); break;
		case 5: randomPiece = new JTetriminos(0,0,randomColor); break;
		case 6: randomPiece = new LTetriminos(0,0,randomColor); break;
		case 7: randomPiece = new ITetriminos(0,0,randomColor); break;
		case 8: randomPiece = new ITriminos(0,0,randomColor); break;
		case 9: randomPiece = new JTriminos(0,0,randomColor); break;
		default: randomPiece = new RTriminos(0,0,randomColor); break;
		}
		
		int appearX = ((int) (getBoardColumnLength() - randomPiece.boundingBox().width)/2);
		while (appearX % randomPiece.getBlocks().get(1).getBlockSize() != 0)
			++appearX;
			
		int appearY = -4 * randomPiece.getBlocks().get(1).getBlockSize();
		randomPiece.move(appearX, appearY);
		
		return randomPiece;
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
}
