package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import settings.LevelChoice;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import game.Board;
import game.Engine;

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
	
	private Timer timer; 
	
	private AnimationEventListener eventListener;
	
	private boolean mute = true;
	private AudioInputStream audioStream;
	private ArrayList<String> sounds;


	public PlayGUI(GUI ui){

		super();
		this.gui = ui;
		gameOverListener = new GameOverListener();
		timerForCheckingGameOver = new Timer(500, gameOverListener);
		
		eventListener = new AnimationEventListener();
		timer= new Timer(5000, eventListener);
		
		playBackground(!mute);
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
		gameBoard.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED, Color.GRAY,
						Color.DARK_GRAY)));
		nextPieceAndScorePanel.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createBevelBorder(
						BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY)));
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
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gui.setEnabled(true);
				dispose();
		    }
		});
		
		pack();
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}

	public void showGameOver() {
		JPanel gameOverPanel = new GameOverPanel(engine.getScore(),
				engine.getLevelNo());
		removeAll();
		add(gameOverPanel);
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

	public class GameOverPanel extends JPanel {
		private JTextField getName;
		private JButton submit;

		private JLabel gameOverLabel;

		public GameOverPanel(double score, int level) {
			super();

			setBackground(Color.WHITE);
			setSize(width * 2, height);

			String infoScore = "Score: " + score;
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

		public void paint(Graphics g) {
			super.paint(g);
			gameOverLabel.setLocation(10, 10);
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
					//
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
					//
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
	}

	public void playAudio2(boolean status) {
		String stringFile = "assets/sounds/oneKill.wav";

		AudioInputStream audioStream = null;

		try {
			if (status){
				Clip clip = AudioSystem.getClip();
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				clip.open(audioStream);
				clip.start();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// play the audio clip with the audioplayer class
		if (status)
			AudioPlayer.player.start(audioStream);

	}

	public void playAudio(boolean status, int counter) {
		String gongFile = " ";
		int a = 1;
		int b = 1;

		int level = levelChoiceObject.getLevel();
		if (level >= 3)
			a = a * -1;

		if (level == 5)
			b = b * -1;

		if (counter == 1)
			gongFile = "assets/sounds/oneKill.wav";
		else if (counter == 2) {
			gongFile = "assets/sounds/DoubleKill.wav";
		} else if (counter == 3 && a > 0) {
			gongFile = "assets/sounds/TripleKill.wav";
		} else if (counter == 3 && a < 0) {
			gongFile = "assets/sounds/MonsterKill.wav";
		} else if (counter == 4 && a > 0 && level < 5) {
			gongFile = "assets/sounds/Rampage.wav";
		} else if (counter == 4 && a < 0 && level < 5) {
			gongFile = "assets/sounds/GodLike.wav";
		} else if (counter == 4 && b < 0) {
			gongFile = "assets/sounds/GodLike.wav";
		} else if (counter == 4 && b > 0) {
			gongFile = "assets/sounds/Unstopable.wav";
		}

		AudioInputStream audioStream = null;

		try {
			if (status){
				Clip clip = AudioSystem.getClip();
				audioStream = AudioSystem.getAudioInputStream(new File(gongFile));
				clip.open(audioStream);
				if (counter != 1 && status) 
				clip.start();
				
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playSitSound(boolean status) {
		String stringFile = "assets/sounds/lastMove.wav";

		AudioInputStream audioStream = null;

		try {
			if (status){
				Clip clip = AudioSystem.getClip();
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				clip.open(audioStream);
				clip.start();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playRotate(boolean status) {
		String stringFile = "assets/sounds/rotate.wav";

		AudioInputStream audioStream = null;


		try {
			if (status){
				Clip clip = AudioSystem.getClip();
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				clip.open(audioStream);
				clip.start();
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void playAudioFirstBlood(boolean status) {
		String stringFile = "assets/sounds/firstBlood.wav";

		AudioInputStream audioStream = null;

		try {
			if (status){
			Clip clip = AudioSystem.getClip();
			audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
			clip.open(audioStream);
			clip.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// play the audio clip with the audioplayer class
	}
	private void playBackground(boolean status) {

		sounds = new ArrayList<String>();
		for (int i = 0; i < 29; i++) {
			sounds.add("assets/sounds/backGround/" + (i + 1) + ".wav");
		}
		audioStream = null;
		timer.setInitialDelay(0);
		timer.start();
	}
	
	class AnimationEventListener implements  ActionListener{
		public AnimationEventListener(){ }
		
		
		public void actionPerformed(ActionEvent e) {
			
				 try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(sounds.get(0)));
					clip.open(audioStream);
					FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue(-10.0f);
					clip.start();
					sounds.add(sounds.remove(0));
					
				} catch (LineUnavailableException e0) {
					// TODO Auto-generated catch block
					e0.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		}
	}
}
