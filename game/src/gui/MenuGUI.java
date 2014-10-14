package gui;

import sun.audio.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;

public class MenuGUI extends JFrame {

	JFrame settingsGui;
	AudioPlayer AP = AudioPlayer.player;
	AudioStream AS;
	AudioData AD;
	ContinuousAudioDataStream loop = null;

	public MenuGUI() {
		super();
		setTitle("Main Menu");
		setSize(680, 690);
		setLocation(780, 150);
		playAudio(true);


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // windowClosing
		});


		//buton panel tum butonlari tutuyor
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setLocation(950, 500);
		buttonPanel.setSize(250, 70);

		JButton newGame = new JButton();

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPanel.setVisible(false);
				setTitle("Tetris-v1.0");
				setResizable(true);
			}

		});
		//sound button olusturuldu
		final JButton sound = new JButton();
		final JButton durdur = new JButton();
		sound.setVisible(false);
		durdur.setVisible(true);


		durdur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAudio(false);
				durdur.setVisible(false);
				sound.setVisible(true);
			}	
		});


		sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playAudio(true);				
				sound.setVisible(false);
				durdur.setVisible(true);
			}
		});
		
		//new game button eklendi
		ImageIcon img = new ImageIcon("temp1.png");
		newGame.setIcon(img);

		newGame.setLocation(120, 90);
		newGame.setSize(370, 93);
		buttonPanel.add(newGame);

		JButton settings = new JButton();
		ImageIcon img2 = new ImageIcon("temp2.png");
		settings.setIcon(img2);

		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsGui = new SettingsGUI();
			}	
		});

		settings.setLocation(120, 180);
		settings.setSize(370, 93);
		buttonPanel.add(settings);


		//highscore button eklendi
		JButton highScores = new JButton();
		ImageIcon img3 = new ImageIcon("temp3.png");
		highScores.setIcon(img3);

		highScores.setLocation(120, 270);
		highScores.setSize(370, 93);
		buttonPanel.add(highScores);

		JButton quit = new JButton();
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		//quit button eklendi
		ImageIcon img4 = new ImageIcon("temp4.png");
		quit.setIcon(img4);

		quit.setLocation(120, 360);
		quit.setSize(370, 93);
		buttonPanel.add(quit);
		// Sound button eklendi
		ImageIcon img5 = new ImageIcon("sound.png");
		ImageIcon img6 = new ImageIcon("mute.png");
		sound.setIcon(img6);
		sound.setLocation(260, 590);
		sound.setSize(80, 40);
		buttonPanel.add(sound);


		// mute button eklendi	

		durdur.setIcon(img5);
		durdur.setLocation(260, 590);
		durdur.setSize(80, 40);
		buttonPanel.add(durdur);

		this.add(buttonPanel);
	}

	public static void main(String[] args) {
		JFrame f = new MenuGUI();
		f.setResizable(false);

		f.show();
	}
	
	private void playAudio(boolean status){
		if (status){
			try{
				AS = new AudioStream(new FileInputStream("backGround.wav"));
				AD = AS.getData();
				loop = new ContinuousAudioDataStream(AD);
			}
			catch(IOException e){}
			AP.start(loop);
		} else {
			AP.stop(loop);
		}
	}
}
