package gui;

import javax.swing.BorderFactory;
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

public class SButton extends JButton {
	
	public static final int MAIN_MENU_BUTTON = 0;
	public static final int SOUND_BUTTON = 1;
	public static final int SETTINGS_BUTTON = 2;
	
	private Font font;
	private int type;

	public SButton(String text, int type) {
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
	
	public SButton(Icon icon, int type) {
		super(icon);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/forced_square.ttf"));
		} catch (Exception e) {
			font = new Font("Helvetica", Font.PLAIN, 22);
			e.printStackTrace();
		}
		this.setFont(font);
		this.setType(type);
	}

	public void setText(String text) {
		super.setText(text);
	}
	
	private void setType(int t) {
		this.type = t;
		float f = 24F;
		Color color = new SColor(52, 152, 219);
		switch (type) {
			case 0:
				f = 52F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
				this.setPreferredSize(new Dimension(330,60));
				break;
			case 1:
				this.setPreferredSize(new Dimension(80,40));
				break;
			case 2:
				f = 36F;
				this.setBackground(color);
				this.setBorder(BorderFactory.createLineBorder(Color.white, 5));
				this.setForeground(Color.white);
				this.setMinimumSize(new Dimension(120,30));
				break;
			default:
				this.setSize(140, 40);
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	
}
