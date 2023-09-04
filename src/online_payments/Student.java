package online_payments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private String id;
    private List<Item> items;

    public Student(String id) {
        this.id = id;
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int totalNet() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    public int fee() {
        int total = totalNet();
        double fee = Math.round(total * 0.0114);
        if (fee > 300) {
            fee = 300;
        }
        if (fee < 3) {
            fee = 3;
        }
        return (int) fee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Student: %s Net: %d Fee: %d Total: %d\n", id, totalNet(), fee(), totalNet() + fee()));
        sb.append("Items:\n");
        items.sort(Comparator.comparing(Item::getPrice).thenComparing(Item::getItemName).reversed());
        List<String> outputs = new ArrayList<String>();
        for (int i = 0; i < items.size(); i++) {
            outputs.add(String.format("%d. %s", i + 1, items.get(i).toString()));
        }
        sb.append(outputs.stream().collect(Collectors.joining("\n")));
        return sb.toString();
    }
}
