package gui;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import settings.LevelChoice;
import game.Board;
import game.Engine;
import highscores.Player;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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

	private Clip clipBackground;


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
		JPanel gameOverPanel = new GameOverPanel(engine.getScore(), engine.getLevelNo());
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

	public class GameOverPanel extends JPanel {
		private JTextField getName;
		private JButton submit;

		private JLabel gameOverLabel;

		public GameOverPanel(double score, int level) {
			super();

			setBackground(Color.WHITE);
			setSize(width * 2, height);

			String infoScore = "Score: " + Engine.round(score,2);
			String infoLevel = "Level: " + level;

			setLayout(new GridLayout(4, 1));

			gameOverLabel = new JLabel("Game Over!!");
			gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
			gameOverLabel.setFont(new Font(gameOverLabel.getFont().getFamily(),
					gameOverLabel.getFont().getStyle(), 50));
			add(gameOverLabel);

			JPanel getNameForHighScoreTable = new JPanel();
			getNameForHighScoreTable.setLayout(new GridLayout(1, 3));
			getNameForHighScoreTable.setBackground(Color.WHITE);
			JLabel name = new JLabel("Enter your name: ");
			getNameForHighScoreTable.add(name);
			getName = new JTextField();
			getNameForHighScoreTable.add(getName);
			submit = new JButton("Submit");
			submit.addActionListener(new SubmitHandler(getName,score));
			getNameForHighScoreTable.add(submit);
			add(getNameForHighScoreTable);

			if (engine.isScoreHighEnough(score)) {
				getNameForHighScoreTable.setVisible(true);
			} else {
				getNameForHighScoreTable.setVisible(false);
			}

			JPanel info = new JPanel();
			info.setLayout(new GridLayout(2, 1));
			info.setBackground(Color.WHITE);
			JLabel infoScoreLabel = new JLabel(infoScore);
			infoScoreLabel.setHorizontalAlignment(JLabel.CENTER);
			infoScoreLabel.setFont(new Font(infoScoreLabel.getFont()
					.getFamily(), infoScoreLabel.getFont().getStyle(), 40));
			info.add(infoScoreLabel);
			JLabel infoLevelLabel = new JLabel(infoLevel);
			infoLevelLabel.setHorizontalAlignment(JLabel.CENTER);
			infoLevelLabel.setFont(new Font(infoLevelLabel.getFont()
					.getFamily(), infoLevelLabel.getFont().getStyle(), 30));
			info.add(infoLevelLabel);
			add(info);

			add(addButtons());
		}

		protected JPanel addButtons() {

			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(3, 1));
			buttons.setBackground(Color.WHITE);

			JButton button = new JButton("Restart Game");
			button.setToolTipText("Restart the Tetris/Trisis game");
			// when this button is pushed it calls animationWindow.setMode(true)
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gui.showPlay();
					audioPlayers.disableGameOverSound();
					dispose();
				}
			});
			buttons.add(button);

			JButton button2 = new JButton("Return To Main Menu");
			button2.setToolTipText("Back to the main menu");
			// when this button is pushed it calls
			// animationWindow.setMode(false)
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gui.setEnabled(true);
					audioPlayers.disableGameOverSound();
					dispose();
				}
			});
			buttons.add(button2);

			JButton button3 = new JButton("Quit");
			button3.setToolTipText("Quit the program");
			button3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			buttons.add(button3);

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
				JButton callerButton = (JButton) e.getSource();
				callerButton.setText("Submitted");
				callerButton.setEnabled(false);
				String name = nameField.getText();
				nameField.setEnabled(false);
				Player newPlayer = new Player(name, Engine.round(score, 2));
				gui.addPlayerToHighScoreList(newPlayer);
			}
		}
	}	
}
