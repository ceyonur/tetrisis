package gui;

import javax.swing.*;
import javax.swing.text.*;

import game.Engine;
import highscores.Player;

import java.awt.*;
import java.awt.event.*;

/**
 * This is the JFrame extension class generated when new game button is pressed
 * @author bedirhancaldir
 */
public class PlayGUI extends JFrame {
	protected Engine engine; // The caller engine object
	private BoardPanel board; // One of the sibling panels contained by this JFrame; board panel GUI
	private NextPieceAndScorePanel nextPiecePanel; // Other sibling panel contained by this JFrame; next piece and score panel GUI
	private GameOverListener gameOverListener; // The game over listener checking if the game is over or not
	private JPanel realPane; // This is the real panel containing two sibling panels boardPanel and nextPiecePanel
	private JPanel generalPane; // This is the panel containing realPane in it. Here for just layouting
	private Timer timerForCheckingGameOver; // Timer for checking game over
	private Timer timerForShakingWindow; // Timer controlling the shaking process
	private int width; // The width of the Frame
	private int height; // The height of the Frame
	private GUI gui; // The caller GUI object

	private AudioPlayers audioPlayers; // The audio player of the frame
	int shakeCounter = 0; // This variable holds the how many times the shaking is done
	private static final int SHAKEFINISHNUMBER = 50;

	/**
	 * This is the constructor of the class, initializes the timers and listeners mainly. The caller GUI is also set
	 * @param ui The caller GUI of the object
	 */
	public PlayGUI(GUI ui){
		super();
		this.gui = ui; // sets the gui of the object to the given gui

		createTimersAndListeners();
	}

