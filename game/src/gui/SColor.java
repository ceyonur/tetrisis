package gui;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Random;

public class SColor extends Color {
	
	public static SColor backgroundColor = new SColor(41,128,185);
	public static SColor boardColor = new SColor(236, 240, 241);
	public static SColor boardPauseColor = new SColor(52, 73, 94);
	public static SColor sidePanelColor = new SColor(41,128,185);
	public static SColor buttonColor = new SColor(52, 152, 219);
	
	
	public static SColor getRandomPieceColor() {
		
		ArrayList<SColor> pieceColorSet = new ArrayList<SColor>();
		pieceColorSet.add(new SColor(241, 196, 15));
		pieceColorSet.add(new SColor(230, 126, 34));
		pieceColorSet.add(new SColor(231, 76, 60));
		pieceColorSet.add(new SColor(46, 204, 113));
		pieceColorSet.add(new SColor(52, 152, 219));
		pieceColorSet.add(new SColor(155, 89, 182));
		
		Random rgen = new Random(System.currentTimeMillis());
		
		return pieceColorSet.get(rgen.nextInt(pieceColorSet.size()));
	}

	public SColor(int rgb) {
		super(rgb);
		// TODO Auto-generated constructor stub
	}

	public SColor(int rgba, boolean hasalpha) {
		super(rgba, hasalpha);
		// TODO Auto-generated constructor stub
	}

	public SColor(int r, int g, int b) {
		super(r, g, b);
		// TODO Auto-generated constructor stub
	}

	public SColor(float r, float g, float b) {
		super(r, g, b);
		// TODO Auto-generated constructor stub
	}

	public SColor(ColorSpace arg0, float[] arg1, float arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public SColor(int r, int g, int b, int a) {
		super(r, g, b, a);
		// TODO Auto-generated constructor stub
	}

	public SColor(float r, float g, float b, float a) {
		super(r, g, b, a);
		// TODO Auto-generated constructor stub
	}

}
