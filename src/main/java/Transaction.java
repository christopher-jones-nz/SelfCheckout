import java.util.ArrayList;
import java.util.List;

public class Transaction {
    List<Item> items;
    float total;

    Transaction() {
        items = new ArrayList<>();
        total = 0;
    }

    Transaction(Item item) {
        items = new ArrayList<>();
        total = 0;
        addItem(item);
    }

    void addItem(Item item) {
        items.add(item);
        total += item.getPrice();
    }
}
