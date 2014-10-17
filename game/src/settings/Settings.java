package settings;

import highscores.HighScores;
import highscores.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Settings. This class holds other elements of settings package,
 * and provides a direct access to getters and setters.
 * @author ceyonur
 */
public class Settings implements Serializable{
	
	/** The size. */
	private BoardSize boardSizeChoice;
	
	/** The key. */
	private KeyConfigure keyConfig;
	
	/** The level. */
	private LevelChoice levelChoice;
	
	/** The piece. */
	private PieceChoice pieceChoice;

	/**
	 * Default constructor for the Settings class.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	
	public Settings() {
		boardSizeChoice = new BoardSize();
		keyConfig = new KeyConfigure();
		levelChoice = new LevelChoice();
		pieceChoice = new PieceChoice();
		this.loadSettings();
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public BoardSize getBoardSizeChoice() {
		return boardSizeChoice;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setBoardSizeChoice(BoardSize size) {
		this.boardSizeChoice = size;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public KeyConfigure getKeyConfigure() {
		return keyConfig;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKeyConfigure(KeyConfigure key) {
		this.keyConfig = key;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public LevelChoice getLevelChoice() {
		return levelChoice;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevelChoice(LevelChoice level) {
		this.levelChoice = level;
	}

	/**
	 * Gets the piece.
	 *
	 * @return the piece
	 */
	public PieceChoice getPieceChoice() {
		return pieceChoice;
	}

	/**
	 * Sets the piece.
	 *
	 * @param piece the new piece
	 */
	public void setPieceChoice(PieceChoice piece) {
		this.pieceChoice = piece;
	}
	
	/**
	 * Checks if is small.
	 *
	 * @return true, if is small
	 */
	public boolean isSmall() {
		return boardSizeChoice.isSmall();
	}

	/**
	 * Sets the size to small.
	 */
	public void setSmall() {
		boardSizeChoice.setSmall();
	}

	/**
	 * Checks if the size is medium.
	 *
	 * @return true, if the size is medium
	 */
	public boolean isMedium() {
		return boardSizeChoice.isMedium();
	}

	/**
	 * Sets the size to medium.
	 */
	public void setMedium() {
		boardSizeChoice.setMedium();
	}

	/**
	 * Checks if the size is large.
	 *
	 * @return true, if the size is large
	 */
	public boolean isLarge() {
		return boardSizeChoice.isLarge();
	}

	/**
	 * Sets the size to large.
	 */
	public void setLarge() {
		boardSizeChoice.setLarge();
	}

	/**
	 * Gets the row according to the selected size.
	 *
	 * @return The row size
	 */
	public int getRow() {
		return boardSizeChoice.getRow();
	}

	/**
	 * Gets the column according to the selected size.
	 *
	 * @return The column size
	 */
	public int getColumn() {
		return boardSizeChoice.getColumn();
	}
	
	/**
	 * Gets the size choice.
	 *
	 * @return the size choice
	 */
	public String getSizeChoice(){
		return boardSizeChoice.getChoice();
		
	}
	
	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public int getLeft() {
		return keyConfig.getLeft();
	}

	/**
	 * Sets the left.
	 *
	 * @param left The new key for function Left. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location.   
	 */
	public void setLeft(int left) {	
		keyConfig.setLeft(left);

	}

	/**
	 * Gets the right.
	 *
	 * @return The key of function Right.
	 */
	public int getRight() {
		return keyConfig.getRight();
	}

	/**
	 * Sets the right.
	 *
	 * @param right The new key for function Right. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRight(int right) {	
		keyConfig.setRight(right);

	}

	/**
	 * Gets the down.
	 *
	 * @return The key of function Down.
	 */
	public int getDown() {
		return keyConfig.getDown();
	}

	/**
	 * Sets the down.
	 *
	 * @param down The new key for function Down. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setDown(int down) {		
		keyConfig.setDown(down);

	}

	/**
	 * Gets the rotate.
	 *
	 * @return The key of function Rotate.
	 */
	public int getRotate() {
		return keyConfig.getRotate();
	}

	/**
	 * Sets the rotate.
	 *
	 * @param rotate The new key for function Rotate. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRotate(int rotate) {	
		keyConfig.setRotate(rotate);

	}

	/**
	 * Gets the pause.
	 *
	 * @return The key of function Pause. 
	 */
	public int getPause() {
		return keyConfig.getPause();
	}

	/**
	 * Sets the pause.
	 *
	 * @param pause The new key for function Pause. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setPause(int pause){ 	
		keyConfig.setPause(pause);

	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public HashMap<String, Integer> getMap(){
		return keyConfig.getMap();
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setMap(HashMap<String, Integer> map){
		keyConfig.setMap(map);
	}
	

	/**
	 * Gets the level.
	 *
	 * @return The level
	 */
	public Integer getLevel(){
		return levelChoice.getLevel();
	}
	
	/**
	 * Sets the level. Check whether the given level between 1 and 5, otherwise sets the level to 1.
	 *
	 * @param level The new level
	 */
	public void setLevel(Integer level){
		this.levelChoice.setLevel(level);
	}
	
	/**
	 * Gets the speed according to the level.
	 *
	 * @return The speed
	 */
	public Double getSpeed(){
		return levelChoice.getSpeed();
	}
	
	/**
	 * Sets the Tetriminos.
	 *
	 * @param choice the new tetriminos boolean
	 */
	public void setTetriminos(boolean choice) {
		pieceChoice.setTetriminos(choice);
	}

	/**
	 * Sets the  triminos.
	 *
	 * @param choice the new triminos boolean
	 */
	public void setTriminos(boolean choice) {
		pieceChoice.setTriminos(choice);
	}

	
	/**
	 * Checks for triminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTriminos(){
		return pieceChoice.hasTriminos();
	}
	
	/**
	 * Checks for tetriminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTetriminos(){
		return pieceChoice.hasTetriminos();
	}
	
	/**
	 * Checks for both tetriminos and triminos.	
	 *
	 * @return true, if successful
	 */
	public boolean hasBoth(){
		return pieceChoice.hasBoth();
	}
	
	/**
	 * Sets the both.
	 *
	 * @param bool the new both
	 */
	public void setBoth(boolean bool){
		pieceChoice.setBoth();
	}
	
	/**
	 * Gets the choice.
	 *
	 * @return the choice
	 */
	public String getChoice(){
		return pieceChoice.getChoice();
		
	}
	
	/**
	 * Save settings.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveSettings() {
		try{
			
			
			FileOutputStream fout = new FileOutputStream("Settings.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(this);
			oos.close();			
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   
	}
	}
	
	/**
	 * Load settings.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	public void loadSettings() {
		try{
			 
			   FileInputStream fin = new FileInputStream("Settings.ser");
			   ObjectInputStream ois = new ObjectInputStream(fin);
			   Settings settings = (Settings) ois.readObject();
			   ois.close();
			   this.setBoardSizeChoice(settings.getBoardSizeChoice());
			   this.setKeyConfigure(settings.getKeyConfigure());
			   this.setLevelChoice(settings.getLevelChoice());
			   this.setPieceChoice(settings.getPieceChoice());			   
		   }catch(Exception ex){
			   try {
				FileOutputStream fout = new FileOutputStream("Settings.ser");
				fout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		   
		   }		
		
	}	
}
