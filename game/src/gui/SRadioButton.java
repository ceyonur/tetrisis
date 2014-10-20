package gui;

import java.awt.*;
import java.io.FileInputStream;

import javax.swing.*;

public class SRadioButton extends JRadioButton {
	
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
	 * Creates a radio button with given string of given type
	 * @param text
	 * @param type
	 */
	public SRadioButton(String text, int type) {
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
	 * Sets the type of the radio button
	 * @param type
	 */
	private void setType(int type) {
		float f;
		switch (type) {
			case SETTINGS_RADIO:
				f = 17F;
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
	 * Type values of SRadioButton
	 */	
	public static final int SETTINGS_RADIO = 21;
}
