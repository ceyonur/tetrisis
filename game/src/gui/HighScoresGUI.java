package gui;


import java.awt.*;
import java.awt.event.*;
import java.text.*;
import highscores.*;
import java.awt.GridLayout;
import javax.swing.*;


/**
 * 
 * @author ogunoz atilberk
 * 
 */
public class HighScoresGUI extends JFrame {

	/**
	 * Fields
	 */
	private GUI gui;
	private HighScores highScoresObject;
	private DateFormat dateFormat;
	private JLabel[][] labels;
	private Color bgcolor = SColor.backgroundColor;;

	/**
	 * Constructor
	 * 
	 * @param GUI
	 *            , HighScores
	 * 
	 */
	public HighScoresGUI(GUI ui, HighScores highscores) {
		super();
		gui = ui;
		setSize(570, 690);
		setBackground(bgcolor);

		highScoresObject = highscores;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy H:m");

		labels = new JLabel[5][3];

		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		add(createHeader());
		add(createScorePanel());
		add(createHighScoreButtons());

		pack();

		this.setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gui.setEnabled(true);
			}
		});
	}

	/**
	 * Creates container for header
	 * 
	 * @return header panel
	 */
	private JPanel createHeader() {
		JPanel headerPanelContainer = new JPanel();
		headerPanelContainer.setBackground(bgcolor);
		headerPanelContainer.setMaximumSize(new Dimension(500, 100));
		JPanel header = new JPanel();

		SLabel title = new SLabel("high scores", SLabel.MAIN_MENU_TITLE);
		header.add(title);
		title.setVerticalAlignment(JLabel.CENTER);

		header.setBackground(bgcolor);
		headerPanelContainer.add(header);
		return headerPanelContainer;
	}

	/**
	 * Creates container for centerPanel
	 * 
	 * @return center panel
	 */
	private JPanel createCenterPanel() {
		GridLayout grd = new GridLayout(6, 3);

		JPanel centerPanel = new JPanel(grd);
		centerPanel.setBackground(bgcolor);

		SLabel playersLabel = new SLabel("player name",
				SLabel.HIGHSCORES_HEADER_LABEL, SwingConstants.CENTER);
		SLabel scoresLabel = new SLabel("score",
				SLabel.HIGHSCORES_HEADER_LABEL, SwingConstants.CENTER);
		SLabel datesLabel = new SLabel("date", SLabel.HIGHSCORES_HEADER_LABEL,
				SwingConstants.CENTER);

		datesLabel.setLocation(400, 10);
		datesLabel.setSize(90, 30);

		centerPanel.add(playersLabel);
		centerPanel.add(scoresLabel);
		centerPanel.add(datesLabel);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				labels[i][j] = new SLabel(" - ",
						SLabel.HIGHSCORES_CONTENT_LABEL, SwingConstants.CENTER);
			}
		}

		if (highScoresObject.getPlayerListSize() > 0
				&& highScoresObject.getPlayerListSize() < 6)
			fillFields(highScoresObject.getPlayerListSize());

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				centerPanel.add(labels[i][j]);
			}
		}

		return centerPanel;
	}

	/**
	 * Creates container for scorePanel
	 * 
	 * @return score panel container
	 */
	private JPanel createScorePanel() {
		JPanel scoresPanelContainer = new JPanel();
		scoresPanelContainer.setBackground(bgcolor);
		scoresPanelContainer.setMaximumSize(new Dimension(500, 180));

		scoresPanelContainer.add(createWestPanel(), BorderLayout.WEST);
		scoresPanelContainer.add(createCenterPanel());

		return scoresPanelContainer;
	}

	/**
	 * Creates container for westPanel
	 * 
	 * @return west panel
	 */
	private JPanel createWestPanel() {
		GridLayout grd2 = new GridLayout(6, 1);
		JPanel westPanel = new JPanel(grd2);
		westPanel.setBackground(bgcolor);

		SLabel zero = new SLabel();
		SLabel first = new SLabel("1.", SLabel.HIGHSCORES_NUMBER_LABEL,
				SwingConstants.CENTER);
		SLabel second = new SLabel("2.", SLabel.HIGHSCORES_NUMBER_LABEL,
				SwingConstants.CENTER);
		SLabel third = new SLabel("3.", SLabel.HIGHSCORES_NUMBER_LABEL,
				SwingConstants.CENTER);
		SLabel fourth = new SLabel("4.", SLabel.HIGHSCORES_NUMBER_LABEL,
				SwingConstants.CENTER);
		SLabel fifth = new SLabel("5.", SLabel.HIGHSCORES_NUMBER_LABEL,
				SwingConstants.CENTER);

		westPanel.add(zero);
		westPanel.add(first);
		westPanel.add(second);
		westPanel.add(third);
		westPanel.add(fourth);
		westPanel.add(fifth);

		return westPanel;
	}

	/**
	 * Creates container for highScore buttons
	 * 
	 * @return buttons panel
	 */
	private JPanel createHighScoreButtons() {
		final JPanel buttonsPanelContainer = new JPanel();
		buttonsPanelContainer.setBackground(bgcolor);
		buttonsPanelContainer.setMaximumSize(new Dimension(500, 80));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(bgcolor);
		buttonsPanel.setLayout(new GridLayout(1, 3, 15, 0));
		buttonsPanel.setPreferredSize(new Dimension(300, 50));
		buttonsPanel.setMaximumSize(new Dimension(300, 50));

		final SButton closeButton = new SButton("close",
				SButton.SETTINGS_BUTTON);
		final SButton resetButton = new SButton("reset",
				SButton.SETTINGS_BUTTON);

		buttonsPanel.add(closeButton);
		buttonsPanel.add(resetButton);

		buttonsPanelContainer.add(buttonsPanel);

		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.setEnabled(true);
				dispose();
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				highScoresObject.resetHighScores();
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 3; j++) {
						labels[i][j].setText(" - ");
					}
				}
			}
		});

		return buttonsPanelContainer;
	}

	/**
	 * Fill score, name, and date fields
	 * 
	 */
	private void fillFields(int a) {
		for (int i = 0; i < a; i++) {
			labels[i][0].setText(highScoresObject.getPlayer(i + 1).getName());
			labels[i][1].setText((Double.toString(highScoresObject.getPlayer(
					i + 1).getScore())));
			labels[i][2].setText(dateFormat.format(highScoresObject.getPlayer(
					i + 1).getDate()));
		}
	}
}
