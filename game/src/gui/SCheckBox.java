package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 * The class generates modified check boxes for GUI
 * @author atilberk
 *
 */
public class SCheckBox extends JCheckBox {
		
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
	 * Creates a check box with given string of given type
	 * @param text
	 * @param type
	 */
	public SCheckBox(String text, int type) {
		super(text);
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
	 * Sets the type of the checkbox
	 * @param type
	 */
	private void setType(int t) {
		type = t;
		float f;
		switch (t) {
			case 21:
				f = 18F;
				this.setForeground(Color.white);
				break;
			default:
				f = 24F;
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	
	/**
	 * Type values of SCheckBox
	 */	
	public static final int SETTINGS_RADIO = 21;
}
