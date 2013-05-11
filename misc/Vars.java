package org.sirenia.scripts.qbutterflies.misc;

import java.awt.Font;
import java.awt.Image;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

import org.sirenia.scripts.qbutterflies.misc.Methods.MouseTrail;

public class Vars {

	// global //
	public static int[] butterfly = { 0 };
	public static Tile reset_tile;
	public static Area hunting_area;
	public static int[] pouches;
	public static boolean lvlreq = false;
	public static boolean needToSummon = false;

	public static Image chatImage;
	public static Font font;
	public static MouseTrail mouseTrail;

	public static int summoning_lvl = Skills.getRealLevel(Skills.SUMMONING);
	public static int hunting_lvl = Skills.getRealLevel(Skills.HUNTER);
	public static int agility_lvl = Skills.getRealLevel(Skills.AGILITY);
	public static int gained_huntexp;
	public static int current_huntexp;
	public static int phour_huntexp;
	public static int gained_agilexp;
	public static int current_agilexp;
	public static int phour_agilexp;
	public static int total_exp;

	public static int caught;
	public static boolean show_paint = true;
	public static String status = "Starting up";
}
