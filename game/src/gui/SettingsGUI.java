package gui;


import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import settings.*;

/**
 * Creates GUI for settings
 * @author atilberk
 *
 */
public class SettingsGUI extends JFrame {
	
	/**
	 * Fields
	 */
	GUI gui;
	private BoardSize boardSizeObject;
	private KeyConfigure keyConfigureObject;
	private LevelChoice levelChoiceObject;
	private PieceChoice pieceChoiceObject;
	private Settings settingsObject;
	private HashMap<String, Integer> keyMap;
	private JComponent[][] items;
	private SRadioButton[] levels;
	private SRadioButton small;
	private SRadioButton medium;
	private SRadioButton large;
	private SCheckBox tetra;
	private SCheckBox tri;
	
	/**
	 * Constructor
	 * Creates a GUI for settings
	 * @param gui
	 * @param settings
	 */
	public SettingsGUI(GUI ui, Settings settings) {
		super();
		gui = ui;
		setSize(570, 690);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setBackground(SColor.backgroundColor);
		
		settingsObject= settings;
		boardSizeObject = settingsObject.getBoardSizeChoiceObject();
		keyConfigureObject = settingsObject.getKeyConfigureObject();
		levelChoiceObject = settingsObject.getLevelChoiceObject();
		pieceChoiceObject = settingsObject.getPieceChoiceObject();
		keyMap = keyConfigureObject.getMap();
		
		
		createAndAddContainers();
		
		this.setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gui.setEnabled(true);
		    }
		});
	}
	
	/**
	 * Creates containers for header, buttons and footer sections
	 */
	private void createAndAddContainers() {
		JPanel headerPanelContainer = new JPanel();
		headerPanelContainer.setBackground(SColor.backgroundColor);
		headerPanelContainer.setMaximumSize(new Dimension(500,80));
		JPanel settingsPanelContainer = new JPanel();
		settingsPanelContainer.setBackground(SColor.backgroundColor);
		settingsPanelContainer.setMaximumSize(new Dimension(500,500));
		JPanel buttonsPanelContainer = new JPanel();
		buttonsPanelContainer.setBackground(SColor.backgroundColor);
		buttonsPanelContainer.setMaximumSize(new Dimension(500,80));
		
		/* HEADER */
		JPanel header = createHeader();
		headerPanelContainer.add(header);
		
		/* SETTINGS */
		JPanel sizePanel = createSizesPanel();
		settingsPanelContainer.add(sizePanel);
		
		JPanel levelPanel = createLevelsPanel();
		settingsPanelContainer.add(levelPanel);
		
		JPanel piecesPanel = createPiecesPanel();
		settingsPanelContainer.add(piecesPanel);
		
		JPanel keyPanel = createKeysPanel();
		settingsPanelContainer.add(keyPanel);
		
		/* BUTTONS */
		JPanel buttonsPanel = createButtons();
		buttonsPanelContainer.add(buttonsPanel);
		
		add(headerPanelContainer);
		add(settingsPanelContainer);
		add(buttonsPanelContainer);
	}
	
	/**
	 * Creates board size panel
	 * @return sizes panel
	 */
	private JPanel createSizesPanel() {
		
		JPanel sizePanel = new JPanel();
		sizePanel.setPreferredSize(new Dimension(500,80));
		sizePanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		sizePanel.setBackground(SColor.backgroundColor);
		sizePanel.setLayout(new BorderLayout());
		
		
		small = new SRadioButton("small", SRadioButton.SETTINGS_RADIO);
		medium = new SRadioButton("medium", SRadioButton.SETTINGS_RADIO);
		large = new SRadioButton("large", SRadioButton.SETTINGS_RADIO);
		
		small.setHorizontalAlignment(JRadioButton.CENTER);
		small.setBackground(SColor.backgroundColor);
		medium.setHorizontalAlignment(JRadioButton.CENTER);
		medium.setBackground(SColor.backgroundColor);
		large.setHorizontalAlignment(JRadioButton.CENTER);
		large.setBackground(SColor.backgroundColor);
		
		ButtonGroup bG = new ButtonGroup();
		bG.add(small);
		bG.add(medium);
		bG.add(large);
		
		final SLabel sizeLabel = new SLabel("board size", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);
		
		JPanel sizeOptions = new JPanel();
		sizeOptions.setBackground(SColor.backgroundColor);
		sizeOptions.setLayout(new GridLayout(1,3));
		sizeOptions.add(small);
		sizeOptions.add(medium);
		sizeOptions.add(large);

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
		
		return sizePanel;
	}
	
	/**
	 * Creates level choice panel
	 * @return levels panel
	 */
	private JPanel createLevelsPanel() {
		
		final String levelLabels[] = levelChoiceObject.levelLabels;
		final int numLevels = levelChoiceObject.numLevels;
		
		JPanel levelPanel = new JPanel();
		levelPanel.setPreferredSize(new Dimension(500,100));
		levelPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		levelPanel.setBackground(SColor.backgroundColor);
		levelPanel.setLayout(new BorderLayout());
		
		SLabel levelLabel = new SLabel("difficulty", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);

		JPanel levelOptions = new JPanel();
		levelOptions.setBackground(SColor.backgroundColor);
		levelOptions.setLayout(new GridLayout(1,numLevels));
		
		levels = new SRadioButton[numLevels];
		ButtonGroup lG = new ButtonGroup();
		
		for(int l = 0; l < numLevels; l++) {
			final SRadioButton lvl = new SRadioButton(levelLabels[l],  SRadioButton.SETTINGS_RADIO);
			lG.add(lvl);
			lvl.setHorizontalAlignment(JRadioButton.CENTER);
			lvl.setBackground(SColor.backgroundColor);
			levelOptions.add(lvl);
			levels[l] = lvl;
		}

		int chosenLevel = levelChoiceObject.getLevel();
		levels[chosenLevel-1].setSelected(true);
		
		levelPanel.add(levelLabel, BorderLayout.NORTH);
		levelPanel.add(levelOptions, BorderLayout.CENTER);

		return levelPanel;
	}
	
	/**
	 * Creates piece choice panel
	 * @return pieces panel
	 */
	private JPanel createPiecesPanel() {
		JPanel piecesPanel = new JPanel();
		piecesPanel.setPreferredSize(new Dimension(500,100));
		piecesPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		piecesPanel.setBackground(SColor.backgroundColor);
		piecesPanel.setLayout(new BorderLayout());
		
		
		final SLabel pieceLabel = new SLabel("piece type", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);
		
		tetra = new SCheckBox("Tetriminos", SRadioButton.SETTINGS_RADIO);
		tetra.setHorizontalAlignment(JCheckBox.CENTER);
		tetra.setBackground(SColor.backgroundColor);
		
		tri = new SCheckBox("Triminos", SRadioButton.SETTINGS_RADIO);
		tri.setHorizontalAlignment(JCheckBox.CENTER);
		tri.setBackground(SColor.backgroundColor);
		
		tetra.setSelected(pieceChoiceObject.hasTetriminos());
		tri.setSelected(pieceChoiceObject.hasTriminos());
		
		JPanel pieceOptions = new JPanel();
		pieceOptions.setLayout(new GridLayout(1,2));
		pieceOptions.setBackground(SColor.backgroundColor);
		
		pieceOptions.add(tetra);
		pieceOptions.add(tri);

		piecesPanel.add(pieceLabel, BorderLayout.NORTH);
		piecesPanel.add(pieceOptions, BorderLayout.CENTER);
				
		// add listeners to checkboxes to prevent to get none as chosen
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
		
		return piecesPanel;
	}

	/**
	 * Creates key configuration panel
	 * @return keys panel
	 */
	private JPanel createKeysPanel() {

		final String[][] keyStrings = keyConfigureObject.getKeyStrings();

		final int numKeys = keyStrings.length;

		JPanel keysPanel = new JPanel();
		keysPanel.setPreferredSize(new Dimension(500,200));
		keysPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		keysPanel.setBackground(SColor.backgroundColor);
		keysPanel.setLayout(new BorderLayout());

		SLabel keyLabel = new SLabel("key configuration", SLabel.SETTINGS_LABEL, SwingConstants.CENTER);

		JPanel keyOptions = new JPanel();
		keyOptions.setBackground(SColor.backgroundColor);
		keyOptions.setLayout(new GridLayout(numKeys,2,0,5));
		
		items = new JComponent[numKeys][2];
		final HashMap<JTextField, Integer> fieldmap = new HashMap<JTextField, Integer>();
		for(int i = 0; i < numKeys; i++) {
			final SLabel label = new SLabel(keyStrings[i][0], SLabel.SETTINGS_FIELD_LABEL);
			final JTextField field = new JTextField(keyStrings[i][1]);
			keyOptions.add(label);
			keyOptions.add(field);
			items[i][0] = label;
			items[i][1] = field;
			fieldmap.put(field, i);

			field.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent e) {
					int which = fieldmap.get((JTextField) e.getSource());
					
					field.setText(null);				
					if (keyMap.containsValue(e.getKeyCode())) {
						for (String key : keyMap.keySet()) {
							if (keyMap.get(key) == e.getKeyCode() && key != keyStrings[which][2] ) {
								keyMap.put(key,0);
							}
						}
					}
					keyMap.put(keyStrings[which][2], e.getKeyCode());
					
					for (int j = 0; j < numKeys; j++) {
						((JTextField) items[j][1]).setText(getKeyText(keyMap.get(keyStrings[j][2])));
					}					
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}

				@Override
				public void keyTyped(KeyEvent e) {
					e.consume();
				}
			});

		}

		JPanel keyOptionsSuper = new JPanel();
		keyOptionsSuper.setBackground(SColor.backgroundColor);
		keyOptionsSuper.setLayout(new BoxLayout(keyOptionsSuper, BoxLayout.Y_AXIS));
		keyOptions.setMaximumSize(new Dimension(400,140));
		keyOptionsSuper.add(keyOptions);

		keysPanel.add(keyLabel, BorderLayout.NORTH);
		keysPanel.add(keyOptionsSuper, BorderLayout.CENTER);

		return keysPanel;
	}
	
	/**
	 * Creates buttons panel
	 * @return buttons
	 */
	private JPanel createButtons() {
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(SColor.backgroundColor);
		buttonsPanel.setLayout(new GridLayout(1,3,15,0));
		buttonsPanel.setPreferredSize(new Dimension(500,50));
		buttonsPanel.setMaximumSize(new Dimension(500,50));

		final SButton save = new SButton("done", SButton.SETTINGS_BUTTON);
		final SButton cancel = new SButton("cancel", SButton.SETTINGS_BUTTON);
		final SButton defaultt = new SButton("default", SButton.SETTINGS_BUTTON);

		buttonsPanel.add(cancel);
		buttonsPanel.add(defaultt);
		buttonsPanel.add(save);

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				gui.setEnabled(true);
				dispose();
			}
		});

		defaultt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyConfigure keyconf = new KeyConfigure();
				keyMap = keyconf.getMap();
				for (int i = 0; i < 5; i++) {
					((JTextField)items[i][1]).setText(keyconf.getKeyStrings()[i][1]);
				}
				medium.setSelected(true);
				levels[0].setSelected(true);
				tetra.setSelected(true);
				tri.setSelected(false);				
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (keyMap.containsValue(0)) {
					save.setEnabled(true);					
				} else {
					save.setEnabled(false);
					keyConfigureObject.setMap(keyMap);

					pieceChoiceObject.setTetriminos(tetra.isSelected());
					pieceChoiceObject.setTriminos(tri.isSelected());

					for (int i = 0; i < levelChoiceObject.numLevels; i++) {
						if (levels[i].isSelected())
							levelChoiceObject.setLevel(i+1);
					}

					if(small.isSelected())
						boardSizeObject.setSmall();
					else if (large.isSelected())
						boardSizeObject.setLarge();
					else
						boardSizeObject.setMedium();					
					settingsObject.saveSettings();

					gui.setEnabled(true);
					dispose();
				}

			}
		});
		
		return buttonsPanel;
	}
	
	/**
	 * Creates header panel
	 * @return header
	 */
	private JPanel createHeader() {
		JPanel header = new JPanel();
		
		SLabel title = new SLabel("settings", SLabel.MAIN_MENU_TITLE);
		header.add(title);
		title.setVerticalAlignment(JLabel.CENTER);
		
		header.setBackground(SColor.backgroundColor);
		return header;
	}
	
	/**
	 * Returs the String representation of given key value
	 * @param key value
	 * @return string representation
	 */
	public static String getKeyText(int a){
		if(a != 0){
			return KeyEvent.getKeyText(a);
		}
		else {
			return null;
		}
	}


}
