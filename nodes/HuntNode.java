package org.sirenia.scripts.qbutterflies.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.NPC;

import org.sirenia.scripts.qbutterflies.misc.Vars;

public class HuntNode extends Node {

	@Override
	public boolean activate() {
		NPC butterfly = NPCs.getNearest(Vars.butterfly);
		return !Vars.needToSummon && Vars.hunting_area.contains(Players.getLocal().getLocation()) && Players.getLocal().getInteracting() == null && Players.getLocal().isIdle() && butterfly != null;
	}

	@Override
	public void execute() {
		Vars.status = "HuntingNode";
		NPC butterfly = NPCs.getNearest(Vars.butterfly);
		if (!butterfly.isOnScreen()) {
			Walking.walk(butterfly);
		} else if (butterfly.validate() && butterfly.interact("Catch")) {
			sleep(500, 800);
		}
	}
}
