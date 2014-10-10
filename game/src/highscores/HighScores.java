package highscores;

import java.util.ArrayList;

/**
 * This class keeps the first 5 highscores
 * @author bedirhancaldir
 */
public class HighScores {
	private ArrayList<Player> playerList;
	private final int UPPERBOUND = 5;

	/**
	 * The constructor of the HighScores class. HighScores holds the players having the highest scores up to 5 players.
	 */
	public HighScores(){
		
		playerList = new ArrayList<Player>();
	}

	/**
	 * This method adds the given player to the list with respect to his/her score.
	 * @param player The player wanted to be added to the list
	 */
	public void add(Player player){

		for (int i=0; i<playerList.size(); i++){
			if (player.getScore() > playerList.get(i).getScore()){ // To hold the order of the ranking
				playerList.add(i, player); // Addition of the player since it satisfies the conditions
				if (playerList.size() > UPPERBOUND){ // To remove the players ranked more than 5th.
					playerList.remove(UPPERBOUND);
				}
				break; // To stop the further addition of the same player.
			}
		}

		if (playerList.isEmpty()) // If the list is empty, then add directly
			playerList.add(player);
		else if (playerList.size() < 5) // If the list has less than 5 players and all of them have higher scores than the new player, 
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

	/**
	 * 
	 * @return 
	 */
	public String toString(){
		return null;

	}
}



