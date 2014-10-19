package gui;

import javax.swing.*;

import javax.swing.border.BevelBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import settings.LevelChoice;
import game.Board;
import game.Engine;
import highscores.Player;

import java.awt.*;
import java.awt.event.*;

public class PlayGUI extends JFrame {
	protected Engine engine;
	private BoardPanel board;
	private NextPieceAndScorePanel nextPiecePanel;
	private GameOverListener gameOverListener;
	private JPanel realPane;
	private JPanel generalPane;
	private Timer timerForCheckingGameOver;
	private int width;
	private int height;
	private GUI gui;

	private AudioPlayers audioPlayers;


	public PlayGUI(GUI ui){

		super();
		this.gui = ui;
		gameOverListener = new GameOverListener();

		
		timerForCheckingGameOver = new Timer(500, gameOverListener);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				closeFrame();
		    }
		});
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
		audioPlayers = engine.getAudioPlayers();
		audioPlayers.playPlayGUIBackground(true);
		timerForCheckingGameOver.start();

		// Create the animation area used for output.
		board = engine.getBoardPanel();
		board.setCurrentPlayGUI(this);
		engine.getBoardMatrix().setCurrentPlayGUI(this);
		nextPiecePanel = engine.getNextPieceAndScorePanel();
		nextPiecePanel.setPlayGUI(this);
		// Put it in a scrollPane, (this makes a border)
		JScrollPane gameBoard = new JScrollPane(board);
		JScrollPane nextPieceAndScorePanel = new JScrollPane(nextPiecePanel);
		/*
		gameBoard.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED, Color.GRAY,
						Color.DARK_GRAY)));
		nextPieceAndScorePanel.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createBevelBorder(
						BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY)));
		*/
		realPane = new JPanel();
		realPane.setLayout(new GridLayout(1, 2));
		realPane.add(gameBoard);
		realPane.add(nextPieceAndScorePanel);
		// Lay out the content pane.
		generalPane = new JPanel();
		generalPane.setLayout(new BorderLayout());
		generalPane.setPreferredSize(new Dimension(engine
				.getBoardColumnLength() * 2, engine.getBoardRowLength()));
		generalPane.add(realPane, BorderLayout.CENTER);
		add(generalPane);

		width = engine.getBoardColumnLength();
		height = engine.getBoardRowLength();
		
		pack();
	}

	public void showGameOver(){

		JFrame gameOverPanel = new GameOverPanel(engine.getScore(), engine.getLevelNo());
		audioPlayers.disablePlayGUIBackgroundSound();
		audioPlayers.playGameOver(true);
		setContentPane(gameOverPanel);
		repaint();
		pack();
	}

	class GameOverListener implements ActionListener {

		public GameOverListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (engine.isGameOver()) {
				timerForCheckingGameOver.stop();
				showGameOver();
			}
		}
	}
	
	public void closeFrame(){
		engine.pause();
		gui.setEnabled(true);
		timerForCheckingGameOver.stop();
		audioPlayers.disableAllSounds();
		engine = null;
		dispose();
	}

	public class GameOverPanel extends JFrame {
		
		JPanel submissionContainer;
		
		public GameOverPanel(double score, int level) {
			super();

			getContentPane().setBackground(SColor.backgroundColor);
			setPreferredSize(new Dimension(400,400));
			setResizable(false);
			setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

			
			JPanel headerContainer = new JPanel();
			headerContainer.setBackground(SColor.backgroundColor);
			JPanel infoContainer = new JPanel();
			infoContainer.setBackground(SColor.backgroundColor);
			submissionContainer = new JPanel();
			submissionContainer.setBackground(SColor.backgroundColor);
			JPanel buttonsContainer = new JPanel();
			buttonsContainer.setBackground(SColor.backgroundColor);

			SLabel gameOverLabel = new SLabel("game over!", SLabel.GAMEOVER_HEADER_LABEL);
			headerContainer.add(gameOverLabel);
			
			String infoScore = "your score: " + Engine.round(score,2);
			SLabel scoreLabel = new SLabel(infoScore, SLabel.GAMEOVER_INFO_LABEL);
			infoContainer.add(scoreLabel);
			
			JPanel submissionPanel = new JPanel();
			submissionPanel.setLayout(new BoxLayout(submissionPanel, BoxLayout.X_AXIS));
			submissionPanel.setBackground(SColor.backgroundColor);
			
			SLabel nameLabel = new SLabel("enter your name to high scores:", SLabel.GAMEOVER_SUBMISSION_LABEL, SwingConstants.CENTER);
			JTextField nameInput = new JTextField(10);
			nameInput.setDocument(new JTextFieldCharacterLimit(10));
			SButton nameSubmit = new SButton("submit", SButton.GAMEOVER_SUBMISSION_BUTTON);
			nameSubmit.addActionListener(new SubmitHandler(nameInput, score));
			

			submissionPanel.add(Box.createHorizontalStrut(50));
			submissionPanel.add(nameInput);
			submissionPanel.add(Box.createHorizontalStrut(10));
			submissionPanel.add(nameSubmit);
			submissionPanel.add(Box.createHorizontalStrut(50));
			
			submissionContainer.setLayout(new GridLayout(2,1,0,10));
			submissionContainer.add(nameLabel);
			submissionContainer.add(submissionPanel);
			
			buttonsContainer.add(addButtons());
						
			getContentPane().add(headerContainer);
			getContentPane().add(Box.createVerticalStrut(30));
			getContentPane().add(infoContainer);
			getContentPane().add(Box.createVerticalStrut(30));
			if (engine.isScoreHighEnough(score)) {
				getContentPane().add(submissionContainer);
				getContentPane().add(Box.createVerticalStrut(30));
			}
			getContentPane().add(buttonsContainer);
			
			pack();
		}

		protected JPanel addButtons() {

			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(3, 1,0,-5));
			buttons.setPreferredSize(new Dimension(240,120));
			buttons.setBackground(Color.WHITE);

			SButton restartButton = new SButton("restart", SButton.GAMEOVER_BUTTON);
			restartButton.setToolTipText("Restart the Tetris/Trisis game");
			// when this button is pushed it calls animationWindow.setMode(true)
			restartButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gui.showPlay();
					audioPlayers.disableGameOverSound();
					dispose();
				}
			});
			buttons.add(restartButton);

			SButton returnButton = new SButton("return to menu", SButton.GAMEOVER_BUTTON);
			returnButton.setToolTipText("Back to the main menu");
			// when this button is pushed it calls
			// animationWindow.setMode(false)
			returnButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gui.setEnabled(true);
					audioPlayers.disableGameOverSound();
					dispose();
				}
			});
			buttons.add(returnButton);

			SButton exitButton = new SButton("exit game", SButton.GAMEOVER_BUTTON);
			exitButton.setToolTipText("Quit the program");
			exitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			buttons.add(exitButton);

			return buttons;

		}
		
		class SubmitHandler implements ActionListener{
			private JTextField nameField;
			private double score;
			
			public SubmitHandler(JTextField name, double score){
				nameField = name;
				this.score = score;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SButton button = (SButton) e.getSource();
				/*
				callerButton.setText("Submitted");
				callerButton.setEnabled(false);
				*/
				String name = nameField.getText();
				nameField.setEnabled(false);
				button.setEnabled(false);
				
				
				Player newPlayer = new Player(name, Engine.round(score, 2));
				gui.addPlayerToHighScoreList(newPlayer);
				
				((SLabel) submissionContainer.getComponent(0)).setText("your score is summitted.");
				submissionContainer.remove(nameField);
				submissionContainer.remove(button);
				submissionContainer.repaint();
				repaint();
			}
		}
	}
	
	class JTextFieldCharacterLimit extends PlainDocument {
		  private int limit;

		  JTextFieldCharacterLimit(int limit) {
		   super();
		   this.limit = limit;
		   }

		  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
		    if (str == null) return;

		    if ((getLength() + str.length()) <= limit) {
		      super.insertString(offset, str, attr);
		    }
		  }
		}
}
