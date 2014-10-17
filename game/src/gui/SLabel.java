package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class SLabel extends JLabel {

	public static final int MAIN_MENU_TITLE = 0;
	public static final int MAIN_MENU_AUTHOR = 1;
	
	private Font font;
	
	public SLabel(String text, int type) {
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
			case 0:
				f = 64F;
				this.setForeground(Color.white);
				break;
			case 1:
				f = 32F;
				this.setForeground(Color.white);
				break;
			default:
				f = 24F;
				this.setSize(140, 40);
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	


}
