package gui;

import java.awt.*;
import java.io.FileInputStream;

import javax.swing.*;

/**
 * The class generates modified labels for GUI
 * @author atilberk
 */
public class SLabel extends JLabel {

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
	 * Creates an empty label.
	 */
	public SLabel(){
		super();
	}
	
	/**
	 * Constructor
	 * Creates a label with given type.
	 */
	public SLabel(int type){
		this("", type);
	}
	
	/**
	 * Constructor
	 * Creates a label of given type with empty string and horizontal alignment
	 */
	public SLabel(int type, int horizontalAlignment){
		this("", type, horizontalAlignment);
	}
	
	/**
	 * Constructor
	 * Creates a label with given text of given type.
	 */
	public SLabel(String text, int type) {
		super(text);
		this.loadFont();
		this.setFont(font);
		this.setType(type);
	}
	
	/**
	 * Constructor
	 * Creates a label with given text of given type with horizontal alignment.
	 */
	public SLabel(String text, int type, int horizontalAlignment) {
		super(text, horizontalAlignment);
		this.loadFont();
		this.setFont(font);
		this.setType(type);
	}
	
	/**
	 * Loads the font, if fails loads Helvetica as default
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
	 * Sets the type of the label
	 * @param type
	 */
	private void setType(int t) {
		this.type = t;
		float f;
		switch (t) {
			case MAIN_MENU_TITLE:
				f = 64F;
				this.setForeground(Color.white);
				break;
			case MAIN_MENU_AUTHOR:
				f = 32F;
				this.setForeground(Color.white);
				break;
			case SETTINGS_LABEL:
				f = 32F;
				this.setForeground(Color.white);
				this.setPreferredSize(new Dimension(500,40));
				break;
			case SETTINGS_FIELD_LABEL:
				f = 24F;
				this.setForeground(Color.white);
				break;
			case SETTINGS_WARNING_LABEL:
				f = 18F;
				this.setForeground(Color.white);
				break;
			case HIGHSCORES_HEADER_LABEL:
				f = 30F;
				this.setForeground(Color.white);
				break;
			case HIGHSCORES_NUMBER_LABEL:
				f = 30F;
				this.setForeground(Color.white);
				break;
			case HIGHSCORES_CONTENT_LABEL:
				f = 24F;
				this.setForeground(Color.white);
				break;
			case PANEL_PAUSE_LABEL:
				f = 64F;
				this.setForeground(Color.white);
				break;
			case SIDE_PANEL_NEXT:
				f = 32F;
				this.setForeground(Color.white);
				break;
			case SIDE_PANEL_LINES:
				f = 48F;
				this.setForeground(Color.white);
				break;
			case SIDE_PANEL_SCORE:
				f = 48F;
				this.setForeground(Color.white);
				break;
			case SIDE_PANEL_LEVEL:
				f = 48F;
				this.setForeground(Color.white);
				break;
			case GAMEOVER_HEADER_LABEL:
				f = 64F;
				this.setForeground(Color.white);
				break;
			case GAMEOVER_INFO_LABEL:
				f = 48F;
				this.setForeground(Color.white);
				break;
			case GAMEOVER_SUBMISSION_LABEL:
				f = 24F;
				this.setForeground(Color.white);
				break;				
			default:
				f = 48F;
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	
	/**
	 * Type values of SLabel
	 */	
	public static final int MAIN_MENU_TITLE = 10;
	public static final int MAIN_MENU_AUTHOR = 11;
	public static final int SETTINGS_LABEL = 20;
	public static final int SETTINGS_FIELD_LABEL = 21;
	public static final int SETTINGS_WARNING_LABEL = 22;
	public static final int HIGHSCORES_HEADER_LABEL = 30;
	public static final int HIGHSCORES_NUMBER_LABEL = 31;
	public static final int HIGHSCORES_CONTENT_LABEL = 32;
	public static final int PANEL_PAUSE_LABEL = 40;
	public static final int SIDE_PANEL_NEXT = 41;
	public static final int SIDE_PANEL_LINES = 42;
	public static final int SIDE_PANEL_SCORE = 43;
	public static final int SIDE_PANEL_LEVEL = 44;
	public static final int GAMEOVER_HEADER_LABEL = 50;
	public static final int GAMEOVER_INFO_LABEL = 51;
	public static final int GAMEOVER_SUBMISSION_LABEL = 52;

}
