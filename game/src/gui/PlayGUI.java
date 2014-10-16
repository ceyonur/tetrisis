package gui;

import javax.swing.*;

import game.Engine;

import java.awt.*;
import java.awt.event.*;

public class PlayGUI extends JFrame {
	protected Engine engine;
	private BoardPanel board;
	private NextPieceAndScorePanel nextPiecePanel;
	private JLabel gameOver;
	
	public PlayGUI(){
		// Title bar
		super("Tetris/Trisis Game");
		// respond to the window system asking us to quit
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setResizable(false);
	}
	
	public void setEngine(Engine engine){
		this.engine = engine;
		
		//Create the toolbar.
		JToolBar toolBar = new JToolBar();
		addButtons(toolBar);

		//Create the animation area used for output.
		board = engine.getBoardPanel();
		nextPiecePanel = engine.getNextPieceAndScorePanel();
		
		// Put it in a scrollPane, (this makes a border)
		JScrollPane gameBoard = new JScrollPane(board);
		JScrollPane nextPieceAndScorePanel = new JScrollPane(nextPiecePanel);
		JPanel realPane = new JPanel();
		realPane.setLayout(new GridLayout(1,2));
		realPane.add(gameBoard);
		realPane.add(nextPieceAndScorePanel);
		//Lay out the content pane.
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(engine.getBoardColumnLength()*2, engine.getBoardRowLength()));
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(realPane, BorderLayout.CENTER);
		setContentPane(contentPane);
	}
	
	protected void addButtons(JToolBar toolBar) {
		// modifies: toolBar
		// effects: adds Run, Stop and Quit buttons to toolBar

		JButton button = null;

		button = new JButton("Run");
		button.setToolTipText("Start the animation");
		// when this button is pushed it calls animationWindow.setMode(true)
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setMode(true);
			}
		});
		toolBar.add(button);

		button = new JButton("Stop");
		button.setToolTipText("Stop the animation");
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
	
	public void showGameOver(){
		gameOver = new JLabel("Game Over!!");
		gameOver.setFont(new Font(gameOver.getFont().getFamily(), gameOver.getFont().getStyle(), 10));
		gameOver.setLocation((this.getWidth() - gameOver.getWidth())/2 , (this.getHeight() - gameOver.getHeight())/2);
		add(gameOver);
		repaint();
	}
}
