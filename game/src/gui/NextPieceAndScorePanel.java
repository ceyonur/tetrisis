package gui;

import game.Engine;

import java.awt.*;

import javax.swing.*;

import pieces.Piece;

public class NextPieceAndScorePanel extends JPanel {
	private Piece nextPiece; // The next piece of the game (shown in this panel)
	private int width; // The width of the panel
	private int height; // The height of the panel
	private SLabel score; // The label for current score
	private SLabel level; // The label for current level
	private SLabel deletedLines; // The label for current deleted line information
	private SLabel nextPieceLabel; // The label for "next piece:" string
	
	private JPanel nextPiecePanel; // The panel containing the next piece of the game
	private JPanel infoPanel; // The panel containing the score, line and level informations
	private JPanel buttonsPanel; // The panel containing the sound on/off buttons
	
	private int deletedLineNo = -1; // The initial deleted line number
	
	private boolean currentState = false; // The visibility state of the next piece (false when the game is paused)
	
	private int nextPieceAreaWidth; // The width of the area containing the next piece of the game
	private int nextPieceAreaHeight; // The height of the area containing the next piece of the game
	private int nextPieceAreaX; // The x-axis of the area containing the next piece of the game
	private int nextPieceAreaY; // The y-axis of the area containing the next piece of the game
	private double pieceOldPositionX; // The x-axis of the first position of the piece which is positioned on the next piece area
	private double pieceOldPositionY; // The y-axis of the first position of the piece which is positioned on the next piece area

	/**
	 * The constructor of the class. 
	 * @param width The width of the panel
	 * @param height The height of the panel
	 */
	public NextPieceAndScorePanel(int width, int height){
		super();
		setBackground(SColor.sidePanelColor);
		this.width = width;
		this.height = height;
		setSize(width, height);
		
		initializeNextPieceAreaValues();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		nextPiecePanel = new JPanel();
		nextPiecePanel.setBackground(getBackground());
		nextPiecePanel.setLayout(new BorderLayout());
		
		infoPanel = new JPanel();
		infoPanel.setBackground(getBackground());
		infoPanel.setLayout(new GridLayout(3,1));
		
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(getBackground());
		buttonsPanel.setLayout(new GridLayout(2,1));
		
		nextPieceLabel = new SLabel("next piece :", SLabel.SIDE_PANEL_NEXT, JLabel.CENTER);
		nextPiecePanel.add(nextPieceLabel, BorderLayout.NORTH);
				
		JPanel nextPieceContainerSuper = new JPanel();
		nextPieceContainerSuper.setBackground(SColor.backgroundColor);
		nextPiecePanel.add(nextPieceContainerSuper, BorderLayout.CENTER);
		
		JPanel nextPieceContainer = new JPanel();
		nextPieceContainer.setBackground(Color.white);
		nextPieceContainer.setPreferredSize(new Dimension(nextPieceAreaWidth, nextPieceAreaWidth));
		nextPieceContainerSuper.add(nextPieceContainer);
		
		deletedLines = new SLabel(SLabel.SIDE_PANEL_LINES, SwingConstants.CENTER);
		infoPanel.add(deletedLines);
		
		score = new SLabel(SLabel.SIDE_PANEL_LINES, SwingConstants.CENTER);
		infoPanel.add(score);
		
		level = new SLabel(SLabel.SIDE_PANEL_LINES, SwingConstants.CENTER);
		infoPanel.add(level);
		
		JPanel gameControlButtonsPanel = new JPanel();
		gameControlButtonsPanel.setLayout(new GridLayout(1,2));
		gameControlButtonsPanel.setBackground(SColor.backgroundColor);
		
		SButton pauseButton = new SButton("pause", SButton.GAME_BUTTON);
		SButton quitButton = new SButton("quit", SButton.GAME_BUTTON);
		
		gameControlButtonsPanel.add(pauseButton);
		gameControlButtonsPanel.add(quitButton);
		
		JPanel soundControlButtonsPanel = new JPanel();
		soundControlButtonsPanel.setLayout(new GridLayout(1,3));
		soundControlButtonsPanel.setBackground(SColor.backgroundColor);
		
		SButton allSoundsButton = new SButton(SButton.SOUND_BUTTON_UNMUTE);
		SButton musicButton = new SButton(SButton.SOUND_BUTTON_MUSIC_ON);
		SButton dotaEffectsButton = new SButton(SButton.SOUND_BUTTON_EFFECTS_ON);
		
		soundControlButtonsPanel.add(allSoundsButton);
		soundControlButtonsPanel.add(musicButton);
		soundControlButtonsPanel.add(dotaEffectsButton);
		
		buttonsPanel.add(gameControlButtonsPanel);
		buttonsPanel.add(soundControlButtonsPanel);
		
		add(Box.createVerticalStrut(30));
		add(nextPiecePanel);
		add(infoPanel);
		add(buttonsPanel);
		
		increaseDeletedLineNo();
	}
	
