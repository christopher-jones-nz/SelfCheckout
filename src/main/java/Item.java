public class Item {
    final String id;
    final String name;
    final float price;

    Item(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    float getPrice() {
        return price;
    }
}
