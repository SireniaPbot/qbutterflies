package org.sirenia.scripts.qbutterflies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.Bot;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.client.Client;

import org.sirenia.scripts.qbutterflies.misc.Const;
import org.sirenia.scripts.qbutterflies.misc.Methods;
import org.sirenia.scripts.qbutterflies.misc.Methods.MouseTrail;
import org.sirenia.scripts.qbutterflies.misc.Vars;
import org.sirenia.scripts.qbutterflies.nodes.HuntNode;
import org.sirenia.scripts.qbutterflies.nodes.ResetNode;
import org.sirenia.scripts.qbutterflies.nodes.SnowUnstuckNode;
import org.sirenia.scripts.qbutterflies.nodes.SummoningNode;

@Manifest(authors = { "Sirenia" }, name = "qButterfliesDEV", description = "Catching Butteflies, start at the location you want to use", vip = false, version = 0.31, website = "http://www.powerbot.org/community/topic/939450-qbutterflies-catches-all-butterflies-barehanded-autosetup-great-exp-free/?p=11494275")
public class qButterflies extends ActiveScript implements PaintListener, MessageListener, MouseListener, ActionListener {

	private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
	private Tree jobContainer = null;
	private Client client = Bot.client();

	Timer t = new Timer(0);

	public synchronized final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!jobsCollection.contains(job)) {
				jobsCollection.add(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
	}

	public synchronized final void revoke(final Node... jobs) {
		for (final Node job : jobs) {
			if (jobsCollection.contains(job)) {
				jobsCollection.remove(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
	}

	public final void submit(final Job... jobs) {
		for (final Job job : jobs) {
			getContainer().submit(job);
		}
	}

	@Override
	public int loop() {
		if (jobContainer != null) {
			final Node job = jobContainer.state();
			if (job != null) {
				jobContainer.set(job);
				getContainer().submit(job);
				job.join();
			}
		}

		if (client != Bot.client()) {
			WidgetCache.purge();
			Bot.context().getEventManager().addListener(this);
			client = Bot.client();
		}
		return Random.nextInt(10, 50);
	}

	@Override
	public void onStart() {
		try {
			Vars.chatImage = Methods.getImage(Const.PAINT_URL);
			Vars.font = Methods.getFont(Const.FONT_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Vars.mouseTrail = new MouseTrail();
		Mouse.setSpeed(Speed.FAST);
		if (Camera.getPitch() < 80)
			Camera.setPitch(true);
		if (Const.WINTER_AREA.contains(Players.getLocal().getLocation())) {
			if (Vars.hunting_lvl > 84 && Vars.agility_lvl > 79) {
				provide(new SnowUnstuckNode());
				Vars.reset_tile = Const.WINTER_TILE;
				Vars.hunting_area = Const.WINTER_AREA;
				if (Vars.hunting_lvl > 89 && Vars.agility_lvl > 84) {
					Vars.butterfly = Const.WINTER_BUTTERFLIES;
				} else {
					Vars.butterfly = Const.SAPPHIRE_GLAC;
				}
			} else {
				Vars.lvlreq = true;
			}
		}
		if (Const.JUNGLE_AREA.contains(Players.getLocal().getLocation())) {
			if (Vars.hunting_lvl > 94 && Vars.agility_lvl > 89) {
				Vars.reset_tile = Const.JUNGLE_TILE;
				Vars.hunting_area = Const.JUNGLE_AREA;
				Vars.butterfly = Const.BLACK_WARLOCK;
			} else {
				Vars.lvlreq = true;
			}
		}
		if (Const.FOREST_AREA.contains(Players.getLocal().getLocation())) {
			if (Vars.hunting_lvl > 79 && Vars.agility_lvl > 74) {
				Vars.reset_tile = Const.FOREST_TILE;
				Vars.hunting_area = Const.FOREST_AREA;
				Vars.butterfly = Const.RUBY_HARVEST;
			} else {
				Vars.lvlreq = true;
			}
		}

		if (Vars.lvlreq) {
			stop();
		}

		Methods.setupPouches();

		provide(new HuntNode(), new ResetNode(), new SummoningNode());
	}

	@Override
	public void messageReceived(MessageEvent e) {
		if (e.getSender().equals("") && e.getMessage().toLowerCase().contains("catch"))
			Vars.caught++;

	}

	@Override
	public void onRepaint(Graphics g) {
		Vars.current_huntexp = Skills.getExperience(Skills.HUNTER);
		Vars.gained_huntexp = Vars.current_huntexp - Const.STARTING_HUNTEXP;
		Vars.current_agilexp = Skills.getExperience(Skills.AGILITY);
		Vars.gained_agilexp = Vars.current_agilexp - Const.STARTING_AGILEXP;
		Vars.total_exp = Vars.gained_agilexp + Vars.gained_huntexp;
		Vars.phour_huntexp = (int) (Vars.gained_huntexp * 3600000D / (System.currentTimeMillis() - Const.START_TIME));
		Vars.phour_agilexp = (int) (Vars.gained_agilexp * 3600000D / (System.currentTimeMillis() - Const.START_TIME));
		Vars.mouseTrail.add(Mouse.getLocation());

		if (Vars.show_paint) {
			if (Players.getLocal().getInteracting() != null) {
				NPC npc = (NPC) Players.getLocal().getInteracting();
				npc.getLocation().draw(g);

			}
			g.setColor(Color.black);
			g.drawLine(0, Mouse.getY(), Game.getDimensions().width, Mouse.getY());
			g.drawLine(0, Mouse.getY() + 1, Game.getDimensions().width, Mouse.getY() + 1);
			g.drawLine(Mouse.getX(), 0, Mouse.getX(), Game.getDimensions().height);
			g.drawLine(Mouse.getX() - 1, 0, Mouse.getX() - 1, Game.getDimensions().height);
			Vars.mouseTrail.draw(g);
			g.drawImage(Vars.chatImage, 0, 390, null);
			g.setFont(Vars.font);
			g.setColor(Color.LIGHT_GRAY);
			g.drawString(t.toElapsedString(), 141, 447);
			g.drawString("" + Vars.phour_huntexp, 157, 459);
			g.drawString("" + Vars.phour_agilexp, 167, 472);
			g.drawString("" + Vars.caught, 203, 486);
			g.drawString("" + Vars.total_exp, 185, 498);
			g.drawString("" + Vars.status, 635, 40);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Const.BOUNDS.contains(e.getPoint())) {
			Vars.show_paint = !Vars.show_paint;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}