import java.io.FileReader;
import java.util.ArrayList;

import orders.Order;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OrderSystem implements SystemSubject {
	ArrayList<Order> orders;
	
	public OrderSystem() throws Exception{
		Object obj = new JSONParser().parse(new FileReader("order-input.json"));
		JSONObject jo = (JSONObject) obj;
		
	}

	public void registerObserver() {
		
	}

	public void removeObserver() {
		
	}

	public void notifyObservers() {
		
	}
}
