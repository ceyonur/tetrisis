package gui;

import game.Engine;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import pieces.*;
import settings.KeyConfigure;

public class BoardPanel extends JPanel {
	private AnimationEventListener eventListener;
	private PauseKeyListener pauseListener;
	private ClearLineListener clearLineListener;
	private Timer timer;
	private Timer timerForClearLine1;
	private Timer timerForClearLine2;
	private Timer timerForClearLine3;
	private Timer timerForClearLine4;
	private boolean mode;
	private Piece piece;
	private KeyConfigure keys;
	private Engine callerEngine;
	private ArrayList<Block> blocks;
	private game.Board boardMatrix;
	private NextPieceAndScorePanel nextPiecePanel;
	private boolean newlyInitiated;

	private JLabel paused;
	private JLabel newlyStarted1;
	private JLabel newlyStarted2;
	private JPanel newlyStartedPanel;

	public BoardPanel(KeyConfigure keys, int speed, Engine engine, game.Board boardMatrix, NextPieceAndScorePanel nextPiecePanel) {
		// effects: initializes this to be in the off mode.

		super();                    // do the standard JPanel setup stuff
		setBackground(Color.GRAY);
		this.keys = keys;
		this.boardMatrix = boardMatrix;
		callerEngine = engine;
		blocks = new ArrayList<Block>();

		newlyInitiated = true;

		//putDotIndicators();
		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method
		this.nextPiecePanel = nextPiecePanel;
		eventListener = new AnimationEventListener();
		pauseListener = new PauseKeyListener();
		clearLineListener = new ClearLineListener(boardMatrix.getColumnSize());

		newlyStartedPanel = new JPanel();
		newlyStartedPanel.setLayout(new GridLayout(2,1));
		newlyStartedPanel.setBackground(getBackground());
		add(newlyStartedPanel);
		
		newlyStarted1 = new JLabel("Press pause/continue key");
		newlyStarted1.setFont(new Font(newlyStarted1.getFont().getFamily(), newlyStarted1.getFont().getStyle(), 18));
		newlyStartedPanel.add(newlyStarted1);
		
		newlyStarted2 = new JLabel("to start!");
		newlyStarted2.setFont(new Font(newlyStarted2.getFont().getFamily(), newlyStarted2.getFont().getStyle(), 18));
		newlyStartedPanel.add(newlyStarted2);
		
		paused = new JLabel("Game is paused!");
		paused.setFont(new Font(paused.getFont().getFamily(), paused.getFont().getStyle(), 20));
		add(paused);

		// The first parameter is how often (in milliseconds) the timer
		// should call us back.  50 milliseconds = 20 frames/second
		timer = new Timer(speed, eventListener);
		timerForClearLine1 = new Timer(75,clearLineListener);
		timerForClearLine2 = new Timer(75,clearLineListener);
		timerForClearLine3 = new Timer(75,clearLineListener);
		timerForClearLine4 = new Timer(75,clearLineListener);
		setMode(false);
		
		System.out.println("keyPressed=");
		KeyListener pressingLeft = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
			}
		};
		addKeyListener(pressingLeft);
		setFocusable(true);
		
	}

	// This is just here so that we can accept the keyboard focus
	@Override
	public boolean isFocusTraversable() { return true; }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (mode == true){
			piece.paint(g);
			for (int i=0; i<blocks.size(); i++){
				blocks.get(i).paint(g);
			}
			paused.setVisible(false);
			newlyStarted1.setVisible(false);
			newlyStarted2.setVisible(false);
		} else {
			if (newlyInitiated){
				paused.setVisible(false);
				newlyStartedPanel.setVisible(true);
				newlyStartedPanel.setLocation(newlyStartedPanel.getX(), (getHeight() - newlyStartedPanel.getHeight()) / 2);
			} else {
				newlyStartedPanel.setVisible(false);
				paused.setLocation(paused.getX(), (getHeight() - paused.getHeight()) / 2);
				paused.setVisible(true);
			}
		}
	}

	public void addPiece(Piece piece){
		this.piece = piece;
		blocks.addAll(piece.getBlocks());
	}

	public void setMode(boolean m) {
		// modifies: this
		// effects: changes the mode to <m>.

		if (mode == true) {
			// we're about to change mode: turn off all the old listeners
			removeMouseListener(eventListener);
			removeMouseMotionListener(eventListener);
			removeKeyListener(eventListener);
		}

		mode = m;

		if (mode == true) {
			// the mode is true: turn on the listeners
			addMouseListener(eventListener);
			addMouseMotionListener(eventListener);
			addKeyListener(eventListener);
			removeKeyListener(pauseListener);
			requestFocus();           // make sure keyboard is directed to us
			timer.start();
			newlyInitiated=false;
		} else {
			addKeyListener(pauseListener);
			timer.stop();
		}
		repaint();
		nextPiecePanel.setVisibility(mode);
	}

	public boolean getMode(){
		return mode;
	}

	public void restartTimer(){
		timer.restart();
	}

	public void clearEliminatedLine(int lineNo){
		if (timerForClearLine1.isRunning()){
			if (timerForClearLine2.isRunning()){
				if (timerForClearLine3.isRunning())
					timerForClearLine4.start();
				else
					timerForClearLine3.start();
			}else
				timerForClearLine2.start();
		}else
			timerForClearLine1.start();

		clearLineListener.addDeletedLineNo(lineNo);
	}

	private void repaintPanel(Rectangle oldPos){
		Rectangle repaintArea = oldPos.union(piece.boundingBox());
		repaint(repaintArea.x,
				repaintArea.y,
				repaintArea.width,
				repaintArea.height);
	}

	/*private void putDotIndicators(){
		this
	}*/

	class AnimationEventListener extends MouseAdapter
	implements MouseMotionListener, KeyListener, ActionListener
	{
		// overview: AnimationEventListener is an inner class that
		// responds to all sorts of external events, and provides the
		// required semantic operations for our particular program.  It
		// owns, and sends semantic actions to the ball and window of the
		// outer class


		public AnimationEventListener(){
			super();
		}
		// MouseAdapter gives us empty methods for the MouseListener
		// interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
		// and mouseReleased.

		@Override
		public void mouseClicked(MouseEvent e) { }
		@Override
		public void mouseDragged(MouseEvent e) { }
		@Override
		public void mouseMoved(MouseEvent e) { }

		// Here's the KeyListener interface
		@Override
		public void keyPressed(KeyEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction but
			//          only if one of the keys A-J is pressed.
			int keynum = e.getKeyCode();

			Rectangle oldPos = piece.boundingBox();

			if (keynum == keys.getLeft()){
				if (boardMatrix.checkCollisionsToGoLeft(piece.getLocationOnMatrix()))
					piece.moveABlockLeft();
			} else if (keynum == keys.getRight()){
				if (boardMatrix.checkCollisionsToGoRight(piece.getLocationOnMatrix()))
					piece.moveABlockRight();
			} else if (keynum == keys.getRotate()){
				String currentCase = boardMatrix.checkCollisionsWhenRotating(piece.cloneRotateAndGetLocationOnMatrix());
				if (!currentCase.equals("NOROTATE")){
					while (currentCase.equals("FIX")){
						piece.moveToAppropriatePositionToRotate(boardMatrix.getColumnSize());
						currentCase = boardMatrix.checkCollisionsWhenRotating(piece.cloneRotateAndGetLocationOnMatrix());
					}
					piece.rotate();
				}
			} else if (keynum == keys.getDown()){
				if (boardMatrix.checkCollisionsToGoBelow(piece.getLocationOnMatrix()))
					piece.moveABlockDown();
				else
					callerEngine.play();
			} else if (keynum == keys.getPause()){
				if (getMode())
					setMode(false);
				else
					setMode(true);
			}

			Rectangle all = new Rectangle(0,0,getWidth(),getHeight());
			repaintPanel(all);
			repaintPanel(oldPos);

		}
		@Override
		public void keyReleased(KeyEvent e) { }
		@Override
		public void keyTyped(KeyEvent e) { }

		// this is the callback for the timer
		@Override
		public void actionPerformed(ActionEvent e) {

			Rectangle oldPos = piece.boundingBox();
			if (boardMatrix.checkCollisionsToGoBelow(piece.getLocationOnMatrix()))
				piece.moveABlockDown();
			else
				callerEngine.play();
			Rectangle all = new Rectangle(0,0,getWidth(),getHeight());
			repaintPanel(oldPos);
			repaintPanel(all);
		}
	}

	class PauseKeyListener implements KeyListener{

		public PauseKeyListener(){}

		@Override
		public void keyPressed(KeyEvent e) {
			int keynum = e.getKeyCode();
			if (keynum == keys.getPause())
				setMode(true);
		}

		@Override
		public void keyReleased(KeyEvent e) { }
		@Override
		public void keyTyped(KeyEvent e) { }
	}

	class ClearLineListener implements ActionListener{
		private int totalBlockNumber;
		private ArrayList<Integer> cleanedCounters;
		private ArrayList<Integer> lineNumbers;

		public ClearLineListener(int totalBlockNumber){
			cleanedCounters = new ArrayList<Integer>();
			this.totalBlockNumber = totalBlockNumber;
			lineNumbers = new ArrayList<Integer>();
		}

		public void addDeletedLineNo(int n){
			lineNumbers.add(n);
			cleanedCounters.add(0);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int lineNo = 0;
			int cleanedCounter = 0;
			int index = 0;
			if (e.getSource().toString().equals(timerForClearLine1.toString())){
				lineNo = lineNumbers.get(0);
				cleanedCounter = cleanedCounters.get(0);
			} else if (e.getSource().toString().equals(timerForClearLine2.toString())){
				lineNo = lineNumbers.get(1);
				cleanedCounter = cleanedCounters.get(1);
				index = 1;
			} else if (e.getSource().toString().equals(timerForClearLine3.toString())){
				lineNo = lineNumbers.get(2);
				cleanedCounter = cleanedCounters.get(2);
				index = 2;
			} else {
				lineNo = lineNumbers.get(3);
				cleanedCounter = cleanedCounters.get(3);
				index = 3;
			}

			if (cleanedCounter != totalBlockNumber){
				for (int i=0; i<blocks.size(); i++){
					int lineOfCurrentBlock = blocks.get(i).getY() / blocks.get(i).getBlockSize() + 5;
					if (lineOfCurrentBlock == lineNo){
						blocks.remove(i);
						repaint();
						cleanedCounters.set(index, ++cleanedCounter);
						Rectangle oldPos = piece.boundingBox();
						repaintPanel(oldPos);
						break;
					}
				}
			} else {
				if (lineNo == lineNumbers.get(0))
					timerForClearLine1.stop();
				else if (lineNo == lineNumbers.get(1))
					timerForClearLine2.stop();
				else if (lineNo == lineNumbers.get(2))
					timerForClearLine3.stop();
				else
					timerForClearLine4.stop();

				boolean clear = false;
				if (cleanedCounters.size()==1){
					if (e.getSource().toString().equals(timerForClearLine1.toString())){
						clear = true;
					}
				} else if (cleanedCounters.size()==2) {
					if (e.getSource().toString().equals(timerForClearLine2.toString())){
						clear = true;
					}
				} else if (cleanedCounters.size()==3) {
					if (e.getSource().toString().equals(timerForClearLine3.toString())){
						clear = true;
					}
				} else {
					if (e.getSource().toString().equals(timerForClearLine4.toString())){
						clear = true;
					}
				}

				if (clear){
					lineNo=30; // to find the minimum line no
					for (int i=0; i<lineNumbers.size() ; i++){
						if (lineNumbers.get(i) < lineNo)
							lineNo = lineNumbers.get(i);
					}
					for (int i=0; i<blocks.size(); i++){
						int lineOfCurrentBlock = blocks.get(i).getY() / blocks.get(i).getBlockSize() + 5;
						if (lineOfCurrentBlock < lineNo)
							blocks.get(i).move(0, lineNumbers.size() * blocks.get(i).getBlockSize());
					}

					while (!cleanedCounters.isEmpty())
						cleanedCounters.remove(0);
					while (!lineNumbers.isEmpty())
						lineNumbers.remove(0);
				}

				repaintPanel(new Rectangle(0,0,getWidth(),getHeight()));
			}
		}
	}
}
