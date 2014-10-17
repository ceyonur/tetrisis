package gui;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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
	private BoardSize boardSizeObject;
	private KeyConfigure keyConfigureObject;
	private LevelChoice levelChoiceObject;
	private PieceChoice pieceChoiceObject;
	private Settings settingsObject;
	private HashMap<String, Integer> keyMap;	
	
	public SettingsGUI(Settings settings) {
		super();
		setTitle("Settings");
		setSize(450, 690);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		
		setLocation(250, 150);
		
		settingsObject= settings;
		boardSizeObject = settingsObject.getBoardSizeChoice();
		keyConfigureObject = settingsObject.getKeyConfigure();
		levelChoiceObject = settingsObject.getLevelChoice();
		pieceChoiceObject = settingsObject.getPieceChoice();
		keyMap = keyConfigureObject.getMap();

		final JRadioButton small = new JRadioButton("Small");
		final JRadioButton medium = new JRadioButton("Medium");
		final JRadioButton large = new JRadioButton("Large");

		ButtonGroup bG = new ButtonGroup();
		bG.add(small);
		bG.add(medium);
		bG.add(large);

		GridLayout grd = new GridLayout(2,3);
		GridLayout mainGrd = new GridLayout(5,1);
		JPanel sizePanel = new JPanel(grd);
		
		JPanel mainPanel = new JPanel(mainGrd);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JLabel sizeLabel = new JLabel("Please select the board size",
				SwingConstants.CENTER);
		JLabel a = new JLabel();
		JLabel b = new JLabel();
		sizePanel.add(a);
		sizePanel.add(sizeLabel);
	
		sizePanel.add(b);
		
		
		sizePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));



		sizePanel.add(small);


		sizePanel.add(medium);


		sizePanel.add(large);


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
		
		
		mainPanel.add(sizePanel, BorderLayout.CENTER);

		// //////////////////////////////////////////////////LEVEL PANEL
		GridLayout grd2 = new GridLayout(2,5);
		JPanel levelPanel = new JPanel(grd2);

		levelPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel levelLabel = new JLabel("Please select dificulty level",
				SwingConstants.CENTER);
		JLabel c = new JLabel();
		JLabel d = new JLabel();
		JLabel e = new JLabel();
		JLabel f = new JLabel();

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


		levelPanel.add(c);
		levelPanel.add(d);
		levelPanel.add(levelLabel);
		levelPanel.add(e);
		levelPanel.add(f);


		levelPanel.add(lvl1);

		levelPanel.add(lvl2);

		levelPanel.add(lvl3);

		levelPanel.add(lvl4);

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
		mainPanel.add(levelPanel);

		// /////////////////////////////////////////////////block paneli
		GridLayout blockGrid = new GridLayout(2,2);
		JPanel blockPanel = new JPanel(blockGrid);
//		blockPanel.setLayout(null);
//		blockPanel.setSize(455, 80);
//		blockPanel.setLocation(10, 210);
		blockPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel blockLabel = new JLabel("                                                          Please select block type",
				SwingConstants.CENTER);
		JLabel h = new JLabel();
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
//		blockLabel.setLocation(130, -10);
//		blockLabel.setSize(220, 60);

//		tetra.setLocation(120, 40);
//		tetra.setSize(140, 20);
		//blockPanel.add(h);
		blockPanel.add(blockLabel);
		blockPanel.add(h);
		blockPanel.add(tetra);

//		tri.setLocation(260, 40);
//		tri.setSize(140, 20);

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

		mainPanel.add(blockPanel);

		// /////////////////////////////////////////keyConfig
		GridLayout keyGrid = new GridLayout(5,2);
		JPanel keyPanel = new JPanel(keyGrid);


		JLabel keyLabel = new JLabel("Key Configuration", SwingConstants.CENTER);
		
		JLabel enterLeft = new JLabel("Move Left");


		final JTextField leftField = new JTextField(getKeyText(keyConfigureObject.getLeft()));
		final JTextField rightField = new JTextField(getKeyText(keyConfigureObject.getRight()));
		final JTextField downField = new JTextField(getKeyText(keyConfigureObject.getDown()));
		final JTextField rotateField = new JTextField(getKeyText(keyConfigureObject.getRotate()));
		final JTextField pauseField = new JTextField(getKeyText(keyConfigureObject.getPause()));
		

		keyPanel.add(enterLeft);
		keyPanel.add(leftField);


		
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
		JLabel enterRight = new JLabel("Move Right");

		keyPanel.add(enterRight);
		keyPanel.add(rightField);



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
		

		JLabel enterRotate = new JLabel("Rotate Blocks");
		keyPanel.add(enterRotate);
		keyPanel.add(rotateField);



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
	

		JLabel enterSpeed = new JLabel("Move Down");
		keyPanel.add(enterSpeed);
		keyPanel.add(downField);


		

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
	

		JLabel enterPause = new JLabel("Pause");
		keyPanel.add(enterPause);
		keyPanel.add(pauseField);


		
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
		finishPanel.setLayout(new BoxLayout(finishPanel, BoxLayout.X_AXIS));

		JButton save = new JButton("Save");

		JButton cancel = new JButton("Cancel");
		JButton defaultt = new JButton("Default");

		finishPanel.add(cancel);
		finishPanel.add(defaultt);
		finishPanel.add(save);

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
				try {
					settingsObject.saveSettings();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
			}
		});

		mainPanel.add(keyPanel);
		mainPanel.add(finishPanel);

		this.add(mainPanel, BorderLayout.CENTER);
		this.setVisible(true);
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
