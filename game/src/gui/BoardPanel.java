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
	private Timer timer;
	private boolean mode;
	private Piece piece;
	private KeyConfigure keys;
	private Engine callerEngine;
	private ArrayList<Block> blocks;
	private game.Board boardMatrix;

	public BoardPanel(KeyConfigure keys, int speed, Engine engine, game.Board boardMatrix) {
		// effects: initializes this to be in the off mode.

		super();                    // do the standard JPanel setup stuff
		setBackground(Color.GRAY);
		this.keys = keys;
		this.boardMatrix = boardMatrix;
		callerEngine = engine;
		blocks = new ArrayList<Block>();
		//putDotIndicators();
		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method
		eventListener = new AnimationEventListener(this);
		pauseListener = new PauseKeyListener(this);
		// The first parameter is how often (in milliseconds) the timer
		// should call us back.  50 milliseconds = 20 frames/second
		timer = new Timer(speed, eventListener);
		mode = false;
	}

	// This is just here so that we can accept the keyboard focus
	public boolean isFocusTraversable() { return true; }

	public void paint(Graphics g) {
		super.paint(g);
		//piece.paint(g);
		for (int i=0; i<blocks.size(); i++){
			blocks.get(i).paint(g);
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
			addKeyListener(pauseListener);
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
		}
		else {
			timer.stop();
		}		
	}

	public boolean getMode(){
		return mode;
	}

	public void clearEliminatedLine(int lineNo){
		for (int i=0; i<blocks.size(); i++){
			int lineOfCurrentBlock = blocks.get(i).getY() / blocks.get(i).getBlockSize() + 1;
			if (lineOfCurrentBlock == lineNo){
				blocks.remove(i);
				i--;
			} else if (lineOfCurrentBlock < lineNo){
				blocks.get(i).move(0, blocks.get(i).getBlockSize());
			}
		}
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

		private BoardPanel callerBoard;

		public AnimationEventListener(BoardPanel caller){
			super();		
			callerBoard = caller;
		}
		// MouseAdapter gives us empty methods for the MouseListener
		// interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
		// and mouseReleased.

		public void mouseClicked(MouseEvent e) { }
		public void mouseDragged(MouseEvent e) { }
		public void mouseMoved(MouseEvent e) { }

		// Here's the KeyListener interface
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
				piece.rotate();
			} else if (keynum == keys.getDown()){
				if (boardMatrix.checkCollisionsToGoBelow(piece.getLocationOnMatrix()))
					piece.moveABlockDown();
			} else if (keynum == keys.getPause()){
				if (callerBoard.getMode())
					callerBoard.setMode(false);
				else
					callerBoard.setMode(true);
			}

			repaintPanel(oldPos);

		}
		public void keyReleased(KeyEvent e) { }
		public void keyTyped(KeyEvent e) { }

		// this is the callback for the timer
		public void actionPerformed(ActionEvent e) {

			Rectangle oldPos = piece.boundingBox();
			if (boardMatrix.checkCollisionsToGoBelow(piece.getLocationOnMatrix()))
				piece.moveABlockDown();
			else
				callerEngine.play();

			repaintPanel(oldPos);
		}

		private void repaintPanel(Rectangle oldPos){
			Rectangle repaintArea = oldPos.union(piece.boundingBox());
			repaint(repaintArea.x,
					repaintArea.y,
					repaintArea.width,
					repaintArea.height);
		}
	}

	class PauseKeyListener implements KeyListener{
		private BoardPanel callerBoard;

		public PauseKeyListener(BoardPanel caller){
			callerBoard = caller;
		}

		public void keyPressed(KeyEvent e) {
			int keynum = e.getKeyCode();
			if (keynum == keys.getPause()){
				callerBoard.setMode(true);
			}
		}

		public void keyReleased(KeyEvent e) { }
		public void keyTyped(KeyEvent e) { }
	}
}
