package shopping;

public class Product {
    int productID;
    String productName;
    double productPrice;

    public Product(int productID, String productName, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
