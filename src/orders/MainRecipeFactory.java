package orders;

import java.util.ArrayList;

public class MainRecipeFactory implements DrinkFactory{
	
	MainRecipe mr;
	
	public MainRecipeFactory() {
		mr = new MainRecipe();
	}

	public BeverageDecorator makeDrink(String drinkName, String requestType, ArrayList<String> commands, ArrayList<String> ingredients) {
		BeverageDecorator beverage = new Drink(drinkName);
		if(requestType.equals("Programmable")) {
	  	  	int commandAmount = commands.size();
	  	  	BeverageDecorator lastBeverage;
	  	  	for (int i = commandAmount - 1; i >= 0; i--) {
	  	  		lastBeverage = beverage;
	  	  		beverage = new Command(commands.get(i), ingredients.get(i));
	  	  		beverage = mr.executeStep(beverage, lastBeverage);
	  	  	}
		}
		return beverage;
	}

}
