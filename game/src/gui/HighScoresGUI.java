package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
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

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // windowClosing
		});
		GridLayout grd = new GridLayout(6,3);
		GridLayout grd2 = new GridLayout(6,1);
		
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
	
		JLabel sifir = new JLabel();
		JLabel first = new JLabel ("    1.", SwingConstants.CENTER);
		JLabel second = new JLabel("    2.", SwingConstants.CENTER);
		JLabel third = new JLabel ("    3.", SwingConstants.CENTER);
		JLabel forth = new JLabel ("    4.", SwingConstants.CENTER);
		JLabel fifth = new JLabel ("    5.", SwingConstants.CENTER);

		westPanel.add(sifir);
		westPanel.add(first);
		westPanel.add(second);
		westPanel.add(third);
		westPanel.add(forth);
		westPanel.add(fifth);

		JLabel birebir = new JLabel(" - ", SwingConstants.CENTER);
		JLabel bireiki = new JLabel(" - ", SwingConstants.CENTER);
		JLabel bireuc = new JLabel(" - ", SwingConstants.CENTER);
		JLabel ikiyebir = new JLabel(" - ", SwingConstants.CENTER);
		JLabel ikiyeiki = new JLabel(" - ", SwingConstants.CENTER);
		JLabel ikiyeuc = new JLabel(" - ", SwingConstants.CENTER);
		JLabel ucebir = new JLabel(" - ", SwingConstants.CENTER);
		JLabel uceiki = new JLabel(" - ", SwingConstants.CENTER);
		JLabel uceuc = new JLabel(" - ", SwingConstants.CENTER);
		JLabel dordebir = new JLabel(" - ", SwingConstants.CENTER);
		JLabel dordeiki = new JLabel(" - ", SwingConstants.CENTER);
		JLabel dordeuc = new JLabel(" - ", SwingConstants.CENTER);
		JLabel besebir = new JLabel(" - ", SwingConstants.CENTER);
		JLabel beseiki = new JLabel(" - ", SwingConstants.CENTER);
		JLabel beseuc = new JLabel(" - ", SwingConstants.CENTER);

		if (highScoresObject.getPlayerListSize() == 5) {
			birebir.setText(highScoresObject.getPlayer(1).getName());
			bireiki.setText((Double.toString(highScoresObject.getPlayer(1)
					.getScore())));
			bireuc.setText(dateFormat.format(highScoresObject.getPlayer(1).getDate()));

			ikiyebir.setText(highScoresObject.getPlayer(2).getName());
			ikiyeiki.setText((Double.toString(highScoresObject.getPlayer(2)
					.getScore())));
			ikiyeuc.setText(dateFormat.format(highScoresObject.getPlayer(2).getDate()));
			
			ucebir.setText(highScoresObject.getPlayer(3).getName());
			uceiki.setText((Double.toString(highScoresObject.getPlayer(3)
					.getScore())));
			uceuc.setText(dateFormat.format(highScoresObject.getPlayer(3).getDate()));
			
			dordebir.setText(highScoresObject.getPlayer(4).getName());
			dordeiki.setText((Double.toString(highScoresObject.getPlayer(4)
					.getScore())));
			dordeuc.setText(dateFormat.format(highScoresObject.getPlayer(4).getDate()));
			
			besebir.setText(highScoresObject.getPlayer(5).getName());
			beseiki.setText((Double.toString(highScoresObject.getPlayer(5)
					.getScore())));
			beseuc.setText(dateFormat.format(highScoresObject.getPlayer(5).getDate()));
		} else if (highScoresObject.getPlayerListSize() == 4) {
			birebir.setText(highScoresObject.getPlayer(1).getName());
			bireiki.setText((Double.toString(highScoresObject.getPlayer(1)
					.getScore())));
			bireuc.setText(dateFormat.format(highScoresObject.getPlayer(1).getDate()));

			ikiyebir.setText(highScoresObject.getPlayer(2).getName());
			ikiyeiki.setText((Double.toString(highScoresObject.getPlayer(2)
					.getScore())));
			ikiyeuc.setText(dateFormat.format(highScoresObject.getPlayer(2).getDate()));
			
			ucebir.setText(highScoresObject.getPlayer(3).getName());
			uceiki.setText((Double.toString(highScoresObject.getPlayer(3)
					.getScore())));
			uceuc.setText(dateFormat.format(highScoresObject.getPlayer(3).getDate()));
			
			dordebir.setText(highScoresObject.getPlayer(4).getName());
			dordeiki.setText((Double.toString(highScoresObject.getPlayer(4)
					.getScore())));
			dordeuc.setText(dateFormat.format(highScoresObject.getPlayer(4).getDate()));
		} else if (highScoresObject.getPlayerListSize() == 3) {
			birebir.setText(highScoresObject.getPlayer(1).getName());
			bireiki.setText((Double.toString(highScoresObject.getPlayer(1)
					.getScore())));
			bireuc.setText(dateFormat.format(highScoresObject.getPlayer(1).getDate()));

			ikiyebir.setText(highScoresObject.getPlayer(2).getName());
			ikiyeiki.setText((Double.toString(highScoresObject.getPlayer(2)
					.getScore())));
			ikiyeuc.setText(dateFormat.format(highScoresObject.getPlayer(2).getDate()));
			
			ucebir.setText(highScoresObject.getPlayer(3).getName());
			uceiki.setText((Double.toString(highScoresObject.getPlayer(3)
					.getScore())));
			uceuc.setText(dateFormat.format(highScoresObject.getPlayer(3).getDate()));
		} else if (highScoresObject.getPlayerListSize() == 2) {
			birebir.setText(highScoresObject.getPlayer(1).getName());
			bireiki.setText((Double.toString(highScoresObject.getPlayer(1)
					.getScore())));
			bireuc.setText(dateFormat.format(highScoresObject.getPlayer(1).getDate()));

			ikiyebir.setText(highScoresObject.getPlayer(2).getName());
			ikiyeiki.setText((Double.toString(highScoresObject.getPlayer(2)
					.getScore())));
			ikiyeuc.setText(dateFormat.format(highScoresObject.getPlayer(2).getDate()));
		} else if (highScoresObject.getPlayerListSize() == 1) {
			
			birebir.setText(highScoresObject.getPlayer(1).getName());
			bireiki.setText((Double.toString(highScoresObject.getPlayer(1)
					.getScore())));
			bireuc.setText(dateFormat.format(highScoresObject.getPlayer(1).getDate()));
		}

		centerPanel.add(birebir);
		centerPanel.add(bireiki);
		centerPanel.add(bireuc);
		centerPanel.add(ikiyebir);
		centerPanel.add(ikiyeiki);
		centerPanel.add(ikiyeuc);
		centerPanel.add(ucebir);
		centerPanel.add(uceiki);
		centerPanel.add(uceuc);
		centerPanel.add(dordebir);
		centerPanel.add(dordeiki);
		centerPanel.add(dordeuc);
		centerPanel.add(besebir);
		centerPanel.add(beseiki);
		centerPanel.add(beseuc);
		
		
		this.add(westPanel, BorderLayout.WEST);
		this.add(centerPanel);
		

	}

	public static void main(String[] args) {
	
		HighScores asd = new HighScores();

		
		JFrame f = new HighScoresGUI(asd);
		


		
		f.show();
		
		
	}

}
