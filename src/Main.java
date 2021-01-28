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

		
		System.out.println("*ORDER INPUT TEST*");
		os.readOrder("order-input2.json", new AddressOrderStrategy());
		
		System.out.println("\n*CONTROLLER COMMAND TEST*");
		os.sendCommand("controllerCommand.json", 12, "Americano", "Automated");
		
		System.out.println("\n*CONTROLLER RESPONSE TEST*");
		os.readControllerResponse("controller-response2.json");
		os.readControllerResponse("controller-response3.json");
		
		System.out.println("\n*RESPOND TO APP TEST*");
		os.readControllerResponse("controller-response2.json");
		os.sendResult("appResponse2.json", 12);
		os.readControllerResponse("controller-response3.json");
		os.sendResult("appResponse3.json", 34);
	}
}


