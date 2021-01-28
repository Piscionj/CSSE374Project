package orders;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

	
	
	public static void createCommand(int controllerID, int machineID, long orderID, String drinkName, String requestType, ArrayList<Option> options) throws IOException {
		JSONObject jo = new JSONObject();
		JSONObject command = new JSONObject();
		JSONObject optiono = new JSONObject();
		JSONArray optionArray = new JSONArray();
		JSONObject option;
		
	      for(Option o : options) {
	    	  option = new JSONObject();
	    	  option.put("Name", o.getName());
	    	  option.put("qty", o.getQuantity());
	    	  optionArray.add(option);  
	      }
	      
	      command.put("options", optionArray);
	      
	      command.put("controllerID", controllerID);
	      command.put("coffeeMachineID", machineID);
	      command.put("orderID", orderID);
	      command.put("drinkName", drinkName);
	      command.put("requestType", requestType);
	      
	      jo.put("command", command);
	      
	      FileWriter file = new FileWriter("controllerCommand.json");
	      file.write(jo.toJSONString());
	      file.close();

	      System.out.println("sending controllerCommand.json to controller with ID " + controllerID);
		
	}
	
	
	
	
	

}
