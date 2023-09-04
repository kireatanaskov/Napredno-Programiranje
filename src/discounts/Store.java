package discounts;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {
    private String storeName;
    private List<Product> products;

    public Store(String storeName, List<Product> products) {
        this.storeName = storeName;
        this.products = products;
    }

    public static Store createStore(String line) {
        String[] parts = line.split("\\s+");
        String name = parts[0];
        List<Product> productList = Arrays.stream(parts)
                .skip(1)
                .map(Product::ofString)
                .collect(Collectors.toList());

        return new Store(name, productList);
    }

    public String getStoreName() {
        return storeName;
    }

    public double averageDiscount() {
        return products.stream()
                .mapToDouble(Product::discount)
                .average().orElse(0);
    }

    public int totalDiscount() {
        return products.stream()
                .mapToInt(Product::absoluteDiscount)
                .sum();
    }

    @Override
    public String toString() {
        String productsStr = products.stream()
                .sorted(Comparator.comparing(Product::discount)
                        .thenComparing(Product::absoluteDiscount).reversed())
                .map(Product::toString)
                .collect(Collectors.joining("\n"));

        double rounded = Math.round(averageDiscount() * 10) / 10.;

        return String.format("%s\nAverage discount: %.1f%%\nTotal discount: %d\n%s",
                this.storeName,
                rounded,
                totalDiscount(),
                productsStr);
    }
}