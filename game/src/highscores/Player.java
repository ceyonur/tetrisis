package highscores;

import java.io.Serializable;
import java.util.Date;

/**
 * This class generates a player with his/her name, score and game playing date
 * @author bedirhancaldir
 */
public class Player implements Serializable{
	private String name;
	private double score;
	private Date date;

	/**
	 * The constructor of the Player class.
	 * @param name The name of the player (must have at most 20 characters)
	 * @param score The score of the player (must be greater than or equal to 0)
	 * @param date The date of the game played
	 */
	public Player(String name, int score, Date date){
		setName(name);
		setScore(score);
		setDate(date);
	}

	/**
	 * The constructor of the Player class. Date will be the running date.
	 * @param name The name of the player (must have at most 20 characters)
	 * @param score The score of the player (must be greater than or equal to 0)
	 */
	public Player(String name, int score){
		this(name, score, new Date()); // new Date() -> gives the instance of calling
	}

	/**
	 * The constructor of the Player class. The score will be 0.  Date will be the running date. 
	 * @param name The name of the player (must have at most 20 characters)
	 */
	public Player(String name){
		this(name, 0);
	}

	/**
	 * This method sets the name of the player to the given name. Name must be no longer than 20 characters.
	 * Otherwise it would be cut to its first 20 letters.
	 * @param name The name of the player
	 */
	public void setName(String name){
		if (name.length() <= 10) 
			this.name = name;
		else
			this.name = name.substring(0,10); // If the length of the name exceeds 20, then the first 20 letters will be taken.
	}

	/**
	 * This method sets the date of the game played by the player to the given date.
	 * @param date The date of the game played
	 */
	public void setDate(Date date){
		this.date = date;
	}

	/**
	 * This method sets the score of the player to the given value. The score must be greater than or equal to 0.
	 * Otherwise the score would be set to 0 automatically.
	 * @param score The score of the player
	 */
	public void setScore(int score){
		if (score >= 0)
			this.score = score;
		else // If score is below 0, then it will be equalized to 0.
			this.score = 0;
	}

	/**
	 * This method returns the name of the player
	 * @return The name of the player
	 */
	public String getName(){
		return name;
	}

	/**
	 * This method returns the date at which this player played the tetris/triris
	 * @return The date of the particular game played by this player
	 */
	public Date getDate(){
		return date;
	}

	/**
	 * This method returns the score of the player
	 * @return The score of the player
	 */
	public double getScore(){
		return score;
	}
}
