package summerpiespro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;

import summerpiespro.nodes.BankNode;
import summerpiespro.nodes.PieMixNode;
import summerpiespro.nodes.PieSelectNode;
import summerpiespro.util.Constants;
import summerpiespro.util.Vars;

@Manifest(authors = {"afzal1996"}, description = "Makes summer pies", name = "SummerPiesPro", version = 1.0)
public class SummerPiesPro extends ActiveScript implements PaintListener {

	private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
	private Tree jobContainer = null;

	@Override
	public void onStart() {
		Vars.bankTime = System.currentTimeMillis();
		Vars.currentTime = System.currentTimeMillis();
		if(Inventory.getCount() > 1) Vars.bank = true;
		provide(new BankNode(), new PieSelectNode(), new PieMixNode());
		super.onStart();
	}
	
	@Override
	public int loop() {
		
		Vars.currentTime = System.currentTimeMillis();
		
		if (jobContainer != null) {
			final Node job = jobContainer.state();
			if (job != null) {
				jobContainer.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		
		if((Vars.currentTime - Vars.bankTime) > 180000 && !Bank.isOpen() && !Constants.MIX_INTERFACE.validate()) {
			Vars.status1 = "Took too long. banking.";
			Vars.bank = true;
			Vars.fails++;
		}
		
		return Random.nextInt(50, 100);
	}
	
	public final void provide(final Node... jobs) {
		for (final Node job : jobs) {
			if (!jobsCollection.contains(job)) {
				jobsCollection.add(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
	}

	@Override
	public void onRepaint(Graphics g) {
		Point p = Mouse.getLocation();
		g.setFont(Constants.font1);
		g.drawString(Vars.status1, 30, 10);
		g.setColor(Color.BLUE);
		g.drawLine(p.x - 8, p.y, p.x + 8, p.y);
		g.drawLine(p.x, p.y - 8, p.x, p.y + 8);
		g.drawString("Fails: " + Integer.toString(Vars.fails), 30, 25);
	}

}
