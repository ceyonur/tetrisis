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
		settings = new Settings();
		settingsGUI = new SettingsGUI(this, settings);
		float[] hsb = Color.RGBtoHSB(41, 128, 185, null);
		bgcolor = Color.getHSBColor(hsb[0],hsb[1],hsb[2]);
		
		menuGUI = new MenuGUI(this);
		setSize(menuGUI.size());
		showPlay();
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
		setContentPane(menuGUI);
	}
	
	public void showPlay() {
		playGUI = new PlayGUI(this);
		setGameEngine(Game.getEngine());
		setContentPane(playGUI);
		repaint();
		playGUI.repaint();
	}
	
	public void showSettings() {
		settings = new Settings();
		settingsGUI = new SettingsGUI(this, settings);
		setContentPane(settingsGUI);
	}
	
	public void showHighScores() {
		setContentPane(highscoresGUI);
	}
}
