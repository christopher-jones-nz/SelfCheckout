import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSelfCheckout {
    SelfCheckout sc;

    @Before
    public void setUp() {
        sc = new SelfCheckout();
        sc.handleScan("0004");
        sc.handleScan("0006");
    }

    @Test
    public void testSelfCheckout() {
        // data.put("0004", new String[]{"0004", "milk", "4.20"});
        Transaction transaction = sc.getTransaction();
        List<Item> items = transaction.getItems();
        assertEquals("0004", items.get(0).id);
        assertEquals("milk", items.get(0).name);
        assertEquals(4.20, items.get(0).price, 0.1);
    }
}
