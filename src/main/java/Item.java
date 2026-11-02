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
    }

    String getName() {
        return name;
    }

    float getPrice() {
        return price;
    }

    /**
     * Return display string representing this item.
     * Format is "NAME ($PRICE)".
     * E.g. "Apple ($1.50)"
     */
    String toDisplayString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" ($");
        sb.append(this.price);
        sb.append(")");

        return sb.toString();
    }
}
