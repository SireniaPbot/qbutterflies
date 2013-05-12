package org.sirenia.scripts.qbutterflies.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.sirenia.scripts.qbutterflies.misc.Vars;

public class ResetNode extends Node {

	@Override
	public boolean activate() {
		return Vars.reset_tile != null && Vars.butterflai == null || !Vars.hunting_area.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		Walking.findPath(Vars.reset_tile).traverse();
		sleep(200, 300);
	}
}
