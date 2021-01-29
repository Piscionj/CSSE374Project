package orders;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer{

	private int id;
	private boolean isAdvancedType;
	private int ordersQueued;
	private Address address;
	
	public Controller(int id, boolean isAdvancedType, int ordersQueued, Address address) {
		this.id = id;
		this.isAdvancedType = isAdvancedType;
		this.address = address;
		this.ordersQueued = ordersQueued;
	}

	public void update(Observable arg0, Object arg1) {
		//controller is notified that order an has been recieved
		System.out.println("controller with ID " + id + " has been notified of new order");
		ordersQueued++;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean getIsAdvancedType() {
		return isAdvancedType;
	}
	
	public int getOrdersQueued() {
		return ordersQueued;
	}
	
	public Address getAddress() {
		return address;
	}
}
