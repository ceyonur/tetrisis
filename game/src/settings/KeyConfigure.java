package settings;

import java.awt.event.KeyEvent;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyConfigure. This class maps the game functions to keys.
 * @author ceyonur
 */
public class KeyConfigure {
	
	/** The map. */
	HashMap<String, Integer> map;

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
	 * @return the key of function Left.
	 */
	public int getLeft() {
		return map.get("Left");
	}

	/**
	 * Sets the left.
	 *
	 * @param left the new key for function Left. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location.   
	 */
	public void setLeft(int left) {
		if (map.containsValue(left)) {
			for (String key : map.keySet()) {
				if (map.get(key) == left) {
					map.remove(key);
				}
			}
		}
		map.put("Left", left);

	}

	/**
	 * Gets the right.
	 *
	 * @return the key of function Right.
	 */
	public int getRight() {
		return map.get("Right");
	}

	/**
	 * Sets the right.
	 *
	 * @param right the new key for function Right. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRight(int right) {
		if (map.containsValue(right)) {
			for (String key : map.keySet()) {
				if (map.get(key) == right) {
					map.remove(key);
				}
			}
		}
		map.put("Right", right);

	}

	/**
	 * Gets the down.
	 *
	 * @return the key of function Down.
	 */
	public int getDown() {
		return map.get("Down");
	}

	/**
	 * Sets the down.
	 *
	 * @param down the new key for function Down. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setDown(int down) {
		if (map.containsValue(down)) {
			for (String key : map.keySet()) {
				if (map.get(key) == down) {
					map.remove(key);
				}
			}
		}
		map.put("Down", down);

	}

	/**
	 * Gets the rotate.
	 *
	 * @return the key of function Rotate.
	 */
	public int getRotate() {
		return map.get("Rotate");
	}

	/**
	 * Sets the rotate.
	 *
	 * @param rotate the new key for function Rotate. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setRotate(int rotate) {
		if (map.containsValue(rotate)) {
			for (String key : map.keySet()) {
				if (map.get(key) == rotate) {
					map.remove(key);
				}
			}
		}
		map.put("Rotate", rotate);

	}

	/**
	 * Gets the pause.
	 *
	 * @return the key of function Pause. 
	 */
	public int getPause() {
		return map.get("Pause");
	}

	/**
	 * Sets the pause.
	 *
	 * @param pause the new key for function Pause. It checks whether this key is already used;
	 * if it is used, then the key is removed from last location. 
	 */
	public void setPause(int pause) {
		if (map.containsValue(pause)) {
			for (String key : map.keySet()) {
				if (map.get(key) == pause) {
					map.remove(key);
				}
			}
		}
		map.put("Pause", pause);

	}

}
