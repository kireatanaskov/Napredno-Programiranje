package online_payments;

public class Item {
    private String index;
    private String itemName;
    private int price;

    public Item(String index, String itemName, int price) {
        this.index = index;
        this.itemName = itemName;
        this.price = price;
    }

    public static Item create(String line) {
        String[] parts = line.split(";");
        return new Item(parts[0], parts[1], Integer.parseInt(parts[2]));
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("%s %d", itemName, price);
    }
}
