package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import highscores.*;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import settings.Settings;

public class HighScoresGUI extends JFrame {
	HighScores highScoresObject;
	DateFormat dateFormat;

	public HighScoresGUI(HighScores highscores) {
		super();
		setTitle("HighScores");
		setSize(490, 250);
		setLocationRelativeTo(null);
		highScoresObject = highscores;
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		GridLayout grd = new GridLayout(6, 3);
		GridLayout grd2 = new GridLayout(6, 1);

		JPanel centerPanel = new JPanel(grd);
		JPanel westPanel = new JPanel(grd2);

		JLabel playersLabel = new JLabel("Player Name", SwingConstants.CENTER);
		JLabel scoresLabel = new JLabel("Score", SwingConstants.CENTER);

		JLabel datesLabel = new JLabel("Date", SwingConstants.CENTER);

		datesLabel.setLocation(400, 10);
		datesLabel.setSize(90, 30);

		centerPanel.add(playersLabel);
		centerPanel.add(scoresLabel);
		centerPanel.add(datesLabel);

		JLabel zero = new JLabel();
		JLabel first = new JLabel("    1.", SwingConstants.CENTER);
		JLabel second = new JLabel("    2.", SwingConstants.CENTER);
		JLabel third = new JLabel("    3.", SwingConstants.CENTER);
		JLabel forth = new JLabel("    4.", SwingConstants.CENTER);
		JLabel fifth = new JLabel("    5.", SwingConstants.CENTER);

		westPanel.add(zero);
		westPanel.add(first);
		westPanel.add(second);
		westPanel.add(third);
		westPanel.add(forth);
		westPanel.add(fifth);

		JLabel[][] labels = new JLabel[5][3];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				labels[i][j] = new JLabel(" - ", SwingConstants.CENTER);
			}
		}
		switch (highScoresObject.getPlayerListSize()) {
		case 1:
			labels[0][0].setText(highScoresObject.getPlayer(1).getName());
			labels[0][1].setText((Double.toString(highScoresObject
					.getPlayer(1).getScore())));
			labels[0][2].setText(dateFormat.format(highScoresObject
					.getPlayer(1).getDate()));
			break;
		case 2:

			for (int i = 0; i < 2; i++){
				labels[i][0].setText(highScoresObject.getPlayer(i+1).getName());
				labels[i][1].setText((Integer.toString((int) highScoresObject
						.getPlayer(i+1).getScore())));
				labels[i][2].setText(dateFormat.format(highScoresObject
						.getPlayer(i+1).getDate()));
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++){
				labels[i][0].setText(highScoresObject.getPlayer(i+1).getName());
				labels[i][1].setText((Integer.toString((int) highScoresObject
						.getPlayer(i+1).getScore())));
				labels[i][2].setText(dateFormat.format(highScoresObject
						.getPlayer(i+1).getDate()));
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++){
				labels[i][0].setText(highScoresObject.getPlayer(i+1).getName());
				labels[i][1].setText((Integer.toString( (int) highScoresObject
						.getPlayer(i+1).getScore())));
				labels[i][2].setText(dateFormat.format(highScoresObject
						.getPlayer(i+1).getDate()));
			}
			break;
		case 5:
			for (int i = 0; i < 5; i++){
				labels[i][0].setText(highScoresObject.getPlayer(i+1).getName());
				labels[i][1].setText((Integer.toString((int) highScoresObject
						.getPlayer(i+1).getScore())));
				labels[i][2].setText(dateFormat.format(highScoresObject
						.getPlayer(i+1).getDate()));
			}

			break;
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				centerPanel.add(labels[i][j]);
			}
		}

		this.add(westPanel, BorderLayout.WEST);
		this.add(centerPanel);

	}

}
