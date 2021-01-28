import orders.strategies.AddressOrderStrategy;

public class Main {
	public static void main(String[] args) throws Exception {
		OrderSystem os = new OrderSystem();
		
		os.readOrder("order-input2.json", new AddressOrderStrategy());
	}
}


