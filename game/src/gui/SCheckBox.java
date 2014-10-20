package gui;

import java.awt.*;
import java.io.FileInputStream;

import javax.swing.*;

public class SCheckBox extends JCheckBox {
	
	public static final int SETTINGS_RADIO = 21;
	
	Font font;

	public SCheckBox(String text, int type) {
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
