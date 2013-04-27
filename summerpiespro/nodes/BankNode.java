package summerpiespro.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import summerpiespro.methods.Inv;
import summerpiespro.util.Constants;
import summerpiespro.util.Vars;

public class BankNode extends Node {

	@Override
	public boolean activate() {
		return !Inv.containsCookingApple() || Vars.bank;
	}

	@Override
	public void execute() {
		NPC[] bankers = NPCs.getLoaded(bankerFilter);
		
		for(int i = 0; i < bankers.length; i++) {
			Vars.banker = NPCs.getNearest(bankers[i].getId());
			if(Vars.banker != null) break;
		}
		
		if(!Bank.isOpen() && Vars.banker != null) {
			if(Vars.banker.isOnScreen()) { 
				Vars.banker.interact("Bank");
				Task.sleep(1000 * (int)Calculations.distanceTo(Vars.banker), 2000 * (int)Calculations.distanceTo(Vars.banker));
			}
			else Walking.findPath(Vars.banker);
		}
		
		else if(Bank.isOpen()) {
			if(Inv.containsPies()) Bank.depositInventory();
			else {
				if(Inv.pieShellCount() != 7) {
					if(Inv.pieShellCount() > 7) { 
						Bank.deposit(Constants.PIE_SHELL, Inv.pieShellCount() - 7);
					}
					else if(Inv.pieShellCount() < 7){
						Bank.withdraw(Constants.PIE_SHELL, 7 - Inv.pieShellCount());
					}
				}
				else if(Inv.strawberryCount() != 7) {
					if(Inv.strawberryCount() > 7) { 
						Bank.deposit(Constants.STRAWBERRY, Inv.strawberryCount() - 7);
					}
					else if(Inv.strawberryCount() < 7){
						Bank.withdraw(Constants.STRAWBERRY, 7 - Inv.strawberryCount());
					}
				}
				else if(Inv.watermelonCount() != 7) {
					if(Inv.watermelonCount() > 7) { 
						Bank.deposit(Constants.WATERMELON, Inv.watermelonCount() - 7);
					}
					else if(Inv.watermelonCount() < 7){
						Bank.withdraw(Constants.WATERMELON, 7 - Inv.watermelonCount());
					}
				}
				else if(Inv.cookingAppleCount() != 7) {
					if(Inv.cookingAppleCount() > 7) { 
						Bank.deposit(Constants.COOKING_APPLE, Inv.cookingAppleCount() - 7);
					}
					else if(Inv.cookingAppleCount() < 7){
						Bank.withdraw(Constants.COOKING_APPLE, 7 - Inv.cookingAppleCount());
					}
					Task.sleep(1000, 2000);
					Vars.bankTime = System.currentTimeMillis();
					Vars.bank = false;
					Bank.close();
				}
				else {
					Task.sleep(700, 1000);
					Vars.bankTime = System.currentTimeMillis();
					Vars.bank = false;
					Bank.close();
				}
			}
		}
		
	}
	
	Filter<NPC> bankerFilter = new Filter<NPC>() {
		@Override
		public boolean accept(NPC n) {
			return n.getName().equals("Banker");		
		}
	};

}
