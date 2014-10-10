package settings;

import game.Level;

/**
 * The Class LevelChoice. This class holds the level choice of user.
 * @author ceyonur 
 */
public class LevelChoice {
	
	/** The level. */
	private Level level;

	/**
	 * Default constructor for the level class. Sets to Level 1
	 */
	public LevelChoice() {
		setLevelChoice(new Level(1));
	}

	/**
	 * Gets the level.
	 *
	 * @return The level
	 */
	public Level getLevelChoice() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level The new level
	 */
	public void setLevelChoice(Level level) {
		this.level = level;
	}

}
