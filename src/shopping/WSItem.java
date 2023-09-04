package shopping;

public class WSItem extends Item{
    private int quantity;

    public WSItem(Product product, int quantity) {
        super(product);
        this.quantity = quantity;
        this.itemPrice = 0;
    }

    @Override
    public String toString() {
        return String.format("%d - %.2f", this.product.getProductID(), this.itemPrice);
    }

    @Override
    public double calculatePrice() {
        this.itemPrice =  product.getProductPrice() * quantity;
        return this.itemPrice;
    }

    @Override
    public double discountPrice() {
        double currentPrice = this.calculatePrice();

        double percent = (currentPrice / 100) * 10;

        return currentPrice - (currentPrice - percent);
    }
}
