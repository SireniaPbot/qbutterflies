package org.sirenia.scripts.qbutterflies.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Summoning;
import org.powerbot.game.api.util.Timer;
import org.sirenia.scripts.qbutterflies.misc.Const;
import org.sirenia.scripts.qbutterflies.misc.Vars;

public class SummoningNode extends Node {

	@Override
	public boolean activate() {
		return Inventory.contains(Const.SUM_POT) && Inventory.contains(Vars.pouches) && !Summoning.isFamiliarSummoned() || Summoning.getPoints() < 1 && !Summoning.isFamiliarSummoned();
	}

	@Override
	public void execute() {
		Vars.status = "SummoningNode";
		Vars.needToSummon = true;
		Timer t = new Timer(2500);
		if (Summoning.getPoints() < 1 && !Summoning.isFamiliarSummoned()) {
			Vars.status = "Pot";
			Inventory.getItem(Const.SUM_POT).getWidgetChild().interact("Drink");
			t.reset();
			while (t.isRunning() && Summoning.getPoints() < 1) {
				sleep(200, 300);
			}
		}

		if (Summoning.getPoints() > 1 && !Summoning.isFamiliarSummoned()) {
			Vars.status = "Pouch";
			Inventory.getItem(Vars.pouches).getWidgetChild().interact("Summon");
			t.reset();
			while (t.isRunning() && !Summoning.isFamiliarSummoned()) {
				sleep(200, 300);
			}
		}
		Vars.needToSummon = false;
	}
}
