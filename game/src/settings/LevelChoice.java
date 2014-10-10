package settings;

import game.Level;

// TODO: Auto-generated Javadoc
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
	 * @return the level
	 */
	public Level getLevelChoice() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevelChoice(Level level) {
		this.level = level;
	}

}
