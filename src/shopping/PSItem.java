package shopping;

public class PSItem extends Item{
    private double quantity;

    public PSItem(Product product, double quantity) {
        super(product);
        this.quantity = quantity;
        this.itemPrice = 0;
    }

    @Override
    public String toString() {
        return String.format("%d - %.2f", this.product.getProductID(), this.calculatePrice());
    }

    @Override
    public double calculatePrice() {
        this.itemPrice = (product.getProductPrice() / 1000.0) * quantity;
        return this.itemPrice;
    }

    @Override
    public double discountPrice() {
        double currentPrice = this.calculatePrice();

        double percent = (currentPrice / 100) * 10;

        return currentPrice - (currentPrice - percent);
    }
}
