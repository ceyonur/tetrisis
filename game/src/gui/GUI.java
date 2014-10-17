package gui;

import game.Engine;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import settings.Settings;

public class GUI extends JFrame {
	
	final Settings settings = new Settings();
	MenuGUI menuGUI;
	SettingsGUI settingsGUI = new SettingsGUI(this, settings);
	HighScoresGUI highscoresGUI;
	Color bgcolor;

	public GUI() {
		float[] hsb = Color.RGBtoHSB(41, 128, 185, null);
		bgcolor = Color.getHSBColor(hsb[0],hsb[1],hsb[2]);
		
		menuGUI = new MenuGUI(this);
		setSize(menuGUI.size());
		setContentPane(menuGUI);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.show();
	}
}
