package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import sun.audio.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.im.InputContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;

public class Settings extends JFrame {
	private int leftKey = 37; // ascii kodlarý
	private int rightKey = 39;
	private int rotateKey = 38;
	private int speedKey = 40;
	private int pauseKey = 32;
	
	private int key1 = 37;
	private int key2 = 39;
	private int key3 = 38;
	private int key4 = 40;
	private int key5 = 32;

	private int boyut = 2; // 1 ise kucuk 2 ise orta 3 ise buyuk
	private int level = 1; // default level
	private int mode = 1; // default blocktype
	HashMap<Integer, String> hm = new HashMap();

	public Settings() {
		super();
		setTitle("Settings");
		setSize(480, 690);
		setLocation(250, 150);
		setResizable(false);
		

		hm.put(32, "space");
		hm.put(37, "left");
		hm.put(39, "right");
		hm.put(38, "up");
		hm.put(40, "down");
		hm.put(17, "ctrl");
		hm.put(16, "shift");
		hm.put(18, "alt");
		hm.put(10, "enter");
		hm.put(27, "esc");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				hide();
			} // windowClosing
		});

		final JRadioButton small = new JRadioButton("Small");
		final JRadioButton medium = new JRadioButton("Medium");
		final JRadioButton large = new JRadioButton("Large");

		ButtonGroup bG = new ButtonGroup();
		bG.add(small);
		bG.add(medium);
		bG.add(large);


		JPanel sizePanel = new JPanel();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		mainPanel.setLayout(null);
		mainPanel.setSize(455, 800);
		mainPanel.setLocation(10, 10);
		
		sizePanel.setLayout(null);
		sizePanel.setLocation(10, 20);
		sizePanel.setSize(455, 90);
		
		sizePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		small.setLocation(120, 50);
		small.setSize(80, 20);

		sizePanel.add(small);

		medium.setLocation(200, 50);
		medium.setSize(80, 20);
		sizePanel.add(medium);

		large.setLocation(280, 50);
		large.setSize(80, 20);
		sizePanel.add(large);

		JLabel l = new JLabel("Please select the board size",
				SwingConstants.CENTER);
		l.setLocation(130, -5);
		l.setSize(220, 60);
		sizePanel.add(l);

		medium.setSelected(true);
		this.setVisible(true);
		mainPanel.add(sizePanel);

		// //////////////////////////////////////////////////LEVEL PANL
		JPanel levelPanel = new JPanel();
		levelPanel.setLayout(null);
		levelPanel.setSize(455, 80);
		levelPanel.setLocation(10, 120);
		
		levelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		final JRadioButton lvl1 = new JRadioButton("Level 1");
		final JRadioButton lvl2 = new JRadioButton("Level 2");
		final JRadioButton lvl3 = new JRadioButton("Level 3");
		final JRadioButton lvl4 = new JRadioButton("Level 4");
		final JRadioButton lvl5 = new JRadioButton("Level 5");

		ButtonGroup lG = new ButtonGroup();
		lG.add(lvl1);
		lG.add(lvl2);
		lG.add(lvl3);
		lG.add(lvl4);
		lG.add(lvl5);

		JLabel levelLabel = new JLabel("Please select dificulty level",
				SwingConstants.CENTER);
		levelLabel.setLocation(130, -10);
		levelLabel.setSize(220, 60);

		lvl1.setLocation(45, 40);
		lvl1.setSize(80, 20);

		levelPanel.add(lvl1);

		lvl2.setLocation(125, 40);
		lvl2.setSize(80, 20);
		levelPanel.add(lvl2);

		lvl3.setLocation(205, 40);
		lvl3.setSize(80, 20);
		levelPanel.add(lvl3);

		lvl4.setLocation(285, 40);
		lvl4.setSize(80, 20);
		levelPanel.add(lvl4);

		lvl5.setLocation(365, 40);
		lvl5.setSize(80, 20);
		levelPanel.add(lvl5);

		lvl1.setSelected(true);
		levelPanel.add(levelLabel);
		mainPanel.add(levelPanel);

		// /////////////////////////////////////////////////block paneli
		JPanel blockPanel = new JPanel();
		blockPanel.setLayout(null);
		blockPanel.setSize(455, 80);
		blockPanel.setLocation(10, 210);
		blockPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		final JCheckBox tetra = new JCheckBox("Tetriminos");
		final JCheckBox tri = new JCheckBox("Triminos");

		tetra.setSelected(true);
		JLabel blockLabel = new JLabel("Please select block type",
				SwingConstants.CENTER);
		blockLabel.setLocation(130, -10);
		blockLabel.setSize(220, 60);

		tetra.setLocation(120, 40);
		tetra.setSize(140, 20);

		blockPanel.add(tetra);

		tri.setLocation(260, 40);
		tri.setSize(140, 20);

		tetra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tetra.isSelected() && !tri.isSelected())
					tetra.setSelected(true);
			}
		});

		tri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tetra.isSelected() && !tri.isSelected())
					tri.setSelected(true);
			}
		});

		blockPanel.add(tri);
		blockPanel.add(blockLabel);

		mainPanel.add(blockPanel);

		// /////////////////////////////////////////keyConfig
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(null);
		keyPanel.setSize(455, 230);
		keyPanel.setLocation(10, 300);

		JLabel keyLabel = new JLabel("Key Configuration", SwingConstants.CENTER);

		keyLabel.setLocation(130, 0);
		keyLabel.setSize(220, 60);

		keyPanel.add(keyLabel);

		final JTextField leftField = new JTextField();
		leftField.setSize(60, 20);
		leftField.setLocation(260, 60);
		keyPanel.add(leftField);

		JLabel enterLeft = new JLabel("Move Left");
		enterLeft.setLocation(170, 60);
		enterLeft.setSize(140, 20);
		keyPanel.add(enterLeft);
		
		keyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		

		leftField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				leftField.setText(null);
				key1 = e.getKeyCode();

				if (hm.containsKey(key1))
					leftField.setText(hm.get(key1));
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		// /////////////////////////////////rightKEy
		final JTextField rightField = new JTextField();
		rightField.setSize(60, 20);
		rightField.setLocation(260, 90);
		keyPanel.add(rightField);

		JLabel enterRight = new JLabel("Move Right");
		enterRight.setLocation(170, 90);
		enterRight.setSize(140, 20);
		keyPanel.add(enterRight);

		rightField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				rightField.setText(null);
				key2 = e.getKeyCode();


				if (hm.containsKey(key2))
					rightField.setText(hm.get(key2));
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		// ////////////////////////////////////rotateKey
		final JTextField rotateField = new JTextField();
		rotateField.setSize(60, 20);
		rotateField.setLocation(260, 120);
		keyPanel.add(rotateField);

		JLabel enterRotate = new JLabel("Rotate Blocks");
		enterRotate.setLocation(170, 120);
		enterRotate.setSize(140, 20);
		keyPanel.add(enterRotate);

		rotateField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				rotateField.setText(null);
				key3 = e.getKeyCode();


				if (hm.containsKey(key3))
					rotateField.setText(hm.get(key3));
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		// //////////////////////////////////////////////////////////////////moveDown
		final JTextField speedField = new JTextField();
		speedField.setSize(60, 20);
		speedField.setLocation(260, 150);
		keyPanel.add(speedField);

		JLabel enterSpeed = new JLabel("Move Down");
		enterSpeed.setLocation(170, 150);
		enterSpeed.setSize(140, 20);
		keyPanel.add(enterSpeed);

		speedField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				speedField.setText(null);
				key4 = e.getKeyCode();

				

				if (hm.containsKey(key4))
					speedField.setText(hm.get(key4));
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		// ////////////////////////////////////////////////////////////////////pause
		final JTextField pauseField = new JTextField();
		pauseField.setSize(60, 20);
		pauseField.setLocation(260, 180);
		keyPanel.add(pauseField);

		JLabel enterPause = new JLabel("Pause");
		enterPause.setLocation(170, 180);
		enterPause.setSize(140, 20);
		keyPanel.add(enterPause);

		leftField.setText("left");
		rightField.setText("right");
		rotateField.setText("up");
		speedField.setText("down");
		pauseField.setText("space");
		pauseField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				pauseField.setText(null);
				key5 = e.getKeyCode();


				if (hm.containsKey(key5))
					pauseField.setText(hm.get(key5));
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		// /////////////////////////////////////////////////////////////////finishPanel
		final JPanel finishPanel = new JPanel();
		finishPanel.setLayout(null);
		finishPanel.setLocation(10, 560);
		finishPanel.setSize(455, 80);
		JButton save = new JButton("Save");
		save.setSize(80, 30);
		save.setLocation(280, 20);
		finishPanel.add(save);

		JButton cancel = new JButton("Cancel");
		cancel.setSize(80, 30);
		cancel.setLocation(80, 20);
		finishPanel.add(cancel);

		JButton defaultt = new JButton("Default");
		defaultt.setSize(80, 30);
		defaultt.setLocation(180, 20);
		finishPanel.add(defaultt);

		final JLabel uyari = new JLabel("Incorrect Key Configuration",
				SwingConstants.CENTER);
		uyari.setSize(200, 30);
		uyari.setLocation(50, 45);
		uyari.setForeground(Color.red);
		finishPanel.add(uyari);
		uyari.hide();

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hide();
				if(boyut == 1)
					small.setSelected(true);
				else if(boyut == 3)
					large.setSelected(true);
				else
					medium.setSelected(true);
				
				if (level == 2)
					lvl2.setSelected(true);
				else if (level == 3)
					lvl3.setSelected(true);
				else if (level == 4)
					lvl4.setSelected(true);
				else if (level == 5)
					lvl5.setSelected(true);
				else
					lvl1.setSelected(true);
				
				if (mode == 2){
					tri.setSelected(true);
					tetra.setSelected(false);
				}
				else if(mode == 3){
					tri.setSelected(true);
					tetra.setSelected(true);
				}
				else{
					tetra.setSelected(true);
					tri.setSelected(false);
				}
				
				if (leftKey >= 65 && leftKey <= 90)
					leftField.setText(Character.toString((char) leftKey));
				if (rightKey >= 65 && rightKey <= 90)
					rightField.setText(Character.toString((char) rightKey));
				if (speedKey >= 65 && speedKey <= 90)
					speedField.setText(Character.toString((char) speedKey));
				if (rotateKey >= 65 && rotateKey <= 90)
					rotateField.setText(Character
							.toString((char) rotateKey));
				if (pauseKey >= 65 && pauseKey <= 90)
					pauseField.setText(Character.toString((char) pauseKey));
				
					
			}
		});

		defaultt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key1 = 37;
				key2 = 39;
				key3 = 38;
				key4 = 40;
				key5 = 32;
				leftField.setText("left");
				rightField.setText("right");
				speedField.setText("down");
				rotateField.setText("up");
				pauseField.setText("space");
				medium.setSelected(true);
				lvl1.setSelected(true);
				tetra.setSelected(true);
				tri.setSelected(false);
				
				uyari.hide();
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (key1 != key2 && key1 != key3
						&& key1 != key4 && key1 != key5
						&& key2 != key3 && key2 != key4
						&& key2 != key5 && key3 != key4
						&& key3 != key5 && key4 != key5) {
					hide();
					if (key1 >= 65 && key1 <= 90)
						leftField.setText(Character.toString((char) key1));
					if (key2 >= 65 && key2 <= 90)
						rightField.setText(Character.toString((char) key2));
					if (key4 >= 65 && key4 <= 90)
						speedField.setText(Character.toString((char) key4));
					if (key3 >= 65 && key3 <= 90)
						rotateField.setText(Character
								.toString((char) key3));
					if (key5 >= 65 && key5 <= 90)
						pauseField.setText(Character.toString((char) key5));
				} else {
					leftField.setText(null);
					rightField.setText(null);
					speedField.setText(null);
					rotateField.setText(null);
					pauseField.setText(null);
					uyari.show();
				}

				if (tetra.isSelected() && tri.isSelected())
					mode = 3;
				else if (tri.isSelected())
					mode = 2;
				else
					mode = 1;

				if (lvl1.isSelected())
					level = 1;
				else if (lvl2.isSelected())
					level = 2;
				else if (lvl3.isSelected())
					level = 3;
				else if (lvl4.isSelected())
					level = 4;
				else if (lvl5.isSelected())
					level = 5;
				
				if(small.isSelected())
					boyut = 1;
				else if(large.isSelected())
					boyut = 3;
				else
					boyut = 2;
				
				leftKey = key1;
				rightKey = key2;
				rotateKey = key3;
				speedKey = key4;
				pauseKey = key5;
			}
		});

		mainPanel.add(keyPanel);
		mainPanel.add(finishPanel);

		this.add(mainPanel);
		this.setVisible(true);

	}

	public int getLeftKey() {
		return leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public int getSpeedKey() {
		return speedKey;
	}

	public int getRotateKey() {
		return rotateKey;
	}

	public int getPauseKey() {
		return pauseKey;
	}

	public int getBoardSize() {
		return boyut;
	}

	public int getLevel() {
		return level;
	}

	public int getMode() {
		return mode;
	}

	public static void main(String[] args) {
		JFrame f = new Settings();

		f.show();
	}

}