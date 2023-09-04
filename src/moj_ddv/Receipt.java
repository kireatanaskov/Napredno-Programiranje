package moj_ddv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Receipt{
    private String id;
    private List<Item> items;

    public Receipt(String id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String[] parts = line.split("\\s+");
        List<Item> itemsList = new ArrayList<Item>();

        String id = parts[0];
        Item item = new Item();
        for (int i = 1; i < parts.length; i++) {
            if (i % 2 == 0) {
                item.setType(TaxType.valueOf(parts[i]));
                itemsList.add(item);
                item = new Item();
            } else {
                item.setPrice(Integer.parseInt(parts[i]));
            }
        }

        int totalAmount = itemsList.stream()
                .mapToInt(Item::getPrice)
                .sum();

        if (totalAmount > 30000) {
            throw new AmountNotAllowedException(totalAmount);
        }

        return new Receipt(id, itemsList);
    }

    public int getSum() {
        return items.stream()
                .mapToInt(Item::getPrice)
                .sum();
    }

    public double getTaxReturn() {
        return items.stream()
                .mapToDouble(Item::getTaxReturn)
                .sum();
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return String.format("%10s\t%10d\t%10.5f", this.id, this.getSum(), this.getTaxReturn());
    }
}
