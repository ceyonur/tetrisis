package game;

import javax.swing.JFrame;

import gui.*;
import settings.Settings;

public class Game {
	public static void main(String[] args){
		/*
		GUI frame = new GUI();
		*/
		
		JFrame frame = new JFrame();
		PlayGUI pg = new PlayGUI();
		pg.setEngine(getEngine(new Settings()));
		// the following code realizes the top level application window
		frame.setContentPane(pg);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static Engine getEngine(Settings settings){
		return new Engine(settings);
	}
}
