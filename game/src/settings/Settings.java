package settings;


import java.io.*;

import java.util.HashMap;

/**
 * The Class Settings. This class holds other elements of settings package,
 * and provides a direct access to getters and setters.
 * @author ceyonur
 */
public class Settings implements Serializable{

	/** The size. */
	private BoardSize boardSizeChoiceObject;

	/** The key. */
	private KeyConfigure keyConfigObject;

	/** The level. */
	private LevelChoice levelChoiceObject;

	/** The piece. */
	private PieceChoice pieceChoiceObject;

	/**
	 * Default constructor for the Settings class.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */

	public Settings() {
		boardSizeChoiceObject = new BoardSize();
		keyConfigObject = new KeyConfigure();
		levelChoiceObject = new LevelChoice();
		pieceChoiceObject = new PieceChoice();
		loadSettings();
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public BoardSize getBoardSizeChoiceObject() {
		return boardSizeChoiceObject;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setBoardSizeChoiceObject(BoardSize size) {
		this.boardSizeChoiceObject = size;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public KeyConfigure getKeyConfigureObject() {
		return keyConfigObject;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKeyConfigureObject(KeyConfigure key) {
		this.keyConfigObject = key;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public LevelChoice getLevelChoiceObject() {
		return levelChoiceObject;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevelChoiceObject(LevelChoice level) {
		this.levelChoiceObject = level;
	}

	/**
	 * Gets the piece.
	 *
	 * @return the piece
	 */
	public PieceChoice getPieceChoiceObject() {
		return pieceChoiceObject;
	}

	/**
	 * Sets the piece.
	 *
	 * @param piece the new piece
	 */
	public void setPieceChoiceObject(PieceChoice piece) {
		this.pieceChoiceObject = piece;
	}

	/**
	 * Checks if is small.
	 *
	 * @return true, if is small
	 */
	public boolean isBoardSmall() {
		return boardSizeChoiceObject.isSmall();
	}

	/**
	 * Sets the size to small.
	 */
	public void setBoardSmall() {
		boardSizeChoiceObject.setSmall();
	}

	/**
	 * Checks if the size is medium.
	 *
	 * @return true, if the size is medium
	 */
	public boolean isBoardMedium() {
		return boardSizeChoiceObject.isMedium();
	}

	/**
	 * Sets the size to medium.
	 */
	public void setBoardMedium() {
		boardSizeChoiceObject.setMedium();
	}

	/**
	 * Checks if the size is large.
	 *
	 * @return true, if the size is large
	 */
	public boolean isBoardLarge() {
		return boardSizeChoiceObject.isLarge();
	}

	/**
	 * Sets the size to large.
	 */
	public void setBoardLarge() {
		boardSizeChoiceObject.setLarge();
	}

	/**
	 * Gets the row according to the selected size.
	 *
	 * @return The row size
	 */
	public int getRow() {
		return boardSizeChoiceObject.getRow();
	}

	/**
	 * Gets the column according to the selected size.
	 *
	 * @return The column size
	 */
	public int getColumn() {
		return boardSizeChoiceObject.getColumn();
	}

	/**
	 * Gets the size choice.
	 *
	 * @return the size choice
	 */
	public String getSizeChoice(){
		return boardSizeChoiceObject.getChoice();

	}

	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public int getLeftKey() {
		return keyConfigObject.getLeft();
	}

	/**
	 * Sets the left.
	 *
	 * @param left The new key for function Left. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location.   
	 */
	public void setLeftKey(int left) {	
		keyConfigObject.setLeft(left);

	}

	/**
	 * Gets the right.
	 *
	 * @return The key of function Right.
	 */
	public int getRightKey() {
		return keyConfigObject.getRight();
	}

	/**
	 * Sets the right.
	 *
	 * @param right The new key for function Right. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRightKey(int right) {	
		keyConfigObject.setRight(right);

	}

	/**
	 * Gets the down.
	 *
	 * @return The key of function Down.
	 */
	public int getDownKey() {
		return keyConfigObject.getDown();
	}

	/**
	 * Sets the down.
	 *
	 * @param down The new key for function Down. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setDownKey(int down) {		
		keyConfigObject.setDown(down);

	}

	/**
	 * Gets the rotate.
	 *
	 * @return The key of function Rotate.
	 */
	public int getRotateKey() {
		return keyConfigObject.getRotate();
	}

	/**
	 * Sets the rotate.
	 *
	 * @param rotate The new key for function Rotate. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRotateKey(int rotate) {	
		keyConfigObject.setRotate(rotate);

	}

	/**
	 * Gets the pause.
	 *
	 * @return The key of function Pause. 
	 */
	public int getPauseKey() {
		return keyConfigObject.getPause();
	}

	/**
	 * Sets the pause.
	 *
	 * @param pause The new key for function Pause. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setPauseKey(int pause){ 	
		keyConfigObject.setPause(pause);

	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public HashMap<String, Integer> getKeyConfigMap(){
		return keyConfigObject.getMap();
	}

	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setKeyConfigMap(HashMap<String, Integer> map){
		keyConfigObject.setMap(map);
	}


	/**
	 * Gets the level.
	 *
	 * @return The level
	 */
	public Integer getLevel(){
		return levelChoiceObject.getLevel();
	}

	/**
	 * Sets the level. Check whether the given level between 1 and 5, otherwise sets the level to 1.
	 *
	 * @param level The new level
	 */
	public void setLevel(Integer level){
		this.levelChoiceObject.setLevel(level);
	}

	/**
	 * Gets the speed according to the level.
	 *
	 * @return The speed
	 */
	public Double getSpeed(){
		return levelChoiceObject.getSpeed();
	}

	/**
	 * Sets the Tetriminos.
	 *
	 * @param choice the new tetriminos boolean
	 */
	public void setTetriminos(boolean choice) {
		pieceChoiceObject.setTetriminos(choice);
	}

	/**
	 * Sets the  triminos.
	 *
	 * @param choice the new triminos boolean
	 */
	public void setTriminos(boolean choice) {
		pieceChoiceObject.setTriminos(choice);
	}


	/**
	 * Checks for triminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTriminos(){
		return pieceChoiceObject.hasTriminos();
	}

	/**
	 * Checks for tetriminos.
	 *
	 * @return true, if successful
	 */
	public boolean hasTetriminos(){
		return pieceChoiceObject.hasTetriminos();
	}

	/**
	 * Checks for both tetriminos and triminos.	
	 *
	 * @return true, if successful
	 */
	public boolean hasBothPieces(){
		return pieceChoiceObject.hasBoth();
	}

	/**
	 * Sets the both.
	 *
	 * @param bool the new both
	 */
	public void setBothPieces(boolean bool){
		pieceChoiceObject.setBoth();
	}

	/**
	 * Gets the choice.
	 *
	 * @return the choice
	 */
	public String getPieceChoice(){
		return pieceChoiceObject.getChoice();

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
			this.setBoardSizeChoiceObject(settings.getBoardSizeChoiceObject());
			this.setKeyConfigureObject(settings.getKeyConfigureObject());
			this.setLevelChoiceObject(settings.getLevelChoiceObject());
			this.setPieceChoiceObject(settings.getPieceChoiceObject());	
		}catch(Exception ex){
			try {
				FileOutputStream fout = new FileOutputStream("Settings.ser");
				fout.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		   
		}
	}	
}
