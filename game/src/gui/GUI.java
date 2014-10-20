package gui;

import game.*;
import highscores.HighScores;

import java.awt.*;

import javax.swing.*;

import settings.Settings;

/**
 * The main GUI object of the game, controls other GUI classes in the game
 * @author atilberk
 *
 */
public class GUI extends JFrame {

	/**
	 * Fields
	 */
	Settings settings;
	HighScores highscores;
	PlayGUI playGUI;
	MenuGUI menuGUI;
	SettingsGUI settingsGUI;
	HighScoresGUI highscoresGUI;
	Dimension size;

	/**
	 * Constructor, initializes the Frame and shows the menu as default
	 */
	public GUI()  {
		setSize(570, 690);
		settings = new Settings();
		highscores = new HighScores();
		size = new Dimension(570, 690);

		setSize(size);
		showMenu();
		
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	/**
	 * Sets the engine to the GUI
	 * @param engine
	 */
	public void setGameEngine(Engine engine){
		playGUI.setEngine(engine);
	}

	/**
	 * Shows the menu
	 */
	protected void showMenu() {
		menuGUI = new MenuGUI(this);
		setContentPane(menuGUI);
	}

	/**
	 * Shows the game and disables the menu
	 */
	protected void showPlay() {
		PlayGUI pgui = new PlayGUI(this);
		pgui.setEngine(Game.getEngine(settings, highscores));
		setEnabled(false);
		pgui.setLocationRelativeTo(null);
		pgui.setVisible(true);
	}

	/**
	 * Opens the settings window and disables the menu
	 */
	protected void showSettings() {
		settingsGUI = new SettingsGUI(this, settings);
		setEnabled(false);
		settingsGUI.setLocationRelativeTo(null);
		settingsGUI.setVisible(true);
	}

	/**
	 * Opens the highscores window and disables the menu
	 */
	protected void showHighScores() {
		highscoresGUI = new HighScoresGUI(this, highscores);
		setEnabled(false);
		highscoresGUI.setLocationRelativeTo(null);
		highscoresGUI.setVisible(true);
	}
	
	protected void menuMusicEnabler() {
		menuGUI.menuMusicEnabler();
	}
}
