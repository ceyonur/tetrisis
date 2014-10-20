package settings;

import gui.SettingsGUI;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;


/**
 * The Class KeyConfigure. This class maps the game functions to keys.
 * @author ceyonur
 */
public class KeyConfigure implements Serializable {
	

	/** The map. */
	private HashMap<String, Integer> map;

	/**
	 * Default constructor for the KeyConfigure class. Generates a map with corresponding functions and default keys.
	 */
	public KeyConfigure() {
		map = new HashMap<String, Integer>();
		setLeft(KeyEvent.VK_LEFT); // Default left button (left arrow key)
		setRight(KeyEvent.VK_RIGHT); // Default right button (right arrow key)
		setDown(KeyEvent.VK_DOWN); // Default speedUp button (down arrow key)
		setRotate(KeyEvent.VK_UP); // Default rotate button (up arrow key)
		setPause(KeyEvent.VK_SPACE); // Default pause button (space)
	}
	
	public String[][] getKeyStrings() {
		String[][] keyString = {	
				{"move left", SettingsGUI.getKeyText(this.getLeft()), "Left"},
				{"move right", SettingsGUI.getKeyText(this.getRight()), "Right"},
				{"go down", SettingsGUI.getKeyText(this.getDown()), "Down"},
				{"rotate", SettingsGUI.getKeyText(this.getRotate()), "Rotate"},
				{"pause/continue", SettingsGUI.getKeyText(this.getPause()), "Pause"}
		};
		return keyString;
	}

	/**
	 * Gets the left key.
	 *
	 * @return The key of function Left.
	 */
	public int getLeft() {
		return map.get("Left");
	}

	/**
	 * Sets the left key.
	 *
	 * @param left The new key for function Left. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location.   
	 */
	public void setLeft(int left) {	
		map.put("Left", left);

	}

	/**
	 * Gets the right key.
	 *
	 * @return The key of function Right.
	 */
	public int getRight() {
		return map.get("Right");
	}

	/**
	 * Sets the right key.
	 *
	 * @param right The new key for function Right. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRight(int right) {	
		map.put("Right", right);

	}

	/**
	 * Gets the down key.
	 *
	 * @return The key of function Down.
	 */
	public int getDown() {
		return map.get("Down");
	}

	/**
	 * Sets the down key.
	 *
	 * @param down The new key for function Down. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setDown(int down) {		
		map.put("Down", down);

	}

	/**
	 * Gets the rotate key.
	 *
	 * @return The key of function Rotate.
	 */
	public int getRotate() {
		return map.get("Rotate");
	}

	/**
	 * Sets the rotate key.
	 *
	 * @param rotate The new key for function Rotate. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRotate(int rotate) {	
		map.put("Rotate", rotate);

	}

	/**
	 * Gets the pause key.
	 *
	 * @return The key of function Pause. 
	 */
	public int getPause() {
		return map.get("Pause");
	}

	/**
	 * Sets the pause key.
	 *
	 * @param pause The new key for function Pause. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setPause(int pause){ 	
		map.put("Pause", pause);

	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public HashMap<String, Integer> getMap(){
		return map;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the map
	 */
	public void setMap(HashMap<String, Integer> map){
		this.map = map;
	}
}
