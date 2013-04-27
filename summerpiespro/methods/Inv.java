package summerpiespro.methods;

import org.powerbot.game.api.methods.tab.Inventory;

import summerpiespro.util.Constants;

public class Inv {
	
	public static boolean containsCookingApple() {
		return Inventory.contains(Constants.COOKING_APPLE);
	}
	
	public static boolean containsMaxOfIngredient() {
		return strawberryCount() == 7 || watermelonCount() == 7 || cookingAppleCount() == 7;
	}
	
	public static boolean containsPies() {
		return Inventory.contains(Constants.PART_SUMMER_PIE_I) || 
		Inventory.contains(Constants.PART_SUMMER_PIE_II) || 
		Inventory.contains(Constants.RAW_SUMMER_PIE);
	}
	
	public static int pieShellCount() {
		return Inventory.getCount(Constants.PIE_SHELL);
	}
	
	public static int strawberryCount() {
		return Inventory.getCount(Constants.STRAWBERRY);
	}
	
	public static int watermelonCount() {
		return Inventory.getCount(Constants.WATERMELON);
	}
	
	public static int cookingAppleCount() {
		return Inventory.getCount(Constants.COOKING_APPLE);
	}
	
	public static int rawSummerPieCount() {
		return Inventory.getCount(Constants.RAW_SUMMER_PIE);
	}

}
