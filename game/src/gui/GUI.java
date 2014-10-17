package gui;

import game.Engine;
import game.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import settings.Settings;

public class GUI extends JFrame {
	
	Settings settings;
	PlayGUI play;
	MenuGUI menuGUI;
	SettingsGUI settingsGUI;
	HighScoresGUI highscoresGUI;
	Color bgcolor;

	public GUI()  {
		setSize(570, 690);
		play = new PlayGUI(this);
		settings = new Settings();
		settingsGUI = new SettingsGUI(this, settings);
		float[] hsb = Color.RGBtoHSB(41, 128, 185, null);
		bgcolor = Color.getHSBColor(hsb[0],hsb[1],hsb[2]);
		
		menuGUI = new MenuGUI(this);
		setSize(menuGUI.size());
		
		setEngine(Game.getEngine());
		setContentPane(play);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.show();
	}
	
	public void setEngine(Engine engine){
		play.setEngine(engine);
	}
}
