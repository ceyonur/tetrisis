package gui;

import game.Engine;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import pieces.*;
import settings.KeyConfigure;

public class BoardPanel extends JPanel {
	private AnimationEventListener eventListener; // This is the main listener of the class. Controls the new piece creation and key functionalities.
	private PauseKeyListener pauseListener; // This listener is active when the game's mode is false meaning only pause button is active. 
											// Activates the actual listeners again
	private ClearLineListener clearLineListener; // This listener active when a line is cleaned from the logical board to destroy them on the GUI as well
	private RestartActualTimerAgain restartActualTimerAgain; // This listener handles the conversion of the inactive state to the active state of the actual
															 // timer controlling almost all events in the game like keyboard functions and game clock
	private FastFadeOutUpdater fastFadeOutUpdater; // This listener is activated when a line is deleted to give the blocks in that line fading out effect
	
	private Timer timer; // This is the main timer of the game, calls AnimationEventListener and handles the main playing activities like moving down a
						 // piece according to the given time period
	private Timer timerForClearLine1; // This timer is activated when a line is deleted (one or more lines)
	private Timer timerForClearLine2; // This timer is activated when two lines are deleted (two or more lines) - provides simultaneous deletion
	private Timer timerForClearLine3; // This timer is activated when three lines are deleted (three or more lines) - provides simultaneous deletion
	private Timer timerForClearLine4; // This timer is activated when tetris is happened (four lines) - provides simultaneous deletion
	private Timer fastUpdaterForFadeOut; // This timer is activated when one or more lines are deleted to give the blocks in that line fading out effect
	private Timer timerForActivationTheActualTimerAgain; // This timer holds the game for about 2 seconds when one or more lines are deleted to prevent
														 // the flow of the game during deletion period
	
	private boolean mode; // The state of the game (when true, game continues; when false, game pauses)
	private Piece piece; // The current falling down piece object
	private KeyConfigure keys; // The key configuration coming from Settings 
	private Engine callerEngine; // The engine object creating this BoardPanel object
	private ArrayList<Block> blocks; // The list holding the whole fallen pieces' blocks to access them
	private ArrayList<Block> deletedBlocks; // The list holding the deleted blocks when a line is deleted (holds for a while - not forever)
	private game.Board boardMatrix; // This is the logical board of the game which is independent from the GUI
	private NextPieceAndScorePanel nextPiecePanel; // This is the next piece panel containing next piece, score, deleted lines and level information
	private AudioPlayers audioPlayers; // This is the main audio source of the game

	private SLabel paused; // This is the "Paused" label when the game is paused
	private PlayGUI playGUI; // This is the JFrame containing this object in it
	public static final int deletionSpeed = 75; // The time in ms to delete a line

	/**
	 * This is the constructor of the class; takes the required object references and generates timers and listeners as well
	 * @param keys The key configuration of the game set from the settings GUI
	 * @param speed The speed of the game set from the settings GUI
	 * @param engine The game engine controlling the backend
	 * @param boardMatrix The logical board holding the game board information and deciding line deletion if necessary
	 * @param nextPiecePanel The sibling panel containing next piece, score, deleted lines and level information
	 */
	public BoardPanel(KeyConfigure keys, int speed, Engine engine, game.Board boardMatrix, NextPieceAndScorePanel nextPiecePanel) {
		super();
		setBackground(SColor.boardColor);
		
		blocks = new ArrayList<Block>();
		deletedBlocks = new ArrayList<Block>();
		
		this.keys = keys;
		this.boardMatrix = boardMatrix;
		callerEngine = engine;
		audioPlayers = engine.getAudioPlayers();
		this.nextPiecePanel = nextPiecePanel;
		
		paused = new SLabel("pause", SLabel.PANEL_PAUSE_LABEL);
		add(paused);
		
		timerAndListenerInitializer(speed);
		
		setMode(true);
	}
	
