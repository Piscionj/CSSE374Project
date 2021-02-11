package orders;

public class MainRecipe implements AbstractRecipe{

	public BeverageDecorator executeStep(BeverageDecorator beverage, BeverageDecorator lastBeverage) {
		beverage.addCommand(lastBeverage);
		return beverage;
	}

}
