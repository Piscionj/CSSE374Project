package orders.strategies;

import orders.Address;

public class AddressOrderStrategy implements OrderStrategy {

	private Address address;

	public AddressOrderStrategy(Address address) {
		this.address = address;
	}
	
	public void makeOrder() {
		
	}

}
