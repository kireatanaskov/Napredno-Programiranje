package shopping;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<Item>();
    }

    public void addItem(String itemData) throws InvalidOperationException {
        this.items.add(Item.createItem(itemData));
    }

    public void printShoppingCart(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        this.items.stream()
                .sorted(Comparator.comparing(Item::calculatePrice).reversed())
                .forEach(pw::println);

        pw.flush();
    }

    public void blackFridayOffer(List<Integer> discountItems, OutputStream os) throws InvalidOperationException {
        PrintWriter pw = new PrintWriter(os);

        if (discountItems.isEmpty())
            throw new InvalidOperationException("There are no products with discount");

        List<Item> blackFridayItems = new ArrayList<Item>();

        for (Item item : items) {
            if (discountItems.contains(item.product.getProductID())) {
                blackFridayItems.add(item);
            }
        }

        blackFridayItems.stream()
                .forEach(item -> System.out.printf("%d - %.2f\n", item.product.getProductID(), item.discountPrice()));

        pw.flush();
    }
}
