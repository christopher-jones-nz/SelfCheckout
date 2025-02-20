public class SelfCheckout {
    EventHandler handler;
    Transaction transaction;
    FakeDB db;

    SelfCheckout() {
        handler = new EventHandler(this);
        db = new FakeDB();
    }

    void addItem(Item item) {
        if (transaction == null) {
            transaction = new Transaction(item);
        } else {
            transaction.addItem(item);
        }
    }
}
