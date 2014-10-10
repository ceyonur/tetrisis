package game;

/**
 * The Class Level.
 * @author ceyonur
 */
public class Level {
	
	/** The level field. */
	Integer level;
	
	/**
	 * Constructor for the Level class. Takes an Integer.
	 *
	 * @param level The level
	 */
	public Level(Integer level){
		setLevel(level);
	}
	
	/**
	 * Default constructor for the Level class. Sets the level to 1.
	 */
	public Level(){
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
		if(1<= level && level <= 5){
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