	/**
	 * This method creates the timers and the listeners of the JFrame
	 */
	private void createTimersAndListeners(){
		gameOverListener = new GameOverListener();

		timerForCheckingGameOver = new Timer(500, gameOverListener);
		timerForShakingWindow = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shakeCounter != SHAKEFINISHNUMBER){
					shakeCounter++;
					if (shakeCounter % 2 == 1){
						setLocation((int) getLocation().getX() + 10, (int) getLocation().getY());
					} else {
						setLocation((int) getLocation().getX() - 10, (int) getLocation().getY());
					}
				} else {
					setLocationRelativeTo(null);
					timerForShakingWindow.stop();
					shakeCounter = 0;
				}
			}
		});

		// The regular closing operation
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeFrame();
			}
		});
	}

	/**
	 * This method accepts an engine to set the game's engine. All of the initializations of the boards are done after an engine is given
	 * @param engine The engine of the game
	 */
	public void setEngine(Engine engine) {
		initiateFields(engine);
		editAppearance();

		width = engine.getBoardColumnLength(); // sets the width and height of the frame
		height = engine.getBoardRowLength();

		pack();
	}

	/**
	 * This method initiates the fields of the class
	 * @param engine The engine which will be the game's engine
	 */
	private void initiateFields(Engine engine){
		this.engine = engine;
		audioPlayers = engine.getAudioPlayers(); // The audioplayer of the game is set to the engine's one
		audioPlayers.playPlayGUIBackground(true); // The audioplayer starts to play
		timerForCheckingGameOver.start(); // The game over checker timer starts its job

		board = engine.getBoardPanel(); // The game board GUI is taken from the engine
		board.setCurrentPlayGUI(this);
		engine.getBoardMatrix().setCurrentPlayGUI(this);
		nextPiecePanel = engine.getNextPieceAndScorePanel(); // The next piece area GUI of the game is taken from the engine
		nextPiecePanel.setPlayGUI(this);
		nextPiecePanel.setAudioPlayers(audioPlayers); // The audioplayers of the next piece panel is set to the one of engine
	}

	private void editAppearance(){
		// Put it in a scrollPane, (this makes a border)
		JScrollPane gameBoard = new JScrollPane(board); // The sibling panels are set
		JScrollPane nextPieceAndScorePanel = new JScrollPane(nextPiecePanel);

		realPane = new JPanel(); // The panel containing the siblings and puts them in a layout to display them truely
		realPane.setLayout(new GridLayout(1, 2));
		realPane.add(gameBoard);
		realPane.add(nextPieceAndScorePanel);
		// Lay out the content pane.
		generalPane = new JPanel();
		generalPane.setLayout(new BorderLayout());
		generalPane.setPreferredSize(new Dimension(engine.getBoardColumnLength() * 2, engine.getBoardRowLength()));
		generalPane.add(realPane, BorderLayout.CENTER);
		add(generalPane);
	}

	/**
	 * This method opens a new JFrame of game over and shows it 
	 */
	public void showGameOver(){
		setEnabled(false);
		JFrame gameOverFrame = new GameOverPanel(engine.getScore(), engine.getLevelNo(), this);
		gameOverFrame.setLocationRelativeTo(null);
		audioPlayers.disableOrEnablePlayGUIBackgroundSound(false);
		audioPlayers.playGameOver(true);
		gameOverFrame.setVisible(true);
		repaint();
		pack();
	}

	/**
	 * This method shakes the screen when a tetris is happened
	 */
	public void shakeWindow(){
		setLocation((int) getLocation().getX() + 5, (int) getLocation().getY());
		timerForShakingWindow.start();
	}

	/**
	 * This is an inner class of game over listener that checks if the game is over or not
	 * @author bedirhancaldir
	 */
	class GameOverListener implements ActionListener {

		public GameOverListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (engine.isGameOver()) {
				timerForCheckingGameOver.stop();
				showGameOver();
			}
		}
	}

	/**
	 * This method simply closes the game playing frame and destroys related objects
	 */
	public void closeFrame(){
		engine.pause(); // stops the engine
		gui.setEnabled(true); // make the actual menu gui enabled to make it interactable again
		timerForCheckingGameOver.stop(); // stops the game over checker
		audioPlayers.disableOrEnableAllSounds(false); // disables all of the sound related timers
		engine = null; // delete the whole engine
		gui.menuMusicEnabler(); // menu's music is enabled
		dispose(); // disposes this screen
	}

	public class GameOverPanel extends JFrame {
		PlayGUI callerPlayGUI; // The caller PlayGUI object

		JPanel submissionContainer; // The container holding name field and submit button for players have score that can be accepted to the high scores list
		JPanel infoContainer; // The container containing score of the player
		JPanel headerContainer; // The container holding the "game over!" label in it
		JPanel buttonsContainer; // The container holding the restart, main menu and exit buttons in it

		/**
		 * This is the constructor of the gameover class, everything is handled in this constructor actually
		 * @param score The score of the player
		 * @param level The level of the game
		 * @param callerPlayGUI The caller PlayGUI object
		 */
		public GameOverPanel(double score, int level, PlayGUI callerPlayGUI) {
			super();

			this.callerPlayGUI = callerPlayGUI;
			getContentPane().setBackground(SColor.backgroundColor);
			setPreferredSize(new Dimension(400,400));
			setResizable(false);
			setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

			createContainers();

			fillHeaderContainer();
			fillInfoContainer(score);
			fillSubmissionContainer(score);
			fillButtonsContainer();

			setTheContainersLocation(score);
			
			setCloseOperation();
		}

		/**
		 * This method creates the containers of the panel : headerContainer, infoContainer, submissionContainer and buttonsContainer
		 */
		private void createContainers(){
			headerContainer = new JPanel();
			headerContainer.setBackground(SColor.backgroundColor);
			infoContainer = new JPanel();
			infoContainer.setBackground(SColor.backgroundColor);
			submissionContainer = new JPanel();
			submissionContainer.setBackground(SColor.backgroundColor);
			buttonsContainer = new JPanel();
			buttonsContainer.setBackground(SColor.backgroundColor);
		}

		/**
		 * This method fills the headerContainer
		 */
		private void fillHeaderContainer(){
			SLabel gameOverLabel = new SLabel("game over!", SLabel.GAMEOVER_HEADER_LABEL);
			headerContainer.add(gameOverLabel);
		}

		/**
		 * This method fills the infoContainer
		 * @param score The score of the player
		 */
		private void fillInfoContainer(double score){
			String infoScore = "your score: " + Engine.round(score,2);
			SLabel scoreLabel = new SLabel(infoScore, SLabel.GAMEOVER_INFO_LABEL);
			infoContainer.add(scoreLabel);
		}

		/**
		 * This method fills the submissionContainer
		 * @param score The score of the player
		 */
		private void fillSubmissionContainer(double score){
			JPanel submissionPanel = new JPanel();
			submissionPanel.setLayout(new BoxLayout(submissionPanel, BoxLayout.X_AXIS));
			submissionPanel.setBackground(SColor.backgroundColor);

			SLabel nameLabel = new SLabel("enter your name to high scores:", SLabel.GAMEOVER_SUBMISSION_LABEL, SwingConstants.CENTER);
			JTextField nameInput = new JTextField(10);
			nameInput.setDocument(new JTextFieldCharacterLimit(10));
			SButton nameSubmit = new SButton("submit", SButton.GAMEOVER_SUBMISSION_BUTTON);
			nameSubmit.addActionListener(new SubmitHandler(nameInput, score));

			submissionPanel.add(Box.createHorizontalStrut(50));
			submissionPanel.add(nameInput);
			submissionPanel.add(Box.createHorizontalStrut(10));
			submissionPanel.add(nameSubmit);
			submissionPanel.add(Box.createHorizontalStrut(50));

			submissionContainer.setLayout(new GridLayout(2,1,0,10));
			submissionContainer.add(nameLabel);
			submissionContainer.add(submissionPanel);
		}

		/**
		 * This method fills the buttonsContainer 
		 */
		private void fillButtonsContainer(){
			buttonsContainer.add(addButtons());
		}

		/**
		 * This method sets the location of the containers by using Box layout
		 * @param score The score of the player
		 */
		private void setTheContainersLocation(double score){
			getContentPane().add(headerContainer);
			getContentPane().add(Box.createVerticalStrut(30));
			getContentPane().add(infoContainer);
			getContentPane().add(Box.createVerticalStrut(30));
			if (engine.isScoreHighEnough(score)) {
				getContentPane().add(submissionContainer);
				getContentPane().add(Box.createVerticalStrut(30));
			}
			getContentPane().add(buttonsContainer);

			pack();
		}
		
		private void setCloseOperation(){
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					gui.setEnabled(true);
					audioPlayers.disableOrEnableGameOverSound(false);
					gui.menuMusicEnabler();
					dispose();
					callerPlayGUI.closeFrame();
				}
			});
		}

		/**
		 * This method adds the buttons of Restart the game, Return back to the main menu, and Exit 
		 * @return A JPanel containing these three buttons in it
		 */
		protected JPanel addButtons() {

			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(3, 1,0,-5));
			buttons.setPreferredSize(new Dimension(240,120));
			buttons.setBackground(SColor.backgroundColor);

			SButton restartButton = new SButton("restart", SButton.GAMEOVER_BUTTON);
			restartButton.setToolTipText("Restart the Tetris/Trisis game");
			restartButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // This button restarts the game by completely closing this game and creating another one				
					gui.showPlay();					
					audioPlayers.disableOrEnableGameOverSound(false);					
					dispose();
					callerPlayGUI.dispose();
				}
			});
			buttons.add(restartButton);

			SButton returnButton = new SButton("return to menu", SButton.GAMEOVER_BUTTON);
			returnButton.setToolTipText("Back to the main menu");
			returnButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // This button closes the game's and gameover's frames and enables the actual menu gui
					gui.setEnabled(true);
					audioPlayers.disableOrEnableGameOverSound(false);
					gui.menuMusicEnabler();
					dispose();
					callerPlayGUI.closeFrame();
				}
			});
			buttons.add(returnButton);

			SButton exitButton = new SButton("exit game", SButton.GAMEOVER_BUTTON);
			exitButton.setToolTipText("Quit the program");
			exitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0); // This button makes the program stopped
				}
			});
			buttons.add(exitButton);

			return buttons;
		}

		/**
		 * This method handles the highscore addition process to the high scores list of the game
		 * @author bedirhancaldir
		 */
		class SubmitHandler implements ActionListener{
			private JTextField nameField;
			private double score;

			public SubmitHandler(JTextField name, double score){
				nameField = name;
				this.score = score;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				SButton button = (SButton) e.getSource();
				String name = nameField.getText();
				nameField.setEnabled(false);
				button.setEnabled(false);

				Player newPlayer = new Player(name, Engine.round(score, 2));
				engine.addPlayerToHighScoreList(newPlayer);

				((SLabel) submissionContainer.getComponent(0)).setText("your score is summitted.");
				submissionContainer.remove(nameField);
				submissionContainer.remove(button);
				submissionContainer.repaint();
				repaint();
			}
		}
	}

	/**
	 * This method puts a limit to write on a JTextField and prevents writing more than limited characters into it
	 * @author atilberk
	 */
	class JTextFieldCharacterLimit extends PlainDocument {
		private int limit;

		JTextFieldCharacterLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
			if (str == null) return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

}
