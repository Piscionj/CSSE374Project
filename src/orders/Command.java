package orders;

public class Command implements BeverageDecorator{
	private String action;
	private String ingredient;
	private BeverageDecorator nextCommand;

	public Command(String action, String ingredient) {
		this.action = action;
		this.ingredient = ingredient;
	}

	@Override
	public void addCommand(BeverageDecorator command) {
		nextCommand = command;
	}

	public String getAction() {
		return action;
	}


	public String getIngredient() {
		return ingredient;
	}


	public BeverageDecorator getNextCommand() {
		return nextCommand;
	}



}
