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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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
	private LevelChoice levelChoiceObject = new LevelChoice();
	private boolean effectSelector = true;
	
	private Timer timer; 
	private Timer gameOverTimer;
	
	private MusicLoopPlayerListener musicPlayerListener;
	private Clip clipGameOver = null;
	private GameOverPlayerListener gameOverPlayerListener;
	
	private boolean mute = true;
//	private AudioInputStream audioStream;
//	private AudioInputStream gameOverStream;
	private ArrayList<String> sounds;
	private ArrayList<String> gameOverSounds;
	private Clip clipBackground;


	public PlayGUI(GUI ui){

		super();
		this.gui = ui;
		gameOverListener = new GameOverListener();
		gameOverPlayerListener = new GameOverPlayerListener();
		
		timerForCheckingGameOver = new Timer(500, gameOverListener);
		
		musicPlayerListener = new MusicLoopPlayerListener();
		timer= new Timer(5000, musicPlayerListener);
		gameOverTimer = new Timer(13503, gameOverPlayerListener);
		
		playBackground(!mute);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				engine.shutDown();
				gui.setEnabled(true);
				timerForCheckingGameOver.stop();
				timer.stop();
				gameOverTimer.stop();
				clipBackground.stop();
				if (clipGameOver != null)
					clipGameOver.stop();
				engine = null;
				dispose();
		    }
		});
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
		timerForCheckingGameOver.start();

		// Create the animation area used for output.
		board = engine.getBoardPanel();
		board.setCurrentPlayGUI(this);
		engine.getBoardMatrix().setCurrentPlayGUI(this);
		nextPiecePanel = engine.getNextPieceAndScorePanel();
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
		gameOverPanel.show();
		timer.stop();
		clipBackground.stop();
		//gameOverTimer.start();
		playGameOver(true);
		//clipGameOver.start();
		/*setContentPane(gameOverPanel);
		repaint();
		pack();*/
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

	public void playAudio2(boolean status) {
		String stringFile = "assets/sounds/oneKill.wav";

		AudioInputStream audioStream = null;

		try {
			if (status){
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				AudioFormat format =  audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip clip = (Clip)AudioSystem.getLine(info);
				clip.open(audioStream);
				FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-5.0f);
				clip.start();
				}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playAudio(boolean status, int counter) {
		String gongFile = " ";
		int level = levelChoiceObject.getLevel();

		if (counter == 1)
			gongFile = "assets/sounds/oneKill.wav";
		else if (counter == 2) {
			gongFile = "assets/sounds/DoubleKill.wav";
		} else if (counter == 3 && effectSelector) {
			gongFile = "assets/sounds/TripleKill.wav";
		} else if (counter == 3 && !effectSelector) {
			gongFile = "assets/sounds/MonsterKill.wav";
		} else if (counter == 4 && effectSelector && level < 5) {
			gongFile = "assets/sounds/Rampage.wav";
		} else if (counter == 4 && effectSelector) {
			gongFile = "assets/sounds/GodLike.wav";
		} else if (counter == 4 && !effectSelector && level == 5) {
			gongFile = "assets/sounds/Unstopable.wav";
		}

		AudioInputStream audioStream = null;

		try {
			if (status){
				audioStream = AudioSystem.getAudioInputStream(new File(gongFile));
				AudioFormat format =  audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip clip = (Clip)AudioSystem.getLine(info);
				if(effectSelector)
					effectSelector = false;
				else
					effectSelector = true;
				clip.open(audioStream);
				
				if (counter != 1 && status){
					clip.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playSitSound(boolean status) {
		String stringFile = "assets/sounds/lastMove.wav";

		AudioInputStream audioStream = null;

		try {
			if (status){
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				AudioFormat format =  audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip clip = (Clip)AudioSystem.getLine(info);
				clip.open(audioStream);
				FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-10.0f);
				clip.start();
				}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playRotate(boolean status) {
		String stringFile = "assets/sounds/rotate.wav";

		AudioInputStream audioStream = null;


		try {
			if (status){
			
				
				
				//Clip clipRotate = AudioSystem.getClip();
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				AudioFormat format =  audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip clipRotate = (Clip)AudioSystem.getLine(info);
				clipRotate.open(audioStream);
				FloatControl volume = (FloatControl) clipRotate.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-15.0f);
				clipRotate.start();
				}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playGameOver(boolean status){
		gameOverSounds = new ArrayList<String>();
			gameOverSounds.add("assets/sounds/gameOver/1.wav");
			gameOverSounds.add("assets/sounds/gameOver/2.wav");

		gameOverTimer.setInitialDelay(0);
		gameOverTimer.start();
		
	}

	public void playAudioFirstBlood(boolean status) {
		String stringFile = "assets/sounds/firstBlood.wav";

		AudioInputStream audioStream = null;

		try {
			if (status){
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				AudioFormat format =  audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				Clip clipFirstBlood = (Clip)AudioSystem.getLine(info);
			clipFirstBlood.open(audioStream);
			clipFirstBlood.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		// play the audio clip with the audioplayer class
	}
	private void playBackground(boolean status) {

		sounds = new ArrayList<String>();
		for (int i = 0; i < 29; i++) {
			sounds.add("assets/sounds/backGround/" + (i + 1) + ".wav");
		}
		//audioStream = null;
		timer.setInitialDelay(0);
		timer.start();
	}
	
	class MusicLoopPlayerListener implements  ActionListener{
		public MusicLoopPlayerListener(){ }
		
		
		public void actionPerformed(ActionEvent e) {
			AudioInputStream audioStream = null;
			
				 try {
				//	clipBackground = AudioSystem.getClip();
					String sound = sounds.remove(0);
				//	AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(sound));
					audioStream = AudioSystem.getAudioInputStream(new File(sound));
					AudioFormat format =  audioStream.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					clipBackground = (Clip)AudioSystem.getLine(info);
					clipBackground.open(audioStream);
					FloatControl volume = (FloatControl) clipBackground.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue(-5.0f);
					clipBackground.start();
					sounds.add(sound);
					
				} catch (LineUnavailableException e0) {
					e0.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
		}
	}
	class GameOverPlayerListener implements  ActionListener{
		public GameOverPlayerListener(){ }
		
		
		public void actionPerformed(ActionEvent e) {
			
				 try {
					 clipGameOver = AudioSystem.getClip();
					String sound = gameOverSounds.remove(0);
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(sound));
					clipGameOver.open(audioStream);
					FloatControl volume = (FloatControl) clipGameOver.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue(-10.0f);
					clipGameOver.start();
					gameOverSounds.add(sound);
					
				} catch (LineUnavailableException e0) {
					e0.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
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
