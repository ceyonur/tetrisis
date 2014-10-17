package game;

import java.io.FileNotFoundException;

import gui.*;
import settings.Settings;

public class Game {
	public static void main(String[] args) throws FileNotFoundException {
		GUI frame = new GUI();
		Settings settings = new Settings();
		
		frame.setEngine(getEngine());

		// the following code realizes the top level application window
		frame.pack();
		frame.setVisible(true);
	}
	
	public static Engine getEngine() throws FileNotFoundException{
		return new Engine();
	}
}
