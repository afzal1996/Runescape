package summerpiespro.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;

import summerpiespro.util.Constants;

public class PieMixNode extends Node {

	@Override
	public boolean activate() {
		return Constants.MIX_INTERFACE.validate();
	}

	@Override
	public void execute() {
		Constants.MIX_BUTTON.click(true);
		Task.sleep(700, 1000);
	}

}
