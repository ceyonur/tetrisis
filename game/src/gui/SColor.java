package gui;

import java.awt.Color;
import java.awt.color.ColorSpace;

public class SColor extends Color {
	
	public static Color backgroundColor = new SColor(41,128,185);

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
