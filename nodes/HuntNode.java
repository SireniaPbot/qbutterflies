package org.sirenia.scripts.qbutterflies.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Timer;
import org.sirenia.scripts.qbutterflies.misc.Vars;

public class HuntNode extends Node {

	@Override
	public boolean activate() {
		return !Vars.needToSummon && Vars.hunting_area.contains(Players.getLocal().getLocation()) && Players.getLocal().getInteracting() == null && Players.getLocal().isIdle() && Vars.butterflai != null;
	}

	@Override
	public void execute() {
		Vars.status = "HuntingNode";
		if (!Vars.butterflai.isOnScreen()) {
			Walking.walk(Vars.butterflai);
		} else if (Vars.butterflai.validate() && Vars.butterflai.interact("Catch")) {
			Timer t = new Timer(1000);
			while (t.isRunning() && Players.getLocal().getInteracting() == null) {
				sleep(200, 300);
			}
		}
	}
}
