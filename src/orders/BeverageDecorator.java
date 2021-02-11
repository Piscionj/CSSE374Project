package orders;

public interface BeverageDecorator {
	public void addCommand(BeverageDecorator command);
	public BeverageDecorator getNextCommand();
}
