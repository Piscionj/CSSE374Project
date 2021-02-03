import orders.Address;
import orders.Controller;
import orders.OrderSystem;
import orders.strategies.AddressOrderStrategy;

public class Main {
	public static void main(String[] args) throws Exception {
		OrderSystem os = new OrderSystem();
		Address a = new Address("200 N Main", 47803);
		Controller cont = new Controller(73, true, 3, a);
		os.addController(cont);

		//tests basic communication between order system, controller, and app
		System.out.println("*ORDER INPUT TEST*");
		os.readOrder("order-input2.json", new AddressOrderStrategy());
		//should notify and select controller with ID 73
		
		System.out.println("\n*CONTROLLER COMMAND TEST*");
		os.sendCommand("controllerCommand.json", 12, "Americano", "Automated");
		//should send controllerCommand.json to controller with ID 73
		
		System.out.println("\n*CONTROLLER RESPONSE TEST*");
		os.readControllerResponse("controller-response2.json");
		//should process order 1 successfully
		os.readControllerResponse("controller-response3.json");
		//order 2 should have an error and cancel the order
		
		
		System.out.println("\n*RESPOND TO APP TEST*");
		os.readControllerResponse("controller-response2.json");
		os.sendResult("appResponse2.json", 12);
		//should process order 1 successfully, then create and send appResponse2.json to user
		os.readControllerResponse("controller-response3.json");
		os.sendResult("appResponse3.json", 34);
		//should recieve error for order 2, then create and send appResponse3.json to user
	}
}


