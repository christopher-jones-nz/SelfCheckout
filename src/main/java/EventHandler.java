public class EventHandler {
    SelfCheckout instance;
    FakeDB db;

    EventHandler(SelfCheckout instance) {
        this.instance = instance;
        db = new FakeDB();
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
        String[] item = db.getItemData(itemId);
        return new Item(item[0], item[1], Float.parseFloat(item[2]), item[3]);
    }
}
