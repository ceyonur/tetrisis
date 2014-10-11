package game;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import settings.*;
import pieces.*;
import gui.BoardPanel;

public class Engine {
	private Board boardMatrix; // The field for board object of the game
	private KeyConfigure keys; // The field for keys of the actions in the game
	private PieceChoice pieceChoice; // The field for the tetriminos/triminos choice
	private int levelNo; // The field for the number of the level to display
	private int speed; // The field for the speed of the game (in milliseconds)
	private Piece currentPiece; // The current piece
	private BoardPanel boardPanel; // The panel for the board

	/**
	 * The constructor of the Engine class. Creates the board and takes the settings (if nothing changed, default ones will be used)
	 * @param settings The settings of the game (can be default)
	 */
	public Engine(Settings settings){
		boardMatrix = new Board(settings.getSizeChoice().getRow(), settings.getSizeChoice().getColumn(), this);
		levelNo = settings.getLevelChoice().getLevel();
		speed = (int) (1000 * settings.getLevelChoice().getSpeed());
		keys = settings.getKeyConfigure();
		pieceChoice = settings.getPieceChoice();
		
		currentPiece = null;
		
		boardPanel = new BoardPanel(keys, speed, this, boardMatrix);
		
		play();
	}

	/**
	 * The default constructor of the game. It creates a new Settings object, so everything will be default.
	 */
	public Engine(){
		this(new Settings());
	}

	public void play(){
		if (currentPiece !=null){
			boardMatrix.updateBoard(currentPiece.getLocationOnMatrix(), currentPiece.getColorAsInteger());
		}
			
		Piece randomPiece = chooseRandomPiece();
		currentPiece = randomPiece;
		// Find the initial location for the pieces
		
		boardPanel.addPiece(currentPiece);
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

		return randomPiece;
	}
	
	public BoardPanel getBoardPanel(){
		return boardPanel;
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
	
	public void eliminatedLine(int lineNo){
		boardPanel.clearEliminatedLine(lineNo);
	}
}
