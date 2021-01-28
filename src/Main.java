import orders.Address;
import orders.Controller;
import orders.OrderSystem;
import orders.strategies.AddressOrderStrategy;

public class Main {
	public static void main(String[] args) throws Exception {
		OrderSystem os = new OrderSystem();
		
		//testing reading order input
		Address a = new Address("200 N Main", 47803);
		Controller cont = new Controller(73, true, 3, a);
		os.addController(cont);
		os.readOrder("order-input2.json", new AddressOrderStrategy());
		
		//testing sending command to controller
		os.sendCommand(12, "Americano", "Automated");
	}
}