	/**
	 * This method paints and refresh the graphics of the panel
	 */
	public void paint(Graphics g) {
		super.paint(g);
		if (currentState){
			nextPiece.paint(g);
		}
	}
	
	/**
	 * This method sets the next piece of the game to see in this panel
	 * @param piece The next piece of the game
	 */
	public void setPiece(Piece piece){
		nextPiece = piece;
		pieceOldPositionX = nextPiece.boundingBox().getX();
		pieceOldPositionY = nextPiece.boundingBox().getY();
		int pieceNewXValue = (int) (nextPieceAreaX + (int) ((nextPieceAreaWidth - nextPiece.boundingBox().getWidth()) /2) - pieceOldPositionX);
		int pieceNewYValue = (int) (nextPieceAreaY + (int) ((nextPieceAreaHeight - nextPiece.boundingBox().getHeight()) /2) - pieceOldPositionY);
		nextPiece.move(pieceNewXValue, pieceNewYValue);
		repaint();
	}
	
	/**
	 * This method gives the next piece of the current instant
	 * @return The next piece of the game
	 */
	public Piece getPiece(){
		int pieceOldXValue = -1 * ((int) (nextPieceAreaX + (int) ((nextPieceAreaWidth - nextPiece.boundingBox().getWidth()) /2) - pieceOldPositionX));
		int pieceOldYValue = -1 * ((int) (nextPieceAreaY + (int) ((nextPieceAreaHeight - nextPiece.boundingBox().getHeight()) /2) - pieceOldPositionY));
		nextPiece.move(pieceOldXValue,pieceOldYValue);
		return nextPiece;
	}
	
	/**
	 * This method sets the current score to display on this panel
	 * @param newScore The current score of the game
	 */
	public void setCurrentScore(double newScore){
		String scoreString = "score : " + Engine.round(newScore,2);
		score.setText(scoreString);
	}
	
	/**
	 * This method sets the level of the game to display on this panel
	 * @param currentLevel The current level of the game
	 */
	public void setLevel(int currentLevel){
		String levelString = "level : " + currentLevel;
		level.setText(levelString);
	}
	
	/**
	 * This method sets the string of deleted lines to display on this panel
	 */
	public void setDeletedLine(){
		String deletedLineString = "lines : " + deletedLineNo;
		deletedLines.setText(deletedLineString);
	}
	
	/**
	 * This method sets the rectangle of the next piece displaying area
	 */
	private void initializeNextPieceAreaValues(){
		nextPieceAreaWidth = (int) (width * 0.44);
		nextPieceAreaHeight = (int) (height * 0.33);
		nextPieceAreaX = (int) ((width - nextPieceAreaWidth) * 0.5);
		nextPieceAreaY = (int) ((height - nextPieceAreaHeight) * 0.18);
	}
	
	/**
	 * This method sets the visibility of the next piece
	 * @param state The visibility state of the next piece
	 */
	public void setVisibility(boolean state){
		currentState = state;
		repaint();
	}
	
	/**
	 * This method sets the number of deleted lines
	 */
	public void increaseDeletedLineNo(){
		deletedLineNo++;
		setDeletedLine();
	}
}
