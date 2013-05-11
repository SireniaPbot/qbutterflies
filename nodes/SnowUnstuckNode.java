package org.sirenia.scripts.qbutterflies.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

import org.sirenia.scripts.qbutterflies.misc.Const;
import org.sirenia.scripts.qbutterflies.misc.Vars;

public class SnowUnstuckNode extends Node {

	@Override
	public boolean activate() {
		SceneObject steppes = SceneEntities.getNearest(Const.STEPPES);
		SceneObject canoe = SceneEntities.getNearest(Const.CANOE);
		return Players.getLocal().getPlane() == 1 && steppes != null && Players.getLocal().isIdle() || Players.getLocal().getLocation().getY() > 3922 && Players.getLocal().isIdle() && canoe != null;
	}

	@Override
	public void execute() {
		Vars.status = "UnstuckNode";
		SceneObject steppes = SceneEntities.getNearest(Const.STEPPES);
		SceneObject canoe = SceneEntities.getNearest(Const.CANOE);
		if (steppes != null) {
			if (!steppes.isOnScreen()) {
				Walking.walk(steppes);
			} else if (steppes.interact("Descend")) {
				sleep(700, 900);
			}
		} else if (canoe != null) {
			if (!canoe.isOnScreen())
				Walking.walk(canoe);
			else if (canoe.interact("Travel")) {
				Timer t = new Timer(10000);
				while (t.isRunning() && !Vars.hunting_area.contains(Players.getLocal().getLocation())) {
					sleep(200, 300);
				}
			}
		}
	}
}
