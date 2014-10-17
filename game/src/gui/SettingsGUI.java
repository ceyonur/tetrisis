package gui;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import settings.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import settings.BoardSize;
import settings.KeyConfigure;
import settings.LevelChoice;
import settings.PieceChoice;
import settings.Settings;

public class SettingsGUI extends JFrame {
	
	GUI gui;
	
	private BoardSize boardSizeObject;
	private KeyConfigure keyConfigureObject;
	private LevelChoice levelChoiceObject;
	private PieceChoice pieceChoiceObject;
	private Settings settingsObject;
	private HashMap<String, Integer> keyMap;	
	
	Color bgcolor = SColor.backgroundColor;;
	
	public SettingsGUI(GUI ui, Settings settings) {
		super();
		gui = ui;
		setSize(570, 690);
		getContentPane().setBackground(bgcolor);
		
		settingsObject= settings;
		boardSizeObject = settingsObject.getBoardSizeChoice();
		keyConfigureObject = settingsObject.getKeyConfigure();
		levelChoiceObject = settingsObject.getLevelChoice();
		pieceChoiceObject = settingsObject.getPieceChoice();
		keyMap = keyConfigureObject.getMap();
		
		JPanel headerPanelContainer = new JPanel();
		headerPanelContainer.setBackground(bgcolor);
		headerPanelContainer.setMaximumSize(new Dimension(500,80));
		JPanel settingsPanelContainer = new JPanel();
		settingsPanelContainer.setBackground(bgcolor);
		settingsPanelContainer.setMaximumSize(new Dimension(500,500));
		final JPanel buttonsPanelContainer = new JPanel();
		buttonsPanelContainer.setBackground(bgcolor);
		buttonsPanelContainer.setMaximumSize(new Dimension(500,80));
		
		/* HEADER */
		JPanel header = createHeader();
		headerPanelContainer.add(header);
		
		/* SETTINGS */
		
		///////////////////////////////////////////////////////////size
		final SRadioButton small = new SRadioButton("small", SRadioButton.SETTINGS_RADIO);
		final SRadioButton medium = new SRadioButton("medium", SRadioButton.SETTINGS_RADIO);
		final SRadioButton large = new SRadioButton("large", SRadioButton.SETTINGS_RADIO);
		
		small.setHorizontalAlignment(JRadioButton.CENTER);
		small.setBackground(bgcolor);
		medium.setHorizontalAlignment(JRadioButton.CENTER);
		medium.setBackground(bgcolor);
		large.setHorizontalAlignment(JRadioButton.CENTER);
		large.setBackground(bgcolor);
		
		ButtonGroup bG = new ButtonGroup();
		bG.add(small);
		bG.add(medium);
		bG.add(large);
		
		JPanel sizePanel = new JPanel();
		sizePanel.setPreferredSize(new Dimension(500,80));
		sizePanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		sizePanel.setBackground(bgcolor);
		
		SLabel sizeLabel = new SLabel("board size", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);
		
		JPanel sizeOptions = new JPanel();
		sizeOptions.setBackground(bgcolor);
		sizeOptions.setLayout(new GridLayout(1,3));
		sizeOptions.add(small);
		sizeOptions.add(medium);
		sizeOptions.add(large);

		sizePanel.setLayout(new BorderLayout());
		sizePanel.add(sizeLabel, BorderLayout.NORTH);
		sizePanel.add(sizeOptions, BorderLayout.CENTER);

		if(boardSizeObject.isSmall()){
			small.setSelected(true);
		}
		else if(boardSizeObject.isLarge()) {
			large.setSelected(true);
		}
		else{
			medium.setSelected(true);
		}
		
		settingsPanelContainer.add(sizePanel);

		// //////////////////////////////////////////////////LEVEL PANEL
		JPanel levelPanel = new JPanel();
		levelPanel.setPreferredSize(new Dimension(500,100));
		levelPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		levelPanel.setBackground(bgcolor);
		
		SLabel levelLabel = new SLabel("difficulty", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);

		final SRadioButton lvl1 = new SRadioButton("grandma", SRadioButton.SETTINGS_RADIO);
		final SRadioButton lvl2 = new SRadioButton("rookie", SRadioButton.SETTINGS_RADIO);
		final SRadioButton lvl3 = new SRadioButton("normalna", SRadioButton.SETTINGS_RADIO);
		final SRadioButton lvl4 = new SRadioButton("rampage", SRadioButton.SETTINGS_RADIO);
		final SRadioButton lvl5 = new SRadioButton("god mod", SRadioButton.SETTINGS_RADIO);
		
		lvl1.setHorizontalAlignment(JRadioButton.CENTER);
		lvl2.setHorizontalAlignment(JRadioButton.CENTER);
		lvl3.setHorizontalAlignment(JRadioButton.CENTER);
		lvl4.setHorizontalAlignment(JRadioButton.CENTER);
		lvl5.setHorizontalAlignment(JRadioButton.CENTER);
		lvl1.setBackground(bgcolor);
		lvl2.setBackground(bgcolor);
		lvl3.setBackground(bgcolor);
		lvl4.setBackground(bgcolor);
		lvl5.setBackground(bgcolor);

		ButtonGroup lG = new ButtonGroup();
		lG.add(lvl1);
		lG.add(lvl2);
		lG.add(lvl3);
		lG.add(lvl4);
		lG.add(lvl5);

		JPanel levelOptions = new JPanel();
		levelOptions.setBackground(bgcolor);
		levelOptions.setLayout(new GridLayout(1,5));
		levelOptions.add(lvl1);
		levelOptions.add(lvl2);
		levelOptions.add(lvl3);
		levelOptions.add(lvl4);
		levelOptions.add(lvl5);
		
		levelPanel.setLayout(new BorderLayout());
		levelPanel.add(levelLabel, BorderLayout.NORTH);
		levelPanel.add(levelOptions, BorderLayout.CENTER);

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
		settingsPanelContainer.add(levelPanel);

		// /////////////////////////////////////////////////piece paneli
		JPanel piecePanel = new JPanel();
		piecePanel.setPreferredSize(new Dimension(500,100));
		piecePanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		piecePanel.setBackground(bgcolor);
		
		
		SLabel pieceLabel = new SLabel("piece type", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);
		final SCheckBox tetra = new SCheckBox("Tetriminos", SRadioButton.SETTINGS_RADIO);
		final SCheckBox tri = new SCheckBox("Triminos", SRadioButton.SETTINGS_RADIO);
		tetra.setHorizontalAlignment(JCheckBox.CENTER);
		tri.setHorizontalAlignment(JCheckBox.CENTER);
		tetra.setBackground(bgcolor);
		tri.setBackground(bgcolor);
		
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
		

		JPanel pieceOptions = new JPanel();
		pieceOptions.setLayout(new GridLayout(1,2));
		pieceOptions.add(tetra);
		pieceOptions.add(tri);

		piecePanel.setLayout(new BorderLayout());
		piecePanel.add(pieceLabel, BorderLayout.NORTH);
		piecePanel.add(pieceOptions, BorderLayout.CENTER);
		
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

		settingsPanelContainer.add(piecePanel);

		// /////////////////////////////////////////keyConfig
		JPanel keyPanel = new JPanel();
		keyPanel.setPreferredSize(new Dimension(500,200));
		keyPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		keyPanel.setBackground(bgcolor);


		SLabel keyLabel = new SLabel("key configuration", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);
		
		JLabel enterLeft = new SLabel("move left", SLabel.SETTINGS_FIELD_LABEL);
		JLabel enterRight = new SLabel("move right", SLabel.SETTINGS_FIELD_LABEL);
		JLabel enterDown = new SLabel("go down", SLabel.SETTINGS_FIELD_LABEL);
		JLabel enterRotate = new SLabel("rotate", SLabel.SETTINGS_FIELD_LABEL);
		JLabel enterPause = new SLabel("pause/continue", SLabel.SETTINGS_FIELD_LABEL);

		final JTextField leftField = new JTextField(getKeyText(keyConfigureObject.getLeft()));
		final JTextField rightField = new JTextField(getKeyText(keyConfigureObject.getRight()));
		final JTextField downField = new JTextField(getKeyText(keyConfigureObject.getDown()));
		final JTextField rotateField = new JTextField(getKeyText(keyConfigureObject.getRotate()));
		final JTextField pauseField = new JTextField(getKeyText(keyConfigureObject.getPause()));

		JPanel keyOptions = new JPanel();
		keyOptions.setBackground(bgcolor);
		keyOptions.setLayout(new GridLayout(5,2,0,5));
		keyOptions.add(enterLeft);
		keyOptions.add(leftField);
		keyOptions.add(enterRight);
		keyOptions.add(rightField);
		keyOptions.add(enterDown);
		keyOptions.add(downField);
		keyOptions.add(enterRotate);
		keyOptions.add(rotateField);
		keyOptions.add(enterPause);
		keyOptions.add(pauseField);
		
		JPanel keyOptionsSuper = new JPanel();
		keyOptionsSuper.setBackground(bgcolor);
		keyOptionsSuper.setLayout(new BoxLayout(keyOptionsSuper, BoxLayout.Y_AXIS));
		keyOptions.setMaximumSize(new Dimension(400,140));
		keyOptionsSuper.add(keyOptions);
		
		keyPanel.setLayout(new BorderLayout());
		keyPanel.add(keyLabel, BorderLayout.NORTH);
		keyPanel.add(keyOptionsSuper, BorderLayout.CENTER);
	
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

		settingsPanelContainer.add(keyPanel);
		
		// /////////////////////////////////////////////////////////////////finishPanel
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(bgcolor);
		buttonsPanel.setLayout(new GridLayout(1,3,15,0));
		buttonsPanel.setPreferredSize(new Dimension(500,50));
		buttonsPanel.setMaximumSize(new Dimension(500,50));
		
		final SButton save = new SButton("done", SButton.SETTINGS_BUTTON);
		final SButton cancel = new SButton("cancel", SButton.SETTINGS_BUTTON);
		final SButton defaultt = new SButton("default", SButton.SETTINGS_BUTTON);
		
		buttonsPanel.add(cancel);
		buttonsPanel.add(defaultt);
		buttonsPanel.add(save);
		
		buttonsPanelContainer.add(buttonsPanel);
		

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
				
					
				gui.setEnabled(true);
				dispose();
			}
		});

		defaultt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					save.disable();
				} else {
					save.enable();
					keyConfigureObject.setMap(keyMap);

					pieceChoiceObject.setTetriminos(tetra.isSelected());
					pieceChoiceObject.setTriminos(tri.isSelected());
					
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
					settingsObject.saveSettings();
					
					gui.setEnabled(true);
					dispose();
				}
				
			}
		});

		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		add(headerPanelContainer);
		add(settingsPanelContainer);
		add(buttonsPanelContainer);

		this.setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gui.setEnabled(true);
		    }
		});
	}
	
	public JPanel createHeader() {
		JPanel header = new JPanel();
		
		SLabel title = new SLabel("settings", SLabel.MAIN_MENU_TITLE);
		header.add(title);
		title.setVerticalAlignment(JLabel.CENTER);
		
		header.setBackground(bgcolor);
		return header;
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
