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

	
	
	public static void createCommand(String commandFilename, int controllerID, int machineID, long orderID, String drinkName, String requestType, ArrayList<Option> options, ArrayList<String> commands, ArrayList<String> ingredients) throws IOException {
		JSONObject jo = new JSONObject();
		JSONObject command = new JSONObject();
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
	      
	      if(requestType.equals("Programmable")) {
	    	  JSONArray commandArray = new JSONArray();
	    	  JSONObject commandStep;
	    	  int commandAmount = commands.size();
	    	  for (int i = 0; i < commandAmount; i++) {
	    		  commandStep = new JSONObject();
	    		  commandStep.put("commandStep", commands.get(i));
	    		  commandStep.put("object", ingredients.get(i));
	    		  commandArray.add(commandStep);
	    	  }
	    	  command.put("recipe", commandArray);
	      }
	      
	      jo.put("command", command);
	      
	      FileWriter file = new FileWriter(commandFilename);
	      file.write(jo.toJSONString());
	      file.close();
	      
	}



	public static ControllerResponse parseControllerResponse(String responseFilename) throws FileNotFoundException, IOException, ParseException {
	    Object obj = new JSONParser().parse(new FileReader(responseFilename));
	    JSONObject jobj = (JSONObject) obj;
	    JSONObject response = (JSONObject) jobj.get("drinkresponse");
	    
	    long orderID = (long) response.get("orderID");
	    long status = (long) response.get("status");
	    String errordesc = "";
	    long errorcode = 0;
	    if (status != 0) {
	    	errordesc = (String) response.get("errordesc");
	    	errorcode = (long) response.get("errorcode");
	    }
	    
	    
	    
	    return new ControllerResponse(orderID, status, errordesc, errorcode);
	    
	}



	public static void createAppResponse(String responseFilename, int machineID, ControllerResponse cr) throws IOException {
		JSONObject jo = new JSONObject();
		JSONObject response = new JSONObject();
		
	      response.put("orderID", cr.getOrderID());
	      response.put("coffeeMachineID", machineID);
	      
	      long status = cr.getStatus();
	      response.put("status", status);
	      
	     if(status != 0) {
	    	 response.put("statusMessage", "Your coffee order has been cancelled.");
	    	 response.put("errorMessage", cr.getErrDesc());
	     }
	     else response.put("statusMessage", "Your coffee has been prepared with your desired options.");
	      
	      jo.put("userResponse", response);
	      
	      FileWriter file = new FileWriter(responseFilename);
	      file.write(jo.toJSONString());
	      file.close();
	}
	
	
	
	
	

}
