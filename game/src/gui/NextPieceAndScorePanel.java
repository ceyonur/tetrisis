package gui;

import game.Engine;

import java.awt.*;

import javax.swing.*;

import pieces.Piece;

public class NextPieceAndScorePanel extends JPanel {
	private Piece nextPiece;
	private int width;
	private int height;
	private SLabel score;
	private SLabel level;
	private SLabel deletedLines;
	private SLabel nextPieceLabel;
	
	private JPanel nextPiecePanel;
	private JPanel infoPanel;
	private JPanel buttonsPanel;
	
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
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
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
		gameControlButtonsPanel.setLayout(new GridLayout(1,2,20,0));
		gameControlButtonsPanel.setBackground(SColor.backgroundColor);
		
		SButton pauseButton = new SButton("pause", SButton.GAME_BUTTON);
		SButton quitButton = new SButton("quit", SButton.GAME_BUTTON);
		
		gameControlButtonsPanel.add(pauseButton);
		gameControlButtonsPanel.add(quitButton);
		
		JPanel gameControlButtonsPanelSuper = new JPanel();
		gameControlButtonsPanelSuper.setBackground(SColor.backgroundColor);
		gameControlButtonsPanelSuper.add(gameControlButtonsPanel);
		
		JPanel soundControlButtonsPanel = new JPanel();
		soundControlButtonsPanel.setLayout(new GridLayout(1,3,10,0));
		soundControlButtonsPanel.setBackground(SColor.backgroundColor);
		
		SButton allSoundsButton = new SButton(SButton.SOUND_BUTTON_UNMUTE);
		SButton musicButton = new SButton(SButton.SOUND_BUTTON_MUSIC_ON);
		SButton dotaEffectsButton = new SButton(SButton.SOUND_BUTTON_EFFECTS_ON);
		
		soundControlButtonsPanel.add(allSoundsButton);
		soundControlButtonsPanel.add(musicButton);
		soundControlButtonsPanel.add(dotaEffectsButton);
		
		JPanel soundControlButtonsPanelSuper = new JPanel();
		soundControlButtonsPanelSuper.setBackground(SColor.backgroundColor);
		soundControlButtonsPanelSuper.add(soundControlButtonsPanel);
		
		buttonsPanel.add(gameControlButtonsPanelSuper);
		buttonsPanel.add(soundControlButtonsPanelSuper);
		
		add(Box.createVerticalStrut(30));
		add(nextPiecePanel);
		add(infoPanel);
		add(Box.createVerticalStrut(30));
		add(buttonsPanel);
		
		increaseDeletedLineNo();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
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
		String scoreString = "score : " + Engine.round(newScore,2);
		score.setText(scoreString);
	}
	
	public void setLevel(int currentLevel){
		String levelString = "level : " + currentLevel;
		level.setText(levelString);
	}
	
	public void setDeletedLine(){
		String deletedLineString = "lines : " + deletedLineNo;
		deletedLines.setText(deletedLineString);
	}
	
	private void initializeNextPieceAreaValues(){
		nextPieceAreaWidth = (int) (width * 0.44);
		nextPieceAreaHeight = (int) (height * 0.33);
		nextPieceAreaX = (int) ((width - nextPieceAreaWidth) * 0.5);
		nextPieceAreaY = (int) ((height - nextPieceAreaHeight) * 0.18);
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
