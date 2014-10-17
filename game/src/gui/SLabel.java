package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileInputStream;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class SLabel extends JLabel {

	public static final int MAIN_MENU_TITLE = 10;
	public static final int MAIN_MENU_AUTHOR = 11;
	public static final int SETTINGS_LABEL = 20;
	public static final int SETTINGS_FIELD_LABEL = 21;
	public static final int SETTINGS_WARNING_LABEL = 22;
	public static final int HIGHSCORES_HEADER_LABEL = 30;
	public static final int HIGHSCORES_NUMBER_LABEL = 31;
	public static final int HIGHSCORES_CONTENT_LABEL = 32;
	
	private Font font;
	
	public SLabel(){
		super();
	}
	
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
	
	public SLabel(String text, int type, int horizontalAlignment) {
		super(text, horizontalAlignment);
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
			case 10:
				f = 64F;
				this.setForeground(Color.white);
				break;
			case 11:
				f = 32F;
				this.setForeground(Color.white);
				break;
			case 20:
				f = 32F;
				this.setForeground(Color.white);
				this.setPreferredSize(new Dimension(500,40));
				break;
			case 21:
				f = 24F;
				this.setForeground(Color.white);
				break;
			case 22:
				f = 18F;
				this.setForeground(Color.white);
				break;
			case 30:
				f = 30F;
				this.setForeground(Color.white);
				break;
			case 31:
				f = 30F;
				this.setForeground(Color.white);
				break;
			case 32:
				f = 24F;
				this.setForeground(Color.white);
				break;
			default:
				f = 20F;
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	


}
