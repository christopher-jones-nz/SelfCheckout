import java.util.HashMap;
import java.util.Map;

public class FakeDB {
    Map<String, String[]> data;

    FakeDB() {
        data = new HashMap<>();
        generateFakeData();
    }

    void generateFakeData() {
        data.put("0001", new String[]{"0001", "apple", "5.00", "apple.svg"});
        data.put("0002", new String[]{"0002", "banana", "2.50", "banana.svg"});
        data.put("0003", new String[]{"0003", "bread", "3.75", "bread.svg"});
        data.put("0004", new String[]{"0004", "milk", "4.20", "milk.svg"});
        data.put("0005", new String[]{"0005", "eggs", "6.30", "eggs.svg"});
        data.put("0006", new String[]{"0006", "chicken", "10.00", "chicken.svg"});
        data.put("0007", new String[]{"0007", "rice", "7.25", "rice.svg"});
        data.put("0008", new String[]{"0008", "cheese", "8.40", "cheese.svg"});
        data.put("0009", new String[]{"0009", "carrots", "3.10", "carrots.svg"});
        data.put("0010", new String[]{"0010", "oranges", "4.50", "oranges.svg"});
    }

    String[] getItemData(String itemId) {
        return data.get(itemId);
    }

    Map<String, String[]> getAllItemsData() {
        return data;
    }

}
