package orders;

import java.util.ArrayList;

public interface DrinkFactory {
	BeverageDecorator makeDrink(String drinkName, String requestType, ArrayList<String> commands, ArrayList<String> ingredients);
}
