package summerpiespro.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

import summerpiespro.methods.Inv;
import summerpiespro.util.Constants;
import summerpiespro.util.Vars;

public class PieSelectNode extends Node {

	@Override
	public boolean activate() {
		return Inv.containsCookingApple() && !Vars.bank && !Constants.MIX_INTERFACE.validate();
	}

	@Override
	public void execute() {
		if(!Bank.isOpen()) {
			if(Inv.strawberryCount() == 7) {
				Inventory.selectItem(Constants.PIE_SHELL);
				Task.sleep(250, 450);
				if(Inventory.isItemSelected()) {
					Inventory.getItem(Constants.STRAWBERRY).getWidgetChild().interact("Use");
					Task.sleep(700, 1000);
				}
			}
			else if(Inv.watermelonCount() == 7 && Inv.strawberryCount() == 0) {
				Inventory.selectItem(Constants.PART_SUMMER_PIE_I);
				Task.sleep(250, 450);
				if(Inventory.isItemSelected()) {
					Inventory.getItem(Constants.WATERMELON).getWidgetChild().interact("Use");
					Task.sleep(700, 1000);
				}
			}
			else if(Inv.cookingAppleCount() == 7 && Inv.watermelonCount() == 0) {
				Inventory.selectItem(Constants.PART_SUMMER_PIE_II);
				Task.sleep(200, 450);
				if(Inventory.isItemSelected()) {
					Inventory.getItem(Constants.COOKING_APPLE).getWidgetChild().interact("Use");
					Task.sleep(700, 1000);
				}
			}
		}
	}

}
