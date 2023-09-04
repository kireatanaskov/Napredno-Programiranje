package discounts;

public class Product {
    private int price;
    private int discountedPrice;

    public Product(int discountedPrice, int price) {
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public static Product ofString(String product) {
        String[] parts = product.split(":");
        return new Product(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public int discount() {
        return (price - discountedPrice) * 100 / price;
    }

    public int absoluteDiscount() {
        return price - discountedPrice;
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", this.discount(), discountedPrice, price);
    }
}
