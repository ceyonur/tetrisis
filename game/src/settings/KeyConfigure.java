package settings;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * The Class KeyConfigure. This class maps the game functions to keys.
 * @author ceyonur
 */
public class KeyConfigure {
	
	/** The map. */
	private HashMap<String, Integer> map;

	/**
	 * Default constructor for the KeyConfigure class. Generates a map.
	 */
	public KeyConfigure() {
		map = new HashMap<String, Integer>();
		setLeft(KeyEvent.VK_LEFT);
		setRight(KeyEvent.VK_RIGHT);
		setDown(KeyEvent.VK_DOWN);
		setRotate(KeyEvent.VK_UP);
		setPause(KeyEvent.VK_SPACE);
	}

	/**
	 * Gets the left.
	 *
	 * @return The key of function Left.
	 */
	public int getLeft() {
		return map.get("Left");
	}

	/**
	 * Sets the left.
	 *
	 * @param left The new key for function Left. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location.   
	 */
	public void setLeft(int left) {	
		map.put("Left", left);

	}

	/**
	 * Gets the right.
	 *
	 * @return The key of function Right.
	 */
	public int getRight() {
		return map.get("Right");
	}

	/**
	 * Sets the right.
	 *
	 * @param right The new key for function Right. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRight(int right) {	
		map.put("Right", right);

	}

	/**
	 * Gets the down.
	 *
	 * @return The key of function Down.
	 */
	public int getDown() {
		return map.get("Down");
	}

	/**
	 * Sets the down.
	 *
	 * @param down The new key for function Down. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setDown(int down) {		
		map.put("Down", down);

	}

	/**
	 * Gets the rotate.
	 *
	 * @return The key of function Rotate.
	 */
	public int getRotate() {
		return map.get("Rotate");
	}

	/**
	 * Sets the rotate.
	 *
	 * @param rotate The new key for function Rotate. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRotate(int rotate) {	
		map.put("Rotate", rotate);

	}

	/**
	 * Gets the pause.
	 *
	 * @return The key of function Pause. 
	 */
	public int getPause() {
		return map.get("Pause");
	}

	/**
	 * Sets the pause.
	 *
	 * @param pause The new key for function Pause. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setPause(int pause){ 	
		map.put("Pause", pause);

	}
	
	public HashMap<String, Integer> getMap(){
		return map;
	}
	
	public void setMap(HashMap<String, Integer> map){
		this.map = map;
	}
}
