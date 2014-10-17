package gui;

import javax.swing.*;

import game.Board;
import game.Engine;

import java.awt.*;
import java.awt.event.*;

public class PlayGUI extends JPanel {
	protected Engine engine;
	private BoardPanel board;
	private NextPieceAndScorePanel nextPiecePanel;
	private GameOverListener gameOverListener;
	private JPanel realPane;
	private JPanel generalPane;
	private Timer timerForCheckingGameOver;
	private int width;
	private int height;

	public PlayGUI(){
		// Title bar
		super();

		gameOverListener = new GameOverListener();

		timerForCheckingGameOver = new Timer(500,gameOverListener);
	}

	public void setEngine(Engine engine){
		this.engine = engine;
		timerForCheckingGameOver.start();

		//Create the toolbar.
		JToolBar toolBar = new JToolBar();

		//Create the animation area used for output.
		board = engine.getBoardPanel();
		nextPiecePanel = engine.getNextPieceAndScorePanel();
		// Put it in a scrollPane, (this makes a border)
		JScrollPane gameBoard = new JScrollPane(board);
		JScrollPane nextPieceAndScorePanel = new JScrollPane(nextPiecePanel);
		realPane = new JPanel();
		realPane.setLayout(new GridLayout(1,2));
		realPane.add(gameBoard);
		realPane.add(nextPieceAndScorePanel);
		//Lay out the content pane.
		generalPane = new JPanel();
		generalPane.setLayout(new BorderLayout());
		generalPane.setPreferredSize(new Dimension(engine.getBoardColumnLength()*2, engine.getBoardRowLength()));
		generalPane.add(realPane, BorderLayout.CENTER);
		add(generalPane);

		width = engine.getBoardColumnLength();
		height = engine.getBoardRowLength();
		
		//showGameOver();
	}

	public void showGameOver(){
		JPanel gameOverPanel = new GameOverPanel(engine.getScore(), engine.getLevelNo());
		removeAll();
		add(gameOverPanel);
		repaint();
	}

	class GameOverListener implements ActionListener{

		public GameOverListener(){ }

		@Override
		public void actionPerformed(ActionEvent e) {
			if (engine.isGameOver()){
				timerForCheckingGameOver.stop();
				showGameOver();
			}
		}
	}

	public class GameOverPanel extends JPanel{
		private JTextField getName;
		private JButton submit;

		public GameOverPanel(double score, int level){
			super();
			setBackground(Color.WHITE);
			setSize(width * 2,height);

			String infoScore = "Score: " + score;
			String infoLevel = "Level: " + level;
			
			setLayout(new GridLayout(4,1));
			
			JLabel gameOver = new JLabel("Game Over!!");
			gameOver.setHorizontalAlignment(JLabel.CENTER);
			gameOver.setFont(new Font(gameOver.getFont().getFamily(), gameOver.getFont().getStyle(), 50));
			add(gameOver);
			
			JPanel getNameForHighScoreTable = new JPanel();
			getNameForHighScoreTable.setLayout(new GridLayout(1,3));
			getNameForHighScoreTable.setBackground(Color.WHITE);
			JLabel name = new JLabel("Enter your name: ");
			getNameForHighScoreTable.add(name);
			getName = new JTextField();
			getNameForHighScoreTable.add(getName);
			submit = new JButton("Submit"); 
			getNameForHighScoreTable.add(submit);
			add(getNameForHighScoreTable);
			
			if (engine.isScoreHighEnough(score)){
				getNameForHighScoreTable.setVisible(true);
			} else {
				getNameForHighScoreTable.setVisible(false);
			}
			
			JPanel info = new JPanel();
			info.setLayout(new GridLayout(2,1));
			info.setBackground(Color.WHITE);
			JLabel infoScoreLabel = new JLabel(infoScore);
			infoScoreLabel.setHorizontalAlignment(JLabel.CENTER);
			infoScoreLabel.setFont(new Font(infoScoreLabel.getFont().getFamily(), infoScoreLabel.getFont().getStyle(), 40));
			info.add(infoScoreLabel);
			JLabel infoLevelLabel = new JLabel(infoLevel);
			infoLevelLabel.setHorizontalAlignment(JLabel.CENTER);
			infoLevelLabel.setFont(new Font(infoLevelLabel.getFont().getFamily(), infoLevelLabel.getFont().getStyle(), 30));
			info.add(infoLevelLabel);
			add(info);
			
			add(addButtons());
		}

		protected JPanel addButtons() {

			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(3,1));
			buttons.setBackground(Color.WHITE);

			JButton button = new JButton("Restart Game");
			button.setToolTipText("Restart the Tetris/Trisis game");
			// when this button is pushed it calls animationWindow.setMode(true)
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//
				}
			});
			buttons.add(button);

			JButton button2 = new JButton("Return To Main Menu");
			button2.setToolTipText("Back to the main menu");
			// when this button is pushed it calls animationWindow.setMode(false)
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//
				}
			});
			buttons.add(button2);

			JButton button3 = new JButton("Quit");
			button3.setToolTipText("Quit the program");
			button3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			buttons.add(button3);
			
			return buttons;
			
		}
	}
}
