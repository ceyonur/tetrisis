package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileInputStream;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;

public class SRadioButton extends JRadioButton {
	
	public static final int SETTINGS_RADIO = 21;
	
	Font font;

	public SRadioButton(String text, int type) {
		super(text);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/forced_square.ttf"));
		} catch (Exception e) {
			font = new Font("Helvetica", Font.PLAIN, 22);
			e.printStackTrace();
		}
		this.setFont(font);
		this.setType(type);
	}
	
	private void setType(int type) {
		float f;
		switch (type) {
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
}
