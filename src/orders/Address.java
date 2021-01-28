package orders;

public class Address {
	private String address;
	private long zip;
	
	public Address(String address, long zip) {
		this.address = address;
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public long getZip() {
		return zip;
	}
	
	
}
