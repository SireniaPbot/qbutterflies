package org.sirenia.scripts.qbutterflies.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Timer;
import org.sirenia.scripts.qbutterflies.misc.Vars;

public class SnowUnstuckNode extends Node {

	@Override
	public boolean activate() {
		return Players.getLocal().getPlane() == 1 && Vars.steppes != null && Players.getLocal().isIdle() || Players.getLocal().getLocation().getY() > 3922 && Players.getLocal().isIdle() && Vars.canoe != null;
	}

	@Override
	public void execute() {
		Vars.status = "UnstuckNode";
		if (Vars.steppes != null) {
			if (!Vars.steppes.isOnScreen()) {
				Walking.walk(Vars.steppes);
			} else if (Vars.steppes.interact("Descend")) {
				Timer t = new Timer(3500);
				while (t.isRunning() && Players.getLocal().getPlane() == 1) {
					sleep(200, 300);
				}
			}
		} else if (Vars.canoe != null) {
			if (!Vars.canoe.isOnScreen())
				Walking.walk(Vars.canoe);
			else if (Vars.canoe.interact("Travel")) {
				Timer t = new Timer(10000);
				while (t.isRunning() && !Vars.hunting_area.contains(Players.getLocal().getLocation())) {
					sleep(200, 300);
				}
			}
		}
	}
}
