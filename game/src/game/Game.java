package game;

import gui.*;
import settings.Settings;

public class Game {
	public static void main(String[] args) {
		GUI frame = new GUI();
		Settings settings = new Settings();
		
		frame.setEngine(getEngine());

		// the following code realizes the top level application window
		frame.pack();
		frame.setVisible(true);
	}
	
	public static Engine getEngine(){
		return new Engine();
	}
}
