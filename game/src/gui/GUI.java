package gui;

import game.Engine;
import game.Game;
import highscores.HighScores;
import highscores.Player;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import settings.Settings;

public class GUI extends JFrame {

	Settings settings;
	HighScores highscores;
	PlayGUI playGUI;
	MenuGUI menuGUI;
	SettingsGUI settingsGUI;
	HighScoresGUI highscoresGUI;
	Dimension size;

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

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.show();
		gui.setLocationRelativeTo(null);

	}
	
	public void setGameEngine(Engine engine){
		playGUI.setEngine(engine);
	}

	public void showMenu() {
		menuGUI = new MenuGUI(this);
		setContentPane(menuGUI);
	}

	public void showPlay() {
		PlayGUI pgui = new PlayGUI(this);
		pgui.setEngine(Game.getEngine(settings));
		setEnabled(false);
		pgui.setLocationRelativeTo(null);
		pgui.show();
	}

	public void showSettings() {
		settingsGUI = new SettingsGUI(this, settings);
		setEnabled(false);
		settingsGUI.setLocationRelativeTo(null);
		settingsGUI.show();
	}

	public void showHighScores() {
		highscoresGUI = new HighScoresGUI(this, highscores);
		setEnabled(false);
		highscoresGUI.setLocationRelativeTo(null);
		highscoresGUI.show();
	}
	
	public void addPlayerToHighScoreList(Player player){
		highscores.add(player);
		highscores.saveHighScores();
	}
	
	public void menuMusicEnabler(){
		menuGUI.menuMusicEnabler();
	}
}
