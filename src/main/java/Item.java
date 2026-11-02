public class Item {
    final String id;
    final String name;
    final float price;
    final String image;

    Item(String id, String name, float price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.price = price;
    }

    String getName() {
        return name;
    }

    float getPrice() {
        return price;
    }
}
