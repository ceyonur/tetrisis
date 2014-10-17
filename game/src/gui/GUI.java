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
	HighScores highscores;
	PlayGUI playGUI;
	MenuGUI menuGUI;
	SettingsGUI settingsGUI;
	HighScoresGUI highscoresGUI;
	Color bgcolor;
	Dimension size;

	public GUI()  {
		setSize(570, 690);
		float[] hsb = Color.RGBtoHSB(41, 128, 185, null);
		bgcolor = Color.getHSBColor(hsb[0],hsb[1],hsb[2]);
		settings = new Settings();
		highscores = new HighScores();
		size = new Dimension(570, 690);
		menuGUI = new MenuGUI(this);
		setSize(size);
		showMenu();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.show();
	}
	
	public void setGameEngine(Engine engine){
		playGUI.setEngine(engine);
	}

	public void showMenu() {
		menuGUI = new MenuGUI(this);
		setContentPane(menuGUI);
	}

	public void showPlay() {
		/*
		playGUI = new PlayGUI();
		setGameEngine(Game.getEngine(settings));
		setContentPane(playGUI);
		repaint();
		playGUI.repaint();
		pack();
		*/
		PlayGUI pgui = new PlayGUI(this);
		pgui.setEngine(Game.getEngine(settings));
		setEnabled(false);
		pgui.show();
	}

	public void showSettings() {
		settingsGUI = new SettingsGUI(this, settings);
		settingsGUI.show();
	}

	public void showHighScores() {
		highscores = new HighScores();
		highscoresGUI = new HighScoresGUI(this, highscores);
		highscoresGUI.show();
	}
}
