package gui;

import java.awt.*;
import java.awt.event.*;
import pieces.*;

import javax.swing.*;

public class Board extends JPanel {
	private AnimationEventListener eventListener;
	private Timer timer;
	private boolean mode;
	private ZTetriminos tet;

	public Board() {
		// effects: initializes this to be in the off mode.

		super();                    // do the standard JPanel setup stuff
		tet = new ZTetriminos(10,10,Color.GREEN);

		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method
		eventListener = new AnimationEventListener();
		// The first parameter is how often (in milliseconds) the timer
		// should call us back.  50 milliseconds = 20 frames/second
		timer = new Timer(50, eventListener);
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
		tet.paint(g);
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
			requestFocus();           // make sure keyboard is directed to us
			timer.start();
		}
		else {
			timer.stop();
		}
	}

	class AnimationEventListener extends MouseAdapter
	implements MouseMotionListener, KeyListener, ActionListener
	{
		// overview: AnimationEventListener is an inner class that
		// responds to all sorts of external events, and provides the
		// required semantic operations for our particular program.  It
		// owns, and sends semantic actions to the ball and window of the
		// outer class

		// MouseAdapter gives us empty methods for the MouseListener
		// interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
		// and mouseReleased.

		// for this example we only need to override mouseClicked
		public void mouseClicked(MouseEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction
			tet.move(-100, -100);
		}

		// Here's the MouseMotionListener interface
		public void mouseDragged(MouseEvent e) { }
		public void mouseMoved(MouseEvent e) { }

		// Here's the KeyListener interface
		public void keyPressed(KeyEvent e) {
			// modifes: the ball that this listener owns
			// effects: causes the ball to be bumped in a random direction but
			//          only if one of the keys A-J is pressed.
			int keynum = e.getKeyCode();

			if ((keynum >= 65) && (keynum <= 74)) {
				System.out.println("keypress " + e.getKeyCode());
				tet.move(-100, -100);
			}
		}
		public void keyReleased(KeyEvent e) { }
		public void keyTyped(KeyEvent e) { }

		// this is the callback for the timer
		public void actionPerformed(ActionEvent e) {
			// modifes: both the ball and the window that this listener owns
			// effects: causes the ball to move and the window to be updated
			//          to show the new position of the ball.


			Rectangle oldPos = tet.boundingBox();

			tet.move(10, 10);              // make changes to the logical animation state

			Rectangle repaintArea = oldPos.union(tet.boundingBox());

			// Have Swing tell the AnimationWindow to run its paint()
			// method.  One could also call repaint(), but this would
			// repaint the entire window as opposed to only the portion that
			// has changed.




			repaint(repaintArea.x,
					repaintArea.y,
					repaintArea.width,
					repaintArea.height);
		}
	}
}
