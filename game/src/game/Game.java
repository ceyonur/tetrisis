package game;

import gui.*;
import settings.Settings;

public class Game{
	public static void main(String[] args){
		
		GUI frame = new GUI();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	}
	
	/**
	 * This static method generates an Engine object and returns it
	 * @param settings The settings to generate the Engine object
	 * @return The generated Engine object
	 */
	public static Engine getEngine(Settings settings){
		return new Engine(settings);
	}
}
