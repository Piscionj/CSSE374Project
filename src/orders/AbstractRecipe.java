package orders;

public interface AbstractRecipe {
	BeverageDecorator executeStep(BeverageDecorator beverage, BeverageDecorator lastBeverage);
}
