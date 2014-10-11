package game;

import gui.GUI;
import settings.Settings;

public class Game {
	public static void main(String[] args) {
		GUI frame = new GUI();
		Settings settings = new Settings();
		Engine engine = new Engine(settings);
		frame.setEngine(engine);

		// the following code realizes the top level application window
		frame.pack();
		frame.setVisible(true);
	}
}
