package gui;

import settings.Settings;
import sun.audio.*;

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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.*;

public class MenuGUI extends JPanel {

	private GUI gui;

	private JPanel settingsGui;
	private AudioPlayer AP = AudioPlayer.player;
	private AudioStream AS;
	private AudioData AD;
	private ContinuousAudioDataStream loop = null;

	private Color bgcolor = SColor.backgroundColor;;

	private boolean mute = true;

	public MenuGUI(GUI ui) {
		super();
		gui = ui;

		setSize(gui.size);
		this.setBackground(bgcolor);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel headerPanelContainer = new JPanel();
		headerPanelContainer.setBackground(bgcolor);

		JPanel buttonPanelContainer = new JPanel();
		buttonPanelContainer.setBackground(bgcolor);
		JPanel footerPanelContainer = new JPanel();
		footerPanelContainer.setBackground(bgcolor);

		headerPanelContainer.setLayout(new BoxLayout(headerPanelContainer,
				BoxLayout.Y_AXIS));
		JPanel headerPanel = createHeader();
		headerPanelContainer.add(Box.createVerticalStrut(80));
		headerPanelContainer.add(headerPanel);
		headerPanelContainer.add(Box.createVerticalStrut(50));

		JPanel buttonPanel = createMenuButtons();
		buttonPanelContainer.add(buttonPanel);

		JPanel footerPanel = createFooter();
		footerPanelContainer.add(footerPanel);

		this.add(headerPanelContainer);
		this.add(buttonPanelContainer);
		this.add(footerPanelContainer);

		try {
			AS = new AudioStream(new FileInputStream(
					"assets/sounds/mainMenu.wav"));
			AD = AS.getData();
			loop = new ContinuousAudioDataStream(AD);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
		playAudio(mute);
	}

	public JPanel createHeader() {
		JPanel header = new JPanel();

		SLabel openit = new SLabel("OpenIT proudly presents",
				SLabel.MAIN_MENU_AUTHOR);
		SLabel title = new SLabel("tetris/triris game", SLabel.MAIN_MENU_TITLE);
		SLabel subtitle = new SLabel("a COMP 302 Project",
				SLabel.MAIN_MENU_AUTHOR);
		header.add(openit);
		header.add(title);
		header.add(subtitle);

		header.setBackground(bgcolor);
		return header;
	}

	private JPanel createMenuButtons() {
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1, 0, -5));
		buttons.setBackground(SColor.backgroundColor);

		final SButton newGame = new SButton("new game",
				SButton.MAIN_MENU_BUTTON);
		SButton settings = new SButton("settings", SButton.MAIN_MENU_BUTTON);
		SButton highScores = new SButton("high scores",
				SButton.MAIN_MENU_BUTTON);
		SButton exit = new SButton("exit", SButton.MAIN_MENU_BUTTON);

		buttons.add(newGame);
		buttons.add(settings);
		buttons.add(highScores);
		buttons.add(exit);

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAudio(false);
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

	private JPanel createFooter() {
		JPanel footer = new JPanel();
		footer.setBackground(bgcolor);
		int type = mute ? SButton.SOUND_BUTTON_MUTE
				: SButton.SOUND_BUTTON_UNMUTE;
		final SButton musicButton = new SButton(SButton.getIcon(type), type);

		musicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mute = !mute;
				playAudio(mute);
				musicButton.changeState();
			}
		});

		footer.add(musicButton);
		return footer;
	}

	public void playAudio(boolean status) {
		if(status)
			AP.start(loop);
		else
			AP.stop(loop);
	}

	class PlayListeners implements ActionListener {
		private MenuGUI callerMenuGUI;

		public PlayListeners(MenuGUI callerMenuGUI) {
			this.callerMenuGUI = callerMenuGUI;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.showPlay();
		}

	}
}
