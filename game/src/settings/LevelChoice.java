package settings;

import java.io.Serializable;

/**
 * The Class Level.
 * @author ceyonur
 */
public class LevelChoice implements Serializable{
	
	/** The level field. */
	private Integer level;
	public final int numLevels = 5;
	public final String levelLabels[] = {
			"grandma",
			"rookie",
			"normal",
			"expert",
			"god mode"
	};
	
	/**
	 * Constructor for the LevelChoice class. Takes an Integer.
	 *
	 * @param level The level
	 */
	public LevelChoice(Integer level){
		setLevel(level);
	}
	
	/**
	 * Default constructor for the LevelChoice class. Sets the level to 1.
	 */
	public LevelChoice(){
		this(1);
	}
	
	/**
	 * Gets the level.
	 *
	 * @return The level
	 */
	public Integer getLevel(){
		return level;
	}
	
	/**
	 * Sets the level. Check whether the given level between 1 and 5, otherwise sets the level to 1.
	 *
	 * @param level The new level
	 */
	public void setLevel(Integer level){
		if(1<= level && level <= numLevels){
			this.level = level;			
		}
		else{
			this.level = 1;
		}
	}
	
	/**
	 * Gets the speed according to the level.
	 *
	 * @return The speed
	 */
	public Double getSpeed(){
		return 1.2 - level*0.2;
	}
}
