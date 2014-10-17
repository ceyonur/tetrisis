package gui;

import game.Engine;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	private PlayGUI play;
	
	public GUI() {
		play = new PlayGUI();
		setContentPane(play);
	}
	
	public void setEngine(Engine engine){
		play.setEngine(engine);
	}
}
