package orders;


public class Drink implements BeverageDecorator{

	private String name;
	private BeverageDecorator nextCommand = null;
	
	public Drink(String name) {
		this.name = name;
	}

	public void addCommand(BeverageDecorator command) {
		nextCommand = command;
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

	public String getName() {
		return name;
	}

	public BeverageDecorator getNextCommand() {
		return nextCommand;
	}
	
	

}
