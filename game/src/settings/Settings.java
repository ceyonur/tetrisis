package settings;

// TODO: Auto-generated Javadoc
/**
 * The Class Settings.
 * @author ceyonur
 */
public class Settings {
	
	/** The size. */
	private BoardSize size;
	
	/** The key. */
	private KeyConfigure key;
	
	/** The level. */
	private LevelChoice level;
	
	/** The piece. */
	private PieceChoice piece;

	/**
	 * Default constructor for the Settings class.
	 */
	public Settings() {
		size = new BoardSize();
		key = new KeyConfigure();
		level = new LevelChoice();
		piece = new PieceChoice();
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public BoardSize getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(BoardSize size) {
		this.size = size;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public KeyConfigure getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(KeyConfigure key) {
		this.key = key;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public LevelChoice getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(LevelChoice level) {
		this.level = level;
	}

	/**
	 * Gets the piece.
	 *
	 * @return the piece
	 */
	public PieceChoice getPiece() {
		return piece;
	}

	/**
	 * Sets the piece.
	 *
	 * @param piece the new piece
	 */
	public void setPiece(PieceChoice piece) {
		this.piece = piece;
	}
}
