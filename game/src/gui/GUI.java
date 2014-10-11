package gui;

import game.Engine;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	// overview: An ApplicationWindow is a top level program window that
	// contains a toolbar and an animation window.

	protected Engine engine;
	private Board board;

	public GUI() {
		// effects: Initializes the application window so that it contains
		//          a toolbar and an animation window.

		// Title bar
		super("Tetris/Trisis Game");

		// respond to the window system asking us to quit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void setEngine(Engine engine){
		this.engine = engine;
		
		//Create the toolbar.
		JToolBar toolBar = new JToolBar();
		addButtons(toolBar);

		//Create the animation area used for output.
		this.board = engine.getBoardPanel();
		
		// Put it in a scrollPane, (this makes a border)
		JScrollPane scrollPane = new JScrollPane(board);

		//Lay out the content pane.
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(engine.getBoardColumnLength(), engine.getBoardRowLength()));
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
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
			public void actionPerformed(ActionEvent e) {
				board.setMode(true);
			}
		});
		toolBar.add(button);

		button = new JButton("Stop");
		button.setToolTipText("Stop the animation");
		// when this button is pushed it calls animationWindow.setMode(false)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.setMode(false);
			}
		});
		toolBar.add(button);

		button = new JButton("Quit");
		button.setToolTipText("Quit the program");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(button);
	}
}
