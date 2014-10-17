package gui;

import game.Engine;
import game.Game;
import highscores.HighScores;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import settings.Settings;

public class GUI extends JFrame {

	Settings settings;
	PlayGUI playGUI;
	MenuGUI menuGUI;
	SettingsGUI settingsGUI;
	HighScoresGUI highscoresGUI;
	Color bgcolor;

	public GUI()  {
		setSize(570, 690);
		float[] hsb = Color.RGBtoHSB(41, 128, 185, null);
		bgcolor = Color.getHSBColor(hsb[0],hsb[1],hsb[2]);
		settings = new Settings();
		menuGUI = new MenuGUI(this);
		setSize(menuGUI.size());
		showMenu();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.show();
	}

	public void setEngine(Engine engine){
		playGUI.setEngine(engine);
	}

	public void showMenu() {
		setContentPane(menuGUI);
	}

	public void showPlay() {
		playGUI = new PlayGUI(this);
		setEngine(Game.getEngine(settings));
		setContentPane(playGUI);
		repaint();
		playGUI.repaint();
	}

	public void showSettings() {
		settingsGUI = new SettingsGUI(this, settings);
		setContentPane(settingsGUI);
	}

	public void showHighScores() {
		setContentPane(highscoresGUI);
	}
}
