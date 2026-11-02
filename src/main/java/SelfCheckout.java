import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelfCheckout {
    EventHandler handler;
    Transaction transaction;
    UserInterface userInterface;
    FakeDB database;

    SelfCheckout() {
        handler = new EventHandler(this);
        userInterface = new UserInterface(this);
        database = new FakeDB();
    }

    void addItem(Item item) {
        if (transaction == null) {
            transaction = new Transaction(item);
        } else {
            transaction.addItem(item);
        }
    }

    void handleScan(String itemId) {
        handler.handleScan(itemId);
    }

    void handleError(Exception e) {

    }

    Transaction getTransaction() {
        return transaction;
    }

    void cancelTransaction() {
        transaction = new Transaction();
    }

    /**
     * Builds and returns a list of all food items from the fake database.
     */
    List<Item> getAllFoodOptions() {
        List<Item> result = new ArrayList<>();
        
        Map<String,String[]> foodData = database.getAllItemsData();
        for (var item : foodData.entrySet()) {
            String[] value = item.getValue();
            Item food = new Item(value[0],value[1],Float.parseFloat(value[2]),value[3]);
            result.add(food);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Starting SelfCheckout...");
        new SelfCheckout();
    }
}
