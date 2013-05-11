package org.sirenia.scripts.qbutterflies.misc;

import java.awt.Rectangle;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Const {

	public static final int ARCTIC_BEAR = 6839;
	public static final int GRAAHK = 7363;
	public static final int KYATT = 7365;
	public static final int LARUPIA = 7337;
	public static final int WOLPERTINGER = 6869;
	public static final int[] FAMILARS = { ARCTIC_BEAR, GRAAHK, KYATT, LARUPIA, WOLPERTINGER };

	public static final int SUM_POT1 = 12146;
	public static final int SUM_POT2 = 12144;
	public static final int SUM_POT3 = 12142;
	public static final int SUM_POT4 = 12140;
	public static final int[] SUM_POT = { SUM_POT1, SUM_POT2, SUM_POT3, SUM_POT4 };
	public static final int EMPTY_VIAL = 229;

	public static final int GHAARK_POUCH = 12810;
	public static final int KYATT_POUCH = 12812;
	public static final int LARUPIA_POUCH = 12784;
	public static final int ARCTICBEAR_POUCH = 12057;
	public static final int WOLPERTINGER_POUCH = 12089;
	public static final int[] POUCHES_92 = { GHAARK_POUCH, KYATT_POUCH, LARUPIA_POUCH, ARCTICBEAR_POUCH, WOLPERTINGER_POUCH };
	public static final int[] POUCHES_71 = { GHAARK_POUCH, KYATT_POUCH, LARUPIA_POUCH, ARCTICBEAR_POUCH };
	public static final int[] POUCHES_57 = { GHAARK_POUCH, KYATT_POUCH, LARUPIA_POUCH };
	public static final int[] EMPTY = { 0 };

	// snow area //
	public static final Area WINTER_AREA = new Area(new Tile[] { new Tile(2701, 3811, 0), new Tile(2687, 3806, 0), new Tile(2686, 3784, 0), new Tile(2699, 3775, 0), new Tile(2704, 3760, 0), new Tile(2738, 3760, 0), new Tile(2745, 3769, 0), new Tile(2750, 3785, 0), new Tile(2744, 3791, 0), new Tile(2741, 3798, 0), new Tile(2736, 3801, 0), new Tile(2731, 3804, 0), new Tile(2723, 3801, 0), new Tile(2717, 3800, 0), new Tile(2711, 3801, 0), new Tile(2709, 3807, 0), new Tile(2705, 3810, 0) });
	public static final Tile WINTER_TILE = new Tile(2715, 3786, 0);

	public static final int SNOWY_KNIGHT = 5083;
	public static final int[] SAPPHIRE_GLAC = { 5084 };
	public static final int[] WINTER_BUTTERFLIES = { SNOWY_KNIGHT, 5084 };
	public static final int STEPPES = 19691;
	public static final int CANOE = 42858;

	// jungle area //
	public static final Area JUNGLE_AREA = new Area(new Tile[] { new Tile(2530, 2939, 0), new Tile(2495, 2892, 0), new Tile(2521, 2879, 0), new Tile(2576, 2886, 0), new Tile(2574, 2937, 0) });
	public static final Tile JUNGLE_TILE = new Tile(2542, 2912, 0);
	public static final int[] BLACK_WARLOCK = { 5082 };

	// forest area //
	public static final Area FOREST_AREA = new Area(new Tile[] { new Tile(2299, 3611, 0), new Tile(2283, 3577, 0), new Tile(2289, 3541, 0), new Tile(2368, 3541, 0), new Tile(2371, 3569, 0), new Tile(2360, 3574, 0), new Tile(2360, 3597, 0), new Tile(2334, 3619, 0) });
	public static final Tile FOREST_TILE = new Tile(2323, 3587, 0);
	public static final int[] RUBY_HARVEST = { 5085 };

	// Paint //
	public static final String PAINT_URL = "http://i.imgur.com/PH0uXeo.jpg";
	public static final String FONT_URL = "http://dl.dropbox.com/u/32071079/erke/AustereBlackCapsSSK-Regular.ttf";
	
	public static final int STARTING_HUNTEXP = Skills.getExperience(Skills.HUNTER);
	public static final int STARTING_AGILEXP = Skills.getExperience(Skills.AGILITY);
	
	public static final long START_TIME = System.currentTimeMillis();

	public static final Rectangle BOUNDS = new Rectangle(0, 390, 515, 135);
}
