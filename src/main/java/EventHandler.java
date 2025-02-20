public class EventHandler {
    SelfCheckout instance;

    EventHandler(SelfCheckout instance) {
        this.instance = instance;
    }

    void handleScan(String itemId) {
        try {
            Item item = getItemFromDB(itemId);
            instance.addItem(item);
        } catch (Exception e) {
            instance.handleError(e);
        }
    }

    Item getItemFromDB(String itemId) {

    }
}
