package gui;

import java.awt.BorderLayout;
import java.awt.Component;

import settings.*;

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

public class SettingsGUI extends JFrame {
	private BoardSize boardSizeObject;
	private KeyConfigure keyConfigureObject;
	private LevelChoice levelChoiceObject;
	private PieceChoice pieceChoiceObject;
	private Settings settingsObject;
	private HashMap<String, Integer> keyMap;	
	
	public SettingsGUI(Settings settings) {
		super();
		setTitle("Settings");
		setSize(480, 690);
		setLocation(250, 150);
		setResizable(false);
		settingsObject= settings;
		boardSizeObject = settingsObject.getSizeChoice();
		keyConfigureObject = settingsObject.getKeyConfigure();
		levelChoiceObject = settingsObject.getLevelChoice();
		pieceChoiceObject = settingsObject.getPieceChoice();
		keyMap = keyConfigureObject.getMap();
		
	

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

		if(boardSizeObject.isSmall()){
			small.setSelected(true);
		}
		else if(boardSizeObject.isLarge()) {
			large.setSelected(true);
		}
		else{
			medium.setSelected(true);
		}
		
		this.setVisible(true);
		mainPanel.add(sizePanel);

		// //////////////////////////////////////////////////LEVEL PANEL
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

		switch(levelChoiceObject.getLevel()){
		case 1: lvl1.setSelected(true);
		break;
		case 2: lvl2.setSelected(true);
		break;
		case 3: lvl3.setSelected(true);
		break;
		case 4: lvl4.setSelected(true);
		break;
		case 5: lvl5.setSelected(true);
		break;		
		}
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
		
		if(pieceChoiceObject.hasBoth()){
			tetra.setSelected(true);
			tri.setSelected(true);
		}
		else if(pieceChoiceObject.hasTetriminos()){
			tetra.setSelected(true);
		}
		else if(pieceChoiceObject.hasTriminos()){
			tri.setSelected(true);
		}
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

		final JTextField leftField = new JTextField(getKeyText(keyConfigureObject.getLeft()));
		final JTextField rightField = new JTextField(getKeyText(keyConfigureObject.getRight()));
		final JTextField downField = new JTextField(getKeyText(keyConfigureObject.getDown()));
		final JTextField rotateField = new JTextField(getKeyText(keyConfigureObject.getRotate()));
		final JTextField pauseField = new JTextField(getKeyText(keyConfigureObject.getPause()));
		
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
				if (keyMap.containsValue(e.getKeyCode())) {
					for (String key : keyMap.keySet()) {
						if (keyMap.get(key) == e.getKeyCode() && key != "Left" ) {
							keyMap.put(key,0);
						}
					}
				}
					keyMap.put("Left", e.getKeyCode());					
					leftField.setText(getKeyText(keyMap.get("Left")));
					rightField.setText(getKeyText(keyMap.get("Right")));
					downField.setText(getKeyText(keyMap.get("Down")));
					rotateField.setText(getKeyText(keyMap.get("Rotate")));
					pauseField.setText(getKeyText(keyMap.get("Pause")));					
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}
		});

		// /////////////////////////////////rightKEy
		
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
				if (keyMap.containsValue(e.getKeyCode())) {
					for (String key : keyMap.keySet()) {
						if (keyMap.get(key) == e.getKeyCode() && key != "Right" ) {
							keyMap.put(key,0);
						}
					}
				}
				keyMap.put("Right", e.getKeyCode());
					leftField.setText(getKeyText(keyMap.get("Left")));
					rightField.setText(getKeyText(keyMap.get("Right")));
					downField.setText(getKeyText(keyMap.get("Down")));
					rotateField.setText(getKeyText(keyMap.get("Rotate")));
					pauseField.setText(getKeyText(keyMap.get("Pause")));
			}

			

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}
		});
		// ////////////////////////////////////rotateKey
		
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
				if (keyMap.containsValue(e.getKeyCode())) {
					for (String key : keyMap.keySet()) {
						if (keyMap.get(key) == e.getKeyCode() && key != "Rotate" ) {
							keyMap.put(key,0);
						}
					}
				}
				keyMap.put("Rotate", e.getKeyCode());
					leftField.setText(getKeyText(keyMap.get("Left")));
					rightField.setText(getKeyText(keyMap.get("Right")));
					downField.setText(getKeyText(keyMap.get("Down")));
					rotateField.setText(getKeyText(keyMap.get("Rotate")));
					pauseField.setText(getKeyText(keyMap.get("Pause")));
			

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}
		});
		// //////////////////////////////////////////////////////////////////moveDown
	
		downField.setSize(60, 20);
		downField.setLocation(260, 150);
		keyPanel.add(downField);

		JLabel enterSpeed = new JLabel("Move Down");
		enterSpeed.setLocation(170, 150);
		enterSpeed.setSize(140, 20);
		keyPanel.add(enterSpeed);

		downField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				downField.setText(null);				
				if (keyMap.containsValue(e.getKeyCode())) {
					for (String key : keyMap.keySet()) {
						if (keyMap.get(key) == e.getKeyCode() && key != "Down" ) {
							keyMap.put(key,0);
						}
					}
				}
				keyMap.put("Down", e.getKeyCode());
					leftField.setText(getKeyText(keyMap.get("Left")));
					rightField.setText(getKeyText(keyMap.get("Right")));
					downField.setText(getKeyText(keyMap.get("Down")));
					rotateField.setText(getKeyText(keyMap.get("Rotate")));
					pauseField.setText(getKeyText(keyMap.get("Pause")));
			

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}
		});
		// ////////////////////////////////////////////////////////////////////pause
	
		pauseField.setSize(60, 20);
		pauseField.setLocation(260, 180);
		keyPanel.add(pauseField);

		JLabel enterPause = new JLabel("Pause");
		enterPause.setLocation(170, 180);
		enterPause.setSize(140, 20);
		keyPanel.add(enterPause);

		
		pauseField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				pauseField.setText(null);				
				if (keyMap.containsValue(e.getKeyCode())) {
					for (String key : keyMap.keySet()) {
						if (keyMap.get(key) == e.getKeyCode() && key != "Pause") {
							keyMap.put(key,0);
						}
					}
				}
				keyMap.put("Pause", e.getKeyCode());
					leftField.setText(getKeyText(keyMap.get("Left")));
					rightField.setText(getKeyText(keyMap.get("Right")));
					downField.setText(getKeyText(keyMap.get("Down")));
					rotateField.setText(getKeyText(keyMap.get("Rotate")));
					pauseField.setText(getKeyText(keyMap.get("Pause")));
			}


			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
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
				if(boardSizeObject.isSmall())
					small.setSelected(true);
				else if(boardSizeObject.isLarge())
					large.setSelected(true);
				else
					medium.setSelected(true);
				
				switch(levelChoiceObject.getLevel()){
				case 1: lvl1.setSelected(true);
				break;
				case 2: lvl2.setSelected(true);
				break;
				case 3: lvl3.setSelected(true);
				break;
				case 4: lvl4.setSelected(true);
				break;
				case 5: lvl5.setSelected(true);
				break;		
				}
				
				if (pieceChoiceObject.hasTriminos()){
					tri.setSelected(true);
					tetra.setSelected(false);
				}
				else if(pieceChoiceObject.hasBoth()){
					tri.setSelected(true);
					tetra.setSelected(true);
				}
				else{
					tetra.setSelected(true);
					tri.setSelected(false);
				}
				
				
					leftField.setText(getKeyText(keyConfigureObject.getLeft()));
					rightField.setText(getKeyText(keyConfigureObject.getRight()));
					downField.setText(getKeyText(keyConfigureObject.getDown()));
					rotateField.setText(getKeyText(keyConfigureObject.getRotate()));
					pauseField.setText(getKeyText(keyConfigureObject.getPause()));
				
				dispose();	
			}
		});

		defaultt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				uyari.hide();
				KeyConfigure defaultKeyConfig = new KeyConfigure();
				keyMap = defaultKeyConfig.getMap();
				leftField.setText(getKeyText(KeyEvent.VK_LEFT));
				rightField.setText(getKeyText(KeyEvent.VK_RIGHT));
				downField.setText(getKeyText(KeyEvent.VK_DOWN));
				rotateField.setText(getKeyText(KeyEvent.VK_UP));
				pauseField.setText(getKeyText(KeyEvent.VK_SPACE));
				medium.setSelected(true);
				lvl1.setSelected(true);
				tetra.setSelected(true);
				tri.setSelected(false);				
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (keyMap.containsValue(0)) {
					uyari.show();
				}
					else{
					uyari.hide();
					keyConfigureObject.setMap(keyMap);
					hide();					
					
					
				

				if (tetra.isSelected() && tri.isSelected())
					pieceChoiceObject.setBoth(true);
				else if (tri.isSelected()){
					pieceChoiceObject.setTriminos(true);
					pieceChoiceObject.setTetriminos(false);
				}
				else{
					pieceChoiceObject.setTetriminos(true);
					pieceChoiceObject.setTriminos(false);
				}

				if (lvl1.isSelected())
					levelChoiceObject.setLevel(1);
				else if (lvl2.isSelected())
					levelChoiceObject.setLevel(2);
				else if (lvl3.isSelected())
					levelChoiceObject.setLevel(3);
				else if (lvl4.isSelected())
					levelChoiceObject.setLevel(4);
				else if (lvl5.isSelected())
					levelChoiceObject.setLevel(5);
				
				if(small.isSelected())
					boardSizeObject.setSmall();
				else if(large.isSelected())
					boardSizeObject.setLarge();
				else
					boardSizeObject.setMedium();
				
				dispose();
				
				
			}
				
			}
		});

		mainPanel.add(keyPanel);
		mainPanel.add(finishPanel);

		this.add(mainPanel);
		this.setVisible(true);

	}	

	public static void main(String[] args) {
		JFrame f = new SettingsGUI(new Settings());

		f.show();
	}
	
	private static String getKeyText(int a){
		if(a != 0){
			return KeyEvent.getKeyText(a);
		}
		else {
			return null;
		}
	}

}