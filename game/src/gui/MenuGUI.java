package gui;

import settings.Settings;
import highscores.HighScores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * Creates GUI for Menu
 * @author ogunoz atilberk
 *
 */
public class MenuGUI extends JPanel {

	/**
	 * Fields
	 */
	private GUI gui;
	private Clip clip;
	private boolean mute = true;

	/**
	 * Constructor
	 * @param GUI
	 */
	public MenuGUI(GUI gui) {
		super();
		this.gui = gui;
		
		setSize(gui.size);
		setBackground(SColor.backgroundColor);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		createAndAddContainers();

		this.setVisible(true);
		playAudio(mute);
		startAudio(clip);
	}
	
	/**
	 * Creates containers for header, buttons and footer sections
	 */
	private void createAndAddContainers() {
		JPanel headerPanelContainer = new JPanel();
		headerPanelContainer.setBackground(SColor.backgroundColor);
		JPanel buttonPanelContainer = new JPanel();
		buttonPanelContainer.setBackground(SColor.backgroundColor);
		JPanel footerPanelContainer = new JPanel();
		footerPanelContainer.setBackground(SColor.backgroundColor);

		JPanel headerPanel = createHeader();
		headerPanelContainer.setLayout(new BoxLayout(headerPanelContainer,
				BoxLayout.Y_AXIS));		
		headerPanelContainer.add(Box.createVerticalStrut(80));
		headerPanelContainer.add(headerPanel);
		headerPanelContainer.add(Box.createVerticalStrut(50));

		JPanel buttonPanel = createMenuButtons();
		buttonPanelContainer.add(buttonPanel);

		JPanel footerPanel = createFooter();
		footerPanelContainer.add(footerPanel);

		add(headerPanelContainer);
		add(buttonPanelContainer);
		add(footerPanelContainer);
	}

	/**
	 * Creates container for header
	 * @return header panel
	 */
	private JPanel createHeader() {
		JPanel header = new JPanel();

		SLabel openit = new SLabel("openIT proudly presents",
				SLabel.MAIN_MENU_AUTHOR);
		SLabel title = new SLabel("tetris/triris game", SLabel.MAIN_MENU_TITLE);
		SLabel subtitle = new SLabel("a COMP 302 Project",
				SLabel.MAIN_MENU_AUTHOR);
		header.add(openit);
		header.add(title);
		header.add(subtitle);

		header.setBackground(SColor.backgroundColor);
		return header;
	}

	/**
	 * Creates container for menu buttons
	 * @return buttons panel
	 */
	private JPanel createMenuButtons() {
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1, 0, -5));
		buttons.setBackground(SColor.backgroundColor);

		final SButton newGame = new SButton("new game", SButton.MAIN_MENU_BUTTON);
		final SButton settings = new SButton("settings", SButton.MAIN_MENU_BUTTON);
		final SButton highScores = new SButton("high scores",	SButton.MAIN_MENU_BUTTON);
		final SButton exit = new SButton("exit", SButton.MAIN_MENU_BUTTON);

		buttons.add(newGame);
		buttons.add(settings);
		buttons.add(highScores);
		buttons.add(exit);

		
		// add listeners to buttons
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopAudio(clip);
				gui.showPlay();
			}
		});

		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.showSettings();
			}
		});

		highScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.showHighScores();
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		return buttons;
	}

	/**
	 * Creates container for footer
	 * @return footer panel
	 */
	private JPanel createFooter() {
		JPanel footer = new JPanel();
		footer.setBackground(SColor.backgroundColor);
		
		int buttonType = mute ? SButton.SOUND_BUTTON_MUTE
				: SButton.SOUND_BUTTON_UNMUTE;
		final SButton musicButton = new SButton(buttonType);

		footer.add(musicButton);
		
		//add listeners to button
		musicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mute = !mute;
				menuMusicEnabler();
				musicButton.changeState();
			}
		});

		return footer;
	}

	/**
	 * Enables or disables menu music
	 */
	protected void menuMusicEnabler() {
		if (!mute)
			stopAudio(clip);
		else
			startAudio(clip);
	}

	/**
	 * Stops the clip
	 * @param clip
	 */
	public void stopAudio(Clip clip) {
		if (clip != null) {
			clip.stop();
		}
	}

	/**
	 * Starts the clip
	 * @param clip
	 */
	public void startAudio(Clip clip) {
		clip.start();
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * Plays the main menu music
	 * @param status
	 */
	public void playAudio(boolean status) {
		String stringFile = "assets/sounds/mainMenu.wav";
		AudioInputStream audioStream = null;
		clip = null;

		try {
			audioStream = AudioSystem.getAudioInputStream(new File(stringFile));
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioStream);
			FloatControl volume = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(-5.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
