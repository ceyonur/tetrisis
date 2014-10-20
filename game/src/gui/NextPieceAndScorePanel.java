package gui;

import game.Engine;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import pieces.Piece;

/**
 * This JPanel extending class shows in the actual game and indicates the score, next piece, deleted line etc. information to the player.
 * @author bedirhancaldir
 */
public class NextPieceAndScorePanel extends JPanel {
	private Engine callerEngine; // The Engine object generating this object
	private PlayGUI playGUI; // The JFrame that contains this panel
	private AudioPlayers audioPlayers;
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
	
	private boolean stateOfBackgroundMusic = true; // The current state of the background music (if true, then it plays)
	private boolean stateOfDotaEffects = true; // The current state of the dota effects (if true, then it plays)
	private boolean stateOfEffects = true; // The current state of the effects (if true, then it plays)

	/**
	 * The constructor of the class. 
	 * @param width The width of the panel
	 * @param height The height of the panel
	 */
	public NextPieceAndScorePanel(int width, int height, Engine engine){
		super();
		setBackground(SColor.sidePanelColor);
		this.width = width;
		this.height = height;
		setSize(width, height);
		callerEngine = engine;
		
		initializeNextPieceAreaValues();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		createInfoPanel();
		createNextPiecePanel();
		createButtonsPanel();
		
		add(Box.createVerticalStrut(30));
		add(nextPiecePanel);
		add(infoPanel);
		add(Box.createVerticalStrut(30));
		add(buttonsPanel);
		
		increaseDeletedLineNo();
	}
	
	/**
	 * This method creates the info panel of the panel
	 */
	private void createInfoPanel(){
		infoPanel = new JPanel();
		infoPanel.setBackground(getBackground());
		infoPanel.setLayout(new GridLayout(3,1));
		
		deletedLines = new SLabel(SLabel.SIDE_PANEL_LINES, SwingConstants.CENTER);
		infoPanel.add(deletedLines);
		
		score = new SLabel(SLabel.SIDE_PANEL_LINES, SwingConstants.CENTER);
		infoPanel.add(score);
		
		level = new SLabel(SLabel.SIDE_PANEL_LINES, SwingConstants.CENTER);
		infoPanel.add(level);
	}
	
	/**
	 * This method creates the next piece panel of the panel
	 */
	private void createNextPiecePanel(){
		nextPiecePanel = new JPanel();
		nextPiecePanel.setBackground(getBackground());
		nextPiecePanel.setLayout(new BorderLayout());
		
		nextPieceLabel = new SLabel("next piece :", SLabel.SIDE_PANEL_NEXT, JLabel.CENTER);
		nextPiecePanel.add(nextPieceLabel, BorderLayout.NORTH);
		
		JPanel nextPieceContainerSuper = new JPanel();
		nextPieceContainerSuper.setBackground(SColor.backgroundColor);
		nextPiecePanel.add(nextPieceContainerSuper, BorderLayout.CENTER);
		
		JPanel nextPieceContainer = new JPanel();
		nextPieceContainer.setBackground(Color.white);
		nextPieceContainer.setPreferredSize(new Dimension(nextPieceAreaWidth, nextPieceAreaWidth));
		nextPieceContainerSuper.add(nextPieceContainer);
	}
	
	/**
	 * This method creates the buttons panel of the panel
	 */
	private void createButtonsPanel(){
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(getBackground());
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
		JPanel gameControlButtonsPanel = new JPanel();
		gameControlButtonsPanel.setLayout(new GridLayout(1,2,20,0));
		gameControlButtonsPanel.setBackground(SColor.backgroundColor);
		
		SButton pauseButton = new SButton("pause", SButton.GAME_BUTTON);
		pauseButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (callerEngine.isPaused())
						callerEngine.pause();
					else
						callerEngine.unpause();
				}
			});
		
		SButton quitButton = new SButton("quit", SButton.GAME_BUTTON);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGUI.closeFrame();
			}
		});
		
		gameControlButtonsPanel.add(pauseButton);
		gameControlButtonsPanel.add(quitButton);
		
		JPanel gameControlButtonsPanelSuper = new JPanel();
		gameControlButtonsPanelSuper.setBackground(SColor.backgroundColor);
		gameControlButtonsPanelSuper.add(gameControlButtonsPanel);
		
		JPanel soundControlButtonsPanel = new JPanel();
		soundControlButtonsPanel.setLayout(new GridLayout(1,3,10,0));
		soundControlButtonsPanel.setBackground(SColor.backgroundColor);
		
		SButton allSoundsButton = new SButton(SButton.SOUND_BUTTON_UNMUTE);
		allSoundsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SButton callerButton = (SButton) e.getSource();
				callerButton.changeState();
				stateOfEffects = !stateOfEffects;
				SButton dotaButton = (SButton) ((JPanel) ((JPanel) buttonsPanel.getComponent(1)).getComponent(0)).getComponent(2);
				SButton musicButton = (SButton) ((JPanel) ((JPanel) buttonsPanel.getComponent(1)).getComponent(0)).getComponent(1);
				if (stateOfDotaEffects != stateOfEffects){
					dotaButton.changeState();
					stateOfDotaEffects = stateOfEffects;
				}
				
				if (stateOfBackgroundMusic != stateOfEffects){
					musicButton.changeState();
					stateOfBackgroundMusic = stateOfEffects;
				}
				
				if (!stateOfEffects)
					dotaButton.setEnabled(false);
				else
					dotaButton.setEnabled(true);
				
				if (!stateOfEffects)
					musicButton.setEnabled(false);
				else
					musicButton.setEnabled(true);
				
				audioPlayers.disableOrEnableAllSounds(stateOfEffects);
				callerEngine.giveTheKeyboardFocusToGameBoard();
			}
		});
		
		SButton musicButton = new SButton(SButton.SOUND_BUTTON_MUSIC_ON);
		musicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SButton callerButton = (SButton) e.getSource();
				callerButton.changeState();
				stateOfBackgroundMusic = !stateOfBackgroundMusic;
				audioPlayers.disableOrEnablePlayGUIBackgroundSound(stateOfBackgroundMusic);
				callerEngine.giveTheKeyboardFocusToGameBoard();
			}
		});
		
		SButton dotaEffectsButton = new SButton(SButton.SOUND_BUTTON_EFFECTS_ON);
		dotaEffectsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SButton callerButton = (SButton) e.getSource();
				callerButton.changeState();
				stateOfDotaEffects = !stateOfDotaEffects;
				audioPlayers.disableOrEnableDotaEffects(stateOfDotaEffects);
				callerEngine.giveTheKeyboardFocusToGameBoard();
			}
		});
		
		soundControlButtonsPanel.add(allSoundsButton);
		soundControlButtonsPanel.add(musicButton);
		soundControlButtonsPanel.add(dotaEffectsButton);
		
		JPanel soundControlButtonsPanelSuper = new JPanel();
		soundControlButtonsPanelSuper.setBackground(SColor.backgroundColor);
		soundControlButtonsPanelSuper.add(soundControlButtonsPanel);
		
		buttonsPanel.add(gameControlButtonsPanelSuper);
		buttonsPanel.add(soundControlButtonsPanelSuper);
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
	
	/**
	 * This method provides the JFrame that contains this panel itself
	 * @param play The JFrame containing this panel
	 */
	public void setPlayGUI(PlayGUI play){
		playGUI = play;
	}
	
	/**
	 * This method sets the given audioPlayer as the global audio player 
	 * @param audioPlayers The audioPlayers object of this class
	 */
	public void setAudioPlayers(AudioPlayers audioPlayers){
		this.audioPlayers = audioPlayers;
	}
}