	/**
	 * This method initializes the timers and listeners of the game
	 * @param speed The speed of the game determined by the Settings class
	 */
	public void timerAndListenerInitializer(int speed){
		eventListener = new AnimationEventListener();
		pauseListener = new PauseKeyListener();
		clearLineListener = new ClearLineListener(boardMatrix.getColumnSize());
		restartActualTimerAgain = new RestartActualTimerAgain(this);
		fastFadeOutUpdater = new FastFadeOutUpdater(this);
		
		timer = new Timer(speed, eventListener);
		fastUpdaterForFadeOut = new Timer(1,fastFadeOutUpdater);
		timerForClearLine1 = new Timer(deletionSpeed,clearLineListener);
		timerForClearLine2 = new Timer(deletionSpeed,clearLineListener);
		timerForClearLine3 = new Timer(deletionSpeed,clearLineListener);
		timerForClearLine4 = new Timer(deletionSpeed,clearLineListener);
		timerForActivationTheActualTimerAgain = new Timer(deletionSpeed * boardMatrix.getColumnSize(), restartActualTimerAgain);
	}

	/**
	 * This is just here so that we can accept the keyboard focus
	 */
	@Override
	public boolean isFocusTraversable() { return true; }

	/**
	 * This method paints the blocks, deleted blocks and the "Paused" string when necessary
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (mode == true){
			piece.paint(g);
			for(int i=0;i<deletedBlocks.size(); i++){
				deletedBlocks.get(i).paint(g);
			}
			for (int i=0; i<blocks.size(); i++){
				blocks.get(i).paint(g);
			}
			paused.setVisible(false);
			setBackground(SColor.boardColor);
		} else {
			if (!callerEngine.isGameOver()){
				paused.setLocation(paused.getX(), (getHeight() - paused.getHeight()) / 2);
				paused.setVisible(true);
				setBackground(SColor.boardPauseColor);
			}
		}
	}

	/**
	 * This method takes a generated piece and sets the current falling piece of the game
	 * @param piece The newly generated piece
	 */
	public void addPiece(Piece piece){
		this.piece = piece;
		blocks.addAll(piece.getBlocks());
	}

	/**
	 * This method sets the game's current state to the given parameter and changes the listener-timer configurations
	 * @param m The new state of the game
	 */
	public void setMode(boolean m) {

		if (mode == true) {
			removeKeyListener(eventListener);
		}

		mode = m;

		if (mode == true) {
			addKeyListener(eventListener);
			removeKeyListener(pauseListener);
			requestFocus();  // make sure keyboard is directed to us
			timer.start();
		} else {
			addKeyListener(pauseListener);
			timer.stop();
		}
		repaint();
		nextPiecePanel.setVisibility(mode); // The next piece panel must also be paused, so its visibility is changed by this line
	}

	/**
	 * This method returns the current state of the game, true for continuing game, false for paused game
	 * @return The state of the game
	 */
	public boolean getMode(){
		return mode;
	}

	/**
	 * This method helps to restart the actual functional timer
	 */
	public void restartTimer(){
		timer.restart();
	}

	/**
	 * This method sets the current playGUI JFrame containing this panel
	 * @param playGUI The JFrame PlayGUI object containing this panel
	 */
	public void setCurrentPlayGUI(PlayGUI playGUI){
		this.playGUI = playGUI;
	}

	/**
	 * This method forces the game to wait the line deletions to request a new piece; a useful optimization
	 */
	public void delayPieceSelection(){
		timer.stop();
		timerForActivationTheActualTimerAgain.start();
		removeKeyListener(eventListener);
	}

	/**
	 * This method clears the deleted lines from the GUI and does it simultaneously by activating the required timers
	 * @param lineNo This is the total deleted line number to determine how many timer is needed to deletion simultaneously
	 */
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

	/**
	 * This method repaints a certain rectangle of the panel; 
	 * @param oldPos
	 */
	private void repaintPanel(Rectangle oldPos){
		Rectangle repaintArea = oldPos.union(piece.boundingBox());
		repaint(repaintArea.x,
				repaintArea.y,
				repaintArea.width,
				repaintArea.height);
	}

