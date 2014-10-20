package gui;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.swing.Timer;

import settings.LevelChoice;

public class AudioPlayers {
	private Timer timerPlayGUIBackground;
	private Timer timerGameOver;
	private LevelChoice levelChoiceObject;
	private boolean effectSelector = true;
	private MusicLoopPlayerListener musicPlayerListener;
	private Clip clipGameOver = null;
	private GameOverPlayerListener gameOverPlayerListener;
	private ArrayList<String> sounds;
	private ArrayList<String> gameOverSounds;
	private Clip clipBackground;

	private boolean stateOfEffects = true;
	private boolean stateOfDotaEffects = true;

	public AudioPlayers() {
		gameOverPlayerListener = new GameOverPlayerListener();
		musicPlayerListener = new MusicLoopPlayerListener();
		timerPlayGUIBackground = new Timer(5000, musicPlayerListener);
		timerGameOver = new Timer(13503, gameOverPlayerListener);
	}
	
	public void setLevelChoice(LevelChoice levelChoice){
		levelChoiceObject = levelChoice;
	}

	public void playDotaEffects(boolean status, int counter,
			boolean isTheFirstKill) {
		if (stateOfDotaEffects){
			String stringFile = " ";
			int level = levelChoiceObject.getLevel();
			System.out.println(levelChoiceObject.getLevel());

			if (isTheFirstKill) {
				stringFile = "assets/sounds/firstBlood.wav";
			} else {
				if (counter == ONEKILL)
					stringFile = "assets/sounds/oneKill.wav";
				else if (counter == TWOKILL) {
					stringFile = "assets/sounds/DoubleKill.wav";
				} else if (counter == THREEKILL && effectSelector) {
					stringFile = "assets/sounds/TripleKill.wav";
				} else if (counter == THREEKILL && !effectSelector) {
					stringFile = "assets/sounds/MonsterKill.wav";
				} else if (counter == FOURKILL && effectSelector && level < 5) {
					stringFile = "assets/sounds/Rampage.wav";
				} else if (counter == FOURKILL && effectSelector) {
					stringFile = "assets/sounds/GodLike.wav";
				} else if (counter == FOURKILL && !effectSelector && level == 5) {
					stringFile = "assets/sounds/Unstopable.wav";
				}
			}
			AudioInputStream audioStream = null;
			Clip clip = null;

			try {
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
				effectSelector = !effectSelector;
				clip.open(audioStream);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			if (status) {
				clip.start();
			} else {
				if (clip != null)
					clip.stop();
			}
		}
	}

	public void playEffects(boolean status, int selector) {
		if (stateOfEffects){
			String stringFile = " ";
			switch (selector) {
			case ROTATE:
				stringFile = "assets/sounds/rotate.wav";
				break;
			case JUCKJUCK:
				stringFile = "assets/sounds/oneKill.wav";
				break;
			case SITSOUND:
				stringFile = "assets/sounds/lastMove.wav";
				break;
			}
			AudioInputStream audioStream = null;
			Clip clip = null;
			try {
				audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(audioStream);
				FloatControl volume = (FloatControl) clip
						.getControl(FloatControl.Type.MASTER_GAIN);
				switch (selector) {
				case ROTATE:
					volume.setValue(-15.0f);
					break;
				case JUCKJUCK:
					volume.setValue(-5.0f);
					break;
				case SITSOUND:
					volume.setValue(-10.0f);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			if (status) {
				clip.start();
			} else {
				if (clip != null)
					clip.stop();
			}
		}
	}

	public void playPlayGUIBackground(boolean status) {

		sounds = new ArrayList<String>();
		for (int i = 0; i < 29; i++) {
			sounds.add("assets/sounds/backGround/" + (i + 1) + ".wav");
		}
		timerPlayGUIBackground.setInitialDelay(0);
		timerPlayGUIBackground.start();
	}

	class MusicLoopPlayerListener implements ActionListener {
		public MusicLoopPlayerListener() {
		}

		public void actionPerformed(ActionEvent e) {
			AudioInputStream audioStream = null;

			try {
				String sound = sounds.remove(0);
				audioStream = AudioSystem.getAudioInputStream(new File(sound));
				AudioFormat format = audioStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clipBackground = (Clip) AudioSystem.getLine(info);
				clipBackground.open(audioStream);
				FloatControl volume = (FloatControl) clipBackground
						.getControl(FloatControl.Type.MASTER_GAIN);
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

	public void playGameOver(boolean status) {
		gameOverSounds = new ArrayList<String>();
		gameOverSounds.add("assets/sounds/gameOver/1.wav");
		gameOverSounds.add("assets/sounds/gameOver/2.wav");

		timerGameOver.setInitialDelay(0);
		timerGameOver.start();

	}

	class GameOverPlayerListener implements ActionListener {
		public GameOverPlayerListener() {
		}

		public void actionPerformed(ActionEvent e) {

			try {
				clipGameOver = AudioSystem.getClip();
				String sound = gameOverSounds.remove(0);
				AudioInputStream audioStream = AudioSystem
						.getAudioInputStream(new File(sound));
				clipGameOver.open(audioStream);
				FloatControl volume = (FloatControl) clipGameOver
						.getControl(FloatControl.Type.MASTER_GAIN);
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

	public void disableOrEnablePlayGUIBackgroundSound(boolean state) {
		if (!state){
			if (clipBackground != null){
				clipBackground.stop();
				
			}
			if (timerPlayGUIBackground.isRunning())
				timerPlayGUIBackground.stop();
		} else {
			playPlayGUIBackground(state);
		}
	}

	public void disableOrEnableGameOverSound(boolean state) {
		if (!state){
			if (clipGameOver != null)
				clipGameOver.stop();
			if (timerGameOver.isRunning())
				timerGameOver.stop();
		} else {
			playGameOver(state);
		}
	}

	public void disableOrEnableDotaEffects(boolean state) {
		stateOfDotaEffects = state;
	}

	public void disableOrEnableEffects(boolean state) {
		stateOfEffects = state;
	}

	public void disableOrEnableAllSounds(boolean state) {
		disableOrEnablePlayGUIBackgroundSound(state);
		disableOrEnableDotaEffects(state);
		disableOrEnableEffects(state);
	}

	public static final int ROTATE = 11;
	public static final int JUCKJUCK = 12;
	public static final int SITSOUND = 13;
	public static final int ONEKILL = 1;
	public static final int TWOKILL = 2;
	public static final int THREEKILL = 3;
	public static final int FOURKILL = 4;

}
