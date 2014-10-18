package game;

import javax.swing.JFrame;

import gui.*;
import settings.Settings;

public class Game {
	public static void main(String[] args){
		
		GUI frame = new GUI();
		
		// the following code realizes the top level application window
		frame.setVisible(true);
	}
	
	public static Engine getEngine(Settings settings){
		return new Engine(settings);
	}
}
