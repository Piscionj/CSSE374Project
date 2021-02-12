package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import orders.Address;
import orders.Controller;
import orders.ControllerResponse;
import orders.OrderSystem;
import orders.strategies.AddressOrderStrategy;

public class SendCommandTests {

    @Test
    public void sendCommandTest() throws Exception {
    	OrderSystem os = new OrderSystem();
		Address a = new Address("200 N Main", 47803);
		Controller cont = new Controller(73, true, 3, a);
		os.addController(cont);
		os.readOrder("order-input2.json", new AddressOrderStrategy());
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("steam");
		commands.add("add");
		commands.add("top");
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("milk");
		ingredients.add("espresso");
		ingredients.add("whipped cream");
		os.sendCommand("controllerCommand.json", 12, "Americano", "Programmable", commands, ingredients);
	    
		String output = readFile("controllerCommand.json",StandardCharsets.US_ASCII);
		
        assertEquals(output, "{\"command\":{\"controllerID\":73,\"requestType\":\"Programmable\",\"orderID\":1,\"options\":[{\"qty\":1,\"name\":\"Sugar\"},{\"qty\":2,\"name\":\"Cream\"}],\"recipe\":[{\"commandStep\":\"steam\",\"object\":\"milk\"},{\"commandStep\":\"add\",\"object\":\"espresso\"},{\"commandStep\":\"top\",\"object\":\"whipped cream\"}],\"coffeeMachineID\":12,\"drinkName\":\"Americano\"}}");
    }
	
    @Test
    public void readControllerResponseTest() throws Exception {
    	OrderSystem os = new OrderSystem();
		Address a = new Address("200 N Main", 47803);
		Controller cont = new Controller(73, true, 3, a);
		os.addController(cont);
		os.readOrder("order-input2.json", new AddressOrderStrategy());
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("steam");
		commands.add("add");
		commands.add("top");
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("milk");
		ingredients.add("espresso");
		ingredients.add("whipped cream");
		os.sendCommand("controllerCommand.json", 12, "Americano", "Programmable", commands, ingredients);
		
		os.readControllerResponse("controller-response2.json");
		
		ControllerResponse output = os.getCr();
		ControllerResponse exptected = new ControllerResponse(1,0,"",0);
		
		assertEquals(output.getStatus(),exptected.getStatus());
		assertEquals(output.getErrDesc(),exptected.getErrDesc());
		assertEquals(output.getErrCode(),exptected.getErrCode());
		assertEquals(output.getOrderID(),exptected.getOrderID());

		os.readControllerResponse("controller-response3.json");
		
		output = os.getCr();
		exptected = new ControllerResponse(2,1,"Out of milk, drink cancelled",2);
		
		assertEquals(output.getStatus(),exptected.getStatus());
		assertEquals(output.getErrDesc(),exptected.getErrDesc());
		assertEquals(output.getErrCode(),exptected.getErrCode());
		assertEquals(output.getOrderID(),exptected.getOrderID());		
    }
    
    @Test
    public void sendResultTest() throws Exception {
		OrderSystem os = new OrderSystem();
		Address a = new Address("200 N Main", 47803);
		Controller cont = new Controller(73, true, 3, a);
		os.addController(cont);

		os.readOrder("order-input2.json", new AddressOrderStrategy());
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("steam");
		commands.add("add");
		commands.add("top");
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("milk");
		ingredients.add("espresso");
		ingredients.add("whipped cream");
		os.sendCommand("controllerCommand.json", 12, "Americano", "Programmable", commands, ingredients);
		
		os.readControllerResponse("controller-response2.json");
		os.readControllerResponse("controller-response3.json");
		
		
		
		os.readControllerResponse("controller-response2.json");
		os.sendResult("appResponse2.json", 12);
		
		String output = readFile("appResponse2.json",StandardCharsets.US_ASCII);
		assertEquals(output,"{\"userResponse\":{\"orderID\":1,\"coffeeMachineID\":12,\"statusMessage\":\"Your coffee has been prepared with your desired options.\",\"status\":0}}");
		//should process order 1 successfully, then create and send appResponse2.json to user
		os.readControllerResponse("controller-response3.json");
		os.sendResult("appResponse3.json", 34);
		
		output = readFile("appResponse3.json",StandardCharsets.US_ASCII);
		assertEquals(output,"{\"userResponse\":{\"orderID\":2,\"errorMessage\":\"Out of milk, drink cancelled\",\"coffeeMachineID\":34,\"statusMessage\":\"Your coffee order has been cancelled.\",\"status\":1}}");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    static String readFile(String path, Charset encoding)
    		  throws IOException
    		{
    		  byte[] encoded = Files.readAllBytes(Paths.get(path));
    		  return new String(encoded, encoding);
    		}

	
	
	
	
}
