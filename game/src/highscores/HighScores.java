package highscores;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.DateFormatter;

// TODO: Auto-generated Javadoc
/**
 * This class keeps the first 5 highscores.
 *
 * @author bedirhancaldir
 */
public class HighScores implements Serializable {
	
	/** The player list. */
	private ArrayList<Player> playerList;
	
	/** The upperbound. */
	private final int UPPERBOUND = 5;

	/**
	 * The constructor of the HighScores class. HighScores holds the players having the highest scores up to 5 players.
	 */
	public HighScores(){
		playerList = new ArrayList<Player>();
		this.loadHighScores();		
	}

	/**
	 * This method adds the given player to the list with respect to his/her score.
	 * @param player The player wanted to be added to the list
	 */
	public void add(Player player){
		boolean added = false;
		for (int i=0; i<playerList.size(); i++){
			if (player.getScore() > playerList.get(i).getScore()){ // To hold the order of the ranking
				playerList.add(i, player); // Addition of the player since it satisfies the conditions
				if (playerList.size() > UPPERBOUND){ // To remove the players ranked more than 5th.
					playerList.remove(UPPERBOUND);
				}
				added = true;
				break; // To stop the further addition of the same player.
			}
		}

		if (playerList.isEmpty()) // If the list is empty, then add directly
			playerList.add(player);
		else if (playerList.size() < 5 && !added) // If the list has less than 5 players and all of them have higher scores than the new player, 
			// then the new player will be added to the end of the list since the for loop doesn't add.
			playerList.add(playerList.size(), player);
	}

	/**
	 * This method returns the player found in the list at the given index.
	 * @param index must be in [1,5] (i.e. getPlayer(3) for the player having 3rd highest score)
	 * @return The player corresponding to the index given (null if the index isn't in the interval of [1,5])
	 */
	public Player getPlayer(int index){
		
		if (index <= UPPERBOUND && index > 0) // Check whether the given index is in the permitted range.
			return playerList.get(index-1);	

		return null; // If not, return null.
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		String result = "";
		int i = 1;
		for(Player player : playerList){
			result += i + ":" + player.getName() + '\t' + player.getScore() + '\t' + player.getDate().toString();
			result += '\n';
			i++;
		}
		
		return result;

	}
	
	/**
	 * Saves high scores.
	 */
	public void saveHighScores(){
	try{
			
			
			FileOutputStream fout = new FileOutputStream("HighScores.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(this);
			oos.close();			
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   
	}
	}
	
	/**
	 * Loads high scores to the same HighScore object.
	 */
	public void loadHighScores() {		
		try{
			 
			   FileInputStream fin = new FileInputStream("HighScores.ser");
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   HighScores highScores= (HighScores) ois.readObject();
			   ois.close();
			   for(Player player : highScores.playerList){
				   this.add(player);
			   }
			   
	 
		   }catch(Exception ex){
			   try {
				FileOutputStream fout = new FileOutputStream("HighScores.ser");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   
		   } 
		
	}
	
	
	
	
	/**
	 * Gets the player list size.
	 *
	 * @return the player list size
	 */
	public int getPlayerListSize(){
		return playerList.size();
	}
	
	/**
	 * Checks if is score high enough.
	 *
	 * @param score the score
	 * @return true, if is score high enough
	 */
	public boolean isScoreHighEnough(double score){
		if (playerList.size() >= 5){
		for (int i=0; i<playerList.size(); i++){
			if ((int) score >= (int) playerList.get(i).getScore()){
				return true;
			}
		}
		return false;
		} else
			return true;
	}
}
