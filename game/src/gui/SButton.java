package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.Icon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * The class generates modified buttons for GUI
 * @author atilberk
 *
 */
public class SButton extends JButton {

	/**
	 * Font field for the label
	 */
	private Font font;
	/**
	 * Type field of the label
	 */
	private int type;

	/**
	 * Constructor
	 * Creates a button with given string of given type
	 * @param text
	 * @param type
	 */
	public SButton(String text, int type) {
		super(text);
		loadFont();
		this.setFont(font);
		this.setType(type);
	}
	
	/**
	 * Constructor
	 * Creates a button of given type
	 * @param type
	 */
	public SButton(int type) {
		super();
		loadFont();
		setType(type);
	}
	
	/**
	 * Constructor
	 * Creates a button with given icon image of given type
	 * @param icon image
	 * @param type
	 */
	public SButton(Icon icon, int type) {
		super(icon);
		loadFont();
		this.setFont(font);
		this.setType(type);
	}
	
	/**
	 * Sets the font field from the source, sets font to Helvetica if file reading process fails.
	 */
	private void loadFont() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/forced_square.ttf"));
		} catch (Exception e) {
			font = new Font("Helvetica", Font.PLAIN, 22);
			e.printStackTrace();
		}
	}

	/**
	 * Changes the state of iconic, dual state buttons.
	 * Does nothing on other types of buttons.
	 */
	public void changeState() {
		if (Math.abs(type) > 10) {
			setType(type * -1);
		}
	}
	
	/**
	 * Sets the icon of the button to the corresponding icon for the given type.
	 * @param type
	 */
	private void setIcon(int type) {
		super.setIcon(getIcon(type));
	}
	
	/**
	 * Returns the icon of the button corresponding to the given type
	 * @param type
	 * @return icon image
	 */
	private static Icon getIcon(int type) {
		String filename = "assets/images/";
		switch (type) {
		case SOUND_BUTTON_MUTE:
			filename += "mute.png" ;
			break;
		case SOUND_BUTTON_UNMUTE:
			filename += "unmute.png" ;
			break;
		case SOUND_BUTTON_MUSIC_ON:
			filename += "music_on.png" ;
			break;
		case SOUND_BUTTON_MUSIC_OFF:
			filename += "music_off.png" ;
			break;
		case SOUND_BUTTON_EFFECTS_ON:
			filename += "effects_on.png" ;
			break;
		case SOUND_BUTTON_EFFECTS_OFF:
			filename += "effects_off.png" ;
			break;
		default:
			filename = "";
		}
		return new ImageIcon(filename);
	}
	
	/**
	 * Sets the type of the label
	 * @param type
	 */
	private void setType(int t) {
		this.type = t;
		float f = 24F;
		Color color = SColor.buttonColor;
		switch (type) {
			case MAIN_MENU_BUTTON:
				f = 52F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
				this.setPreferredSize(new Dimension(330,60));
				break;
			case SETTINGS_BUTTON:
				f = 36F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setMinimumSize(new Dimension(120,30));
				break;
			case HIGHSCORES_BUTTON:
				f = 36F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setMinimumSize(new Dimension(120,30));
				break;
			case GAME_BUTTON:
				f = 36F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setPreferredSize(new Dimension(120,50));
				this.setMinimumSize(new Dimension(120,50));
				break;
			case GAMEOVER_BUTTON:
				f = 30F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setPreferredSize(new Dimension(100,40));
				this.setMinimumSize(new Dimension(100,40));
				break;
			case GAMEOVER_SUBMISSION_BUTTON:
				f = 24F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setPreferredSize(new Dimension(120,25));
				this.setMinimumSize(new Dimension(120,25));
				break;
			case SOUND_BUTTON_MUTE:
				this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
				this.setBackground(color);
				this.setPreferredSize(new Dimension(40,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_UNMUTE:
				this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
				this.setBackground(color);
				this.setPreferredSize(new Dimension(40,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_MUSIC_ON:
				this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
				this.setBackground(color);
				this.setPreferredSize(new Dimension(40,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_MUSIC_OFF:
				this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
				this.setBackground(color);
				this.setPreferredSize(new Dimension(40,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_EFFECTS_ON:
				this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
				this.setBackground(color);
				this.setPreferredSize(new Dimension(40,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_EFFECTS_OFF:
				this.setBorder(BorderFactory.createLineBorder(Color.white, 3));
				this.setBackground(color);
				this.setPreferredSize(new Dimension(40,40));
				this.setIcon(type);
				break;
			default:
				this.setSize(140, 40);
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	
	/**
	 * Type values of SLabel
	 */	
	public static final int MAIN_MENU_BUTTON = 1;
	public static final int SETTINGS_BUTTON = 2;
	public static final int HIGHSCORES_BUTTON = 3;
	public static final int GAME_BUTTON = 4;
	public static final int GAMEOVER_BUTTON = 5;
	public static final int GAMEOVER_SUBMISSION_BUTTON = 6;
	public static final int SOUND_BUTTON_UNMUTE = 11;
	public static final int SOUND_BUTTON_MUTE = -11;
	public static final int SOUND_BUTTON_MUSIC_ON = 12;
	public static final int SOUND_BUTTON_MUSIC_OFF = -12;
	public static final int SOUND_BUTTON_EFFECTS_ON = 13;
	public static final int SOUND_BUTTON_EFFECTS_OFF = -13;
	
}
