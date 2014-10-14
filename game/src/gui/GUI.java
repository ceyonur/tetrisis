package gui;

import game.Engine;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {

	public GUI() {
		super();
		hide();
		new MenuGUI();
	}
}
