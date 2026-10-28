public class Item {
    final String id;
    final String name;
    final float price;

    Item(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = Float.parseFloat(price);
    }

    String getName() {
        return name;
    }

    float getPrice() {
        return price;
    }
}
