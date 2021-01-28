package orders;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import orders.strategies.OrderStrategy;

public class JsonParser {
	
	public static Order parseOrder(String orderFilename) throws FileNotFoundException, IOException, ParseException{

		
	    StringBuilder sb = new StringBuilder();
	    
	    
		JSONParser parser = new JSONParser();
		
	    Object obj = new JSONParser().parse(new FileReader(orderFilename));
	    JSONObject jobj = (JSONObject)obj;
	    
	    JSONObject ordero = (JSONObject) jobj.get("order");
	    long orderID = (long) ordero.get("orderID");
	    
	    JSONObject addresso = (JSONObject) ordero.get("address");
	    String street = (String) addresso.get("street");
	    long zip = (long) addresso.get("ZIP");
	    
	    String type = (String) ordero.get("drink");
	    JSONArray options = (JSONArray) ordero.get("condiments");
	    
	    ArrayList<Option> optionsList = new ArrayList<Option>();
	    String name;
	    long qty;
	    
	    for (Object o : options) {
	    	JSONObject jo = (JSONObject) o;
	    	name = (String) jo.get("name");
	    	qty = (long) jo.get("qty");
	    	optionsList.add(new Option(name, qty));
	    }
	    
	    Address address = new Address(street, zip);
	    
	    
		return new Order(orderID, address, type, optionsList);
	}
	
	
	
	
	

}
