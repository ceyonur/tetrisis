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

public class SButton extends JButton {

	private Font font;
	private int type;

	public SButton(String text, int type) {
		super(text);
		loadFont();
		this.setFont(font);
		this.setType(type);
	}
	
	SButton(int type) {
		super();
		loadFont();
		setType(type);
	}
	
	public SButton(Icon icon, int type) {
		super(icon);
		loadFont();
		this.setFont(font);
		this.setType(type);
	}
	
	private void loadFont() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/forced_square.ttf"));
		} catch (Exception e) {
			font = new Font("Helvetica", Font.PLAIN, 22);
			e.printStackTrace();
		}
	}

	public void setText(String text) {
		super.setText(text);
	}

	public void changeState() {
		if (Math.abs(type) > 10) {
			setType(type * -1);
		}
	}
	
	public void setIcon(int type) {
		super.setIcon(getIcon(type));
	}
	
	public static Icon getIcon(int type) {
		String filename = "assets/images/";
		switch (type) {
		case SOUND_BUTTON_MUTE:
			filename += "mute.png" ;
			break;
		case SOUND_BUTTON_UNMUTE:
			filename += "unmute.png" ;
			break;
		case SOUND_BUTTON_MUSIC_ON:
			filename += "musicon.png" ;
			break;
		case SOUND_BUTTON_MUSIC_OFF:
			filename += "musicoff.png" ;
			break;
		case SOUND_BUTTON_EFFECTS_ON:
			filename += "effectson.png" ;
			break;
		case SOUND_BUTTON_EFFECTS_OFF:
			filename += "effectsoff.png" ;
			break;
		default:
			filename = "";
		}
		return new ImageIcon(filename);
	}
	
	private void setType(int t) {
		this.type = t;
		float f = 24F;
		Color color = new SColor(52, 152, 219);
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
				this.setPreferredSize(new Dimension(120,30));
				this.setMinimumSize(new Dimension(120,30));
				break;
			case SOUND_BUTTON_MUTE:
				this.setPreferredSize(new Dimension(80,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_UNMUTE:
				this.setPreferredSize(new Dimension(80,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_MUSIC_ON:
				this.setPreferredSize(new Dimension(80,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_MUSIC_OFF:
				this.setPreferredSize(new Dimension(80,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_EFFECTS_ON:
				this.setPreferredSize(new Dimension(80,40));
				this.setIcon(type);
				break;
			case SOUND_BUTTON_EFFECTS_OFF:
				this.setPreferredSize(new Dimension(80,40));
				this.setIcon(type);
				break;
			default:
				this.setSize(140, 40);
				break;
		}
		font = font.deriveFont(f);
		this.setFont(font);
	}
	
	public static final int MAIN_MENU_BUTTON = 1;
	public static final int SETTINGS_BUTTON = 2;
	public static final int HIGHSCORES_BUTTON = 3;
	public static final int GAME_BUTTON = 4;
	public static final int SOUND_BUTTON_UNMUTE = 11;
	public static final int SOUND_BUTTON_MUTE = -11;
	public static final int SOUND_BUTTON_MUSIC_ON = 12;
	public static final int SOUND_BUTTON_MUSIC_OFF = -12;
	public static final int SOUND_BUTTON_EFFECTS_ON = 13;
	public static final int SOUND_BUTTON_EFFECTS_OFF = -13;
	
}
