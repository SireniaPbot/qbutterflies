package org.sirenia.scripts.qbutterflies.misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;

import javax.imageio.ImageIO;

public class Methods {

	// Creds to the Author! :) //
	public static class MouseTrail {
		private final int SIZE = 20;
		private final Point[] points;
		private int index;

		public MouseTrail() {
			points = new Point[SIZE];
			index = 0;
		}

		public void add(final Point p) {
			points[index++] = p;
			index %= SIZE;
		}

		public void draw(final Graphics g) {
			for (int i = index; i != (index == 0 ? SIZE - 1 : index - 1); i = (i + 1) % SIZE) {
				if (points[i] != null && points[(i + 1) % SIZE] != null) {
					g.setColor(Color.black);
					g.drawLine(points[i].x, points[i].y, points[(i + 1) % SIZE].x, points[(i + 1) % SIZE].y);

				}
			}
		}
	}

	public static void setupPouches() {
		if (Vars.summoning_lvl > 91)
			Vars.pouches = Const.POUCHES_92;
		if (Vars.summoning_lvl < 92)
			Vars.pouches = Const.POUCHES_71;
		if (Vars.summoning_lvl < 71)
			Vars.pouches = Const.POUCHES_57;
		if (Vars.summoning_lvl < 57)
			Vars.pouches = Const.EMPTY;
	}

	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Font getFont(String url) {
		try {
			URL fontUrl = new URL(Const.FONT_URL);
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
			font = font.deriveFont(Font.PLAIN, 11);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			return font;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String format(final long ms) {
		int seconds = (int) ms / 1000;
		int minutes = seconds / 60;
		seconds -= minutes * 60;
		int hours = minutes / 60;
		minutes -= hours * 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
