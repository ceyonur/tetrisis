package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import pieces.*;
import settings.KeyConfigure;

public class Board extends JPanel {
	private AnimationEventListener eventListener;
	private PauseKeyListener pauseListener;
	private Timer timer;
	private boolean mode;
	private Piece piece;
	private KeyConfigure keys;

	public Board(KeyConfigure keys, int speed) {
		// effects: initializes this to be in the off mode.

		super();                    // do the standard JPanel setup stuff
		setBackground(Color.GRAY);
		this.keys = keys;
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
		// modifies: <g>
		// effects: Repaints the Graphics area <g>.  Swing will then send the
		//          newly painted g to the screen.

		// first repaint the proper background color (controlled by
		// the windowing system)
		super.paint(g);
		piece.paint(g);
	}

	public void addPiece(Piece piece){
		this.piece = piece;
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

		private Board callerBoard;

		public AnimationEventListener(Board caller){
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

			if (keynum == keys.getLeft())
				piece.moveABlockLeft();
			else if (keynum == keys.getRight())
				piece.moveABlockRight();
			else if (keynum == keys.getRotate())
				piece.rotate();
			else if (keynum == keys.getDown())
				piece.moveABlockDown();
			else if (keynum == keys.getPause()){
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
			// modifes: both the ball and the window that this listener owns
			// effects: causes the ball to move and the window to be updated
			//          to show the new position of the ball.


			Rectangle oldPos = piece.boundingBox();

			piece.moveABlockDown();             // make changes to the logical animation state

			repaintPanel(oldPos);
			// Have Swing tell the AnimationWindow to run its paint()
			// method.  One could also call repaint(), but this would
			// repaint the entire window as opposed to only the portion that
			// has changed.


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
		private Board callerBoard;

		public PauseKeyListener(Board caller){
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
