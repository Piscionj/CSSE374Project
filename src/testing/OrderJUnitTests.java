package testing;

import orders.Address;
import orders.Controller;
import orders.OrderSystem;
import orders.strategies.AddressOrderStrategy;
import orders.strategies.MachineTypeOrderStrategy;
import orders.strategies.QueueOrderStrategy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class OrderJUnitTests {


    @Test
    public void addressStrategyTest() throws Exception {
        OrderSystem os = new OrderSystem();
        Address a = new Address("200 N Main", 47803);
        Address a2 = new Address("201 N Main", 47803);
        Controller cont = new Controller(73, true, 3, a);
        Controller cont2 = new Controller(75, true, 3, a2);
        os.addController(cont);
        os.addController(cont2);
        // Check returned controller ID with expected
        assertEquals(73, os.readOrder("order-input2.json", new AddressOrderStrategy()));
    }

    @Test
    public void queueStrategyTest() throws Exception {
        OrderSystem os = new OrderSystem();
        Address a = new Address("30 W Main", 77903);
        Controller cont = new Controller(73, true, 3, a);
        Controller cont2 = new Controller(75, true, 2, a);
        os.addController(cont);
        os.addController(cont2);
        // Check returned controller ID with expected
        assertEquals(73, os.readOrder("order-input2.json", new QueueOrderStrategy()));
    }

    @Test
    public void machineStrategyTest() throws Exception {
        OrderSystem os = new OrderSystem();
        Address a = new Address("30 W Main", 77903);
        Controller cont = new Controller(73, true, 3, a);
        Controller cont2 = new Controller(75, false, 3, a);
        os.addController(cont);
        os.addController(cont2);
        // Check returned controller ID with expected (success)
        assertEquals(73, os.readOrder("order-input2.json", new MachineTypeOrderStrategy()));
    }

    @Test
    public void obersvableOrderReceivedTest() throws Exception {
        OrderSystem os = new OrderSystem();
        Address a = new Address("200 N Main", 47803);
        Controller cont = new Controller(73, true, 3, a);
        os.addController(cont);
        // Check that orders queued is as instantiated
        assertEquals(3, cont.getOrdersQueued());

        // Make order
        os.readOrder("order-input2.json", new MachineTypeOrderStrategy());

        // Check that controller received order as a subject
        assertEquals(4, cont.getOrdersQueued());
    }

}
