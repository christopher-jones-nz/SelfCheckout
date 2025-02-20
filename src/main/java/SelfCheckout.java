public class SelfCheckout {
    EventHandler handler;
    Transaction transaction;
    UserInterface userInterface;

    SelfCheckout() {
        handler = new EventHandler(this);
        userInterface = new UserInterface();
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
}
