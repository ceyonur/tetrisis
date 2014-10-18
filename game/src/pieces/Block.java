package pieces;

import gui.SColor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class is the building stone of all of the pieces (both Tetriminoses and Triminoses).
 * @author bedirhancaldir
 */
public class Block {
	public static int SIZE = 25; // The size (width and height) of a block
	private final static Color defaultColor = Color.RED; // The default color of a block
	
	private int x; // The x-position of the upper-left corner of the block
	private int y; // The y-position of the upper-left corner of the block
	private Color color; // The color of the block
	
	private Timer timerForFadeAway; // The timer to fade the block out when it is deleted
	private FadeOutTheBlockListener fadeOutBlockListener; // The listener causing the transparency decrement

	/**
	 * The constructor of the class Block. Sets the color, x and y coordinates of the block
	 * @param x The x-position of the upper-left corner of the block
	 * @param y The y-position of the upper-left corner of the block
	 * @param color The color of the block
	 */
	public Block(int x, int y, Color color){
		setLocation(x,y);
		setColor(color);
		fadeOutBlockListener = new FadeOutTheBlockListener();
		timerForFadeAway = new Timer(2,fadeOutBlockListener);
	}
	
	/**
	 * The default constructor of the class. The location is set to 0  and the color is set to the default color as default.
	 */
	public Block(){
		this(0,0,defaultColor);
	}

	/**
	 * This method paints the block to the screen.
	 * @param g This is the Graphics object to which the block will be drawn.
	 */
	public void paint(Graphics g) {
		Rectangle clipRect = g.getClipBounds();

		if (clipRect.intersects(this.boundingBox())) {
			g.setColor(color);
			g.fill3DRect(x, y, SIZE, SIZE,true);
		}
	}
	
	/**
	 * This method cause the fade-out effect of the block when it is deleted from the game board
	 */
	public void fadeOut(){
		timerForFadeAway.start();
	}

	/**
	 * This method gives the rectangle covering the block.
	 * @return The box covering to the block's area
	 */
	public Rectangle boundingBox() {
		return new Rectangle(x, y, SIZE+1, SIZE+1);
	}
	
	/**
	 * This method moves the block in the given way
	 * @param x The distance that the block is traveling on the x axis
	 * @param y The distance that the block is traveling on the y axis
	 */
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}

	/**
	 * This method changes the color of the block to the given color
	 * @param color The new color of the block
	 */
	public void setColor(Color color){
		this.color = color;
	}

	/**
	 * This method changes the location of the block
	 * @param x The new location on the x-axis
	 * @param y The new location on the y-axis
	 */
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * This method changes the size of the block
	 * @param size The new size of the block
	 */
	public static void setSize(int size){
		if (size >= 1)
			SIZE = size;
		else
			SIZE = 1;
	}
	
	/**
	 * This method returns the color of the block
	 * @return The color of the block
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * This method returns the size of the block
	 * @return The size of the block
	 */
	public int getBlockSize(){
		return SIZE;
	}
	
	/**
	 * This method returns the x-position of the block (the upper-left corner)
	 * @return The x-position of the block
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * This method returns the y-position of the block (the upper-left corner)
	 * @return The y-position of the block
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * This is an inner class of actionListener causing the fade-out step by step when it is provoked by the timer of the Block.java class
	 */
	class FadeOutTheBlockListener implements ActionListener{

		public FadeOutTheBlockListener(){ }
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// If color has an alpha value (opacity) different than 0, then it is decremented by one
			// If it is already 0, the timer provoking this listener will be stopped
			if (color.getAlpha() != 0)
				color = new SColor(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha()-1);
			else
				timerForFadeAway.stop();
		}
		
	}
}