	/**
	 * This is the inner class for the AnimationEventListener. Controls the whole flow of the game
	 * @author bedirhancaldir
	 */
	class AnimationEventListener extends MouseAdapter
	implements MouseMotionListener, KeyListener, ActionListener
	{
		// overview: AnimationEventListener is an inner class that
		// responds to all sorts of external events, and provides the
		// required semantic operations for our particular program.

		/**
		 * The constructor of the class
		 */
		public AnimationEventListener(){
			super();
		}

		/**
		 * No mouse interaction at all in the game for this panel
		 */
		@Override
		public void mouseClicked(MouseEvent e) { }
		@Override
		public void mouseDragged(MouseEvent e) { }
		@Override
		public void mouseMoved(MouseEvent e) { }

		/**
		 * This method handles the keyboard pressing functionalities and effects of them to the game
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			
			int keynum = e.getKeyCode();
			Rectangle oldPos = piece.boundingBox();

			if (keynum == keys.getLeft()){
				if (boardMatrix.checkCollisionsToGoLeft(piece.getLocationOnMatrix())) // checks collisions to go left
					piece.moveABlockLeft(); // if it can do that, just do that
			} else if (keynum == keys.getRight()){
				if (boardMatrix.checkCollisionsToGoRight(piece.getLocationOnMatrix())) // checks collisions to go right
					piece.moveABlockRight(); // if it can do that, just do that
			} else if (keynum == keys.getRotate()){
				String currentCase = boardMatrix.checkCollisionsWhenRotating(piece.cloneRotateAndGetLocationOnMatrix()); // checks collisions to rotate
				if (!currentCase.equals("NOROTATE")){ // if the piece cannot be rotate (if its both right and left is filled)
					while (currentCase.equals("FIX")){ // if the piece can move to a location where it can rotate, just do that and rotate
						piece.moveToAppropriatePositionToRotate(boardMatrix.getColumnSize());
						currentCase = boardMatrix.checkCollisionsWhenRotating(piece.cloneRotateAndGetLocationOnMatrix());
					}
					piece.rotate();
					audioPlayers.playEffects(true, AudioPlayers.ROTATE); // rotation sound plays
				}
			} else if (keynum == keys.getDown()){ // checks collisions to go down
				if (boardMatrix.checkCollisionsToGoBelow(piece.getLocationOnMatrix())) // if it can, just do that
					piece.moveABlockDown();
				else{ // if it is impossible (the below of the piece is already filled or floor)
					audioPlayers.playEffects(true, AudioPlayers.SITSOUND); 
					callerEngine.play(); // make this piece sit and generate a new piece
				}
			} else if (keynum == keys.getPause()){ // The pause button handler
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

		/**
		 * This method is repeatedly called by the actual timer of the game controlling the natural game flow until the and of the game 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			Rectangle oldPos = piece.boundingBox();
			if (boardMatrix.checkCollisionsToGoBelow(piece.getLocationOnMatrix()))
				piece.moveABlockDown();
			else{
				audioPlayers.playEffects(true, AudioPlayers.SITSOUND);
				callerEngine.play();
			}
			Rectangle all = new Rectangle(0,0,getWidth(),getHeight());
			repaintPanel(oldPos);
			repaintPanel(all);
		}
	}

	/**
	 * This is another inner class handling the pause key functionality when the game is paused and none of the other keys are functional
	 * @author bedirhancaldir
	 */
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

	/**
	 * This is another inner class handling showing the effects of the fading out of the blocks
	 * @author bedirhancaldir
	 */
	class FastFadeOutUpdater implements ActionListener{
		private BoardPanel callerBoard;

		public FastFadeOutUpdater(BoardPanel board){
			callerBoard = board;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			callerBoard.repaint();
		}

	}

	/**
	 * This is another inner class restarting the actual functional timer after the delay of deletion process
	 * @author bedirhancaldir
	 */
	class RestartActualTimerAgain implements ActionListener{
		private BoardPanel callerPanel;

		public RestartActualTimerAgain(BoardPanel callerPanel){
			this.callerPanel = callerPanel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			timer.start();
			callerPanel.addKeyListener(eventListener);
			timerForActivationTheActualTimerAgain.stop();
		}
	}

	/**
	 * This is another inner class of action listener handling the line clearing effect
	 * @author bedirhancaldir
	 */
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
			// All of the four timer may be installed or not according to the total deleted line number
			if (e.getSource().toString().equals(timerForClearLine1.toString())){ // If the source of the call is the first simultaneous cleaner
				lineNo = lineNumbers.get(0); // Sets the searched line to the lowest line
				cleanedCounter = cleanedCounters.get(0); 
			} else if (e.getSource().toString().equals(timerForClearLine2.toString())){ // If the source of the call is the second simultaneous cleaner
				lineNo = lineNumbers.get(1); // Sets the searched line to the one above of the lowest line
				cleanedCounter = cleanedCounters.get(1);
				index = 1;
			} else if (e.getSource().toString().equals(timerForClearLine3.toString())){ // If the source of the call is the third simultaneous cleaner
				lineNo = lineNumbers.get(2); // Sets the searched line to the one below of the highest line
				cleanedCounter = cleanedCounters.get(2);
				index = 2;
			} else { // If the source of the call is the last simultaneous cleaner
				lineNo = lineNumbers.get(3); // Sets the searched line to the the highest line
				cleanedCounter = cleanedCounters.get(3);
				index = 3;
			}

			// If the cleanedCounter isn't equal to the totalBlockNumber, then there are some blocks which hasn't been deleted yet
			if (cleanedCounter != totalBlockNumber){
				fastUpdaterForFadeOut.start(); // repaints the location of the block to see the fadeout effect
				for (int i=0; i<blocks.size(); i++){
					int lineOfCurrentBlock = blocks.get(i).getY() / blocks.get(i).getBlockSize() + 5;
					if (lineOfCurrentBlock == lineNo){ // if the block lays on the desired line
						Block aDeletedBlock = blocks.remove(i); // remove it from the blocks list
						deletedBlocks.add(aDeletedBlock); //  adds it to the deleted blocks list
						aDeletedBlock.fadeOut(); // call the fade out effect of the block
						repaint();
						cleanedCounters.set(index, ++cleanedCounter); // increased the cleaned counter to stop at a point
						Rectangle oldPos = piece.boundingBox();
						repaintPanel(oldPos);
						break;
					}
				}
			} else { // All of the blocks laying on the deleted line are deleted completely
				// The corresponding timer is stopped
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
					// Remove the whole deleted blocks from the list (they are stored not to lose them when the fading put is happening)
					while(!deletedBlocks.isEmpty()){
						deletedBlocks.remove(0);
					}
					Collections.sort(lineNumbers); // sorts the line numbers

					// move a block length down the blocks found above the deleted line
					while (!lineNumbers.isEmpty()){
						int currentLineNo = lineNumbers.remove(0);
						for (int i=0; i<blocks.size(); i++){
							int lineOfCurrentBlock = blocks.get(i).getY() / blocks.get(i).getBlockSize() + 5;
							if (lineOfCurrentBlock < currentLineNo){
								boolean isThisBelongToCurrentPiece = false;
								for (int j=0; j<piece.getBlocks().size() ; j++){
									if (piece.getBlocks().get(j) == blocks.get(i))
										isThisBelongToCurrentPiece = true;
								}
								if (!isThisBelongToCurrentPiece)
									blocks.get(i).move(0, blocks.get(i).getBlockSize());
							}
						}
					}

					// if the deletion process ends, the cleanedCounter list is also emptied
					while (!cleanedCounters.isEmpty())
						cleanedCounters.remove(0);

					fastUpdaterForFadeOut.stop(); // fading out timer is stopped
				}

				repaintPanel(new Rectangle(0,0,getWidth(),getHeight())); // repaint the whole panel
			}
		}
	}
}
