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
	private int width;
	private Timer timerForCheckingGameOver;
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
	}
	
	public void showGameOver(){
		JPanel gameOverPanel = new GameOverPanel(engine.getScore(), engine.getLevelNo());
		this.remove(generalPane);
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
		private String infoScore;
		private String infoLevel;
		
		public GameOverPanel(double score, int level){
			super();
			setBackground(Color.WHITE);
			setSize(width * 2,height);
			
			infoScore = "Score: " + score;
			infoLevel = "Level: " + level;
		}
		
		public void paint(Graphics g){
			super.paint(g);
			g.setFont(new Font(g.getFont().getFamily(), g.getFont().getStyle(), 50));
			g.drawString("Game Over!!",(width - 50)/2, height/4);
			g.setFont(new Font(g.getFont().getFamily(), g.getFont().getStyle(), 40));
			g.drawString(infoScore ,(width + 50)/2, height/4 + 50);
			g.drawString(infoLevel ,(width + 100)/2, height/4 + 100);
		}
		
		protected void addButtons(JToolBar toolBar) {

			JButton button = null;

			button = new JButton("Restart Game");
			button.setToolTipText("Restart the Tetris/Trisis game");
			// when this button is pushed it calls animationWindow.setMode(true)
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					board.setMode(true);
				}
			});
			toolBar.add(button);

			button = new JButton("Return To Main Menu");
			button.setToolTipText("Back to the main menu");
			// when this button is pushed it calls animationWindow.setMode(false)
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					board.setMode(false);
				}
			});
			toolBar.add(button);

			button = new JButton("Quit");
			button.setToolTipText("Quit the program");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			toolBar.add(button);
		}
	}
}
