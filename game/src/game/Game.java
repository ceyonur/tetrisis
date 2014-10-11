package game;

import gui.GUI;
import settings.Settings;

public class Game {
	public static void main(String[] args) {
		
		Settings settings = new Settings();
		Engine engine = new Engine(settings);
		GUI frame = new GUI(engine);

		// the following code realizes the top level application window
		frame.pack();
		frame.setVisible(true);
	}
}
