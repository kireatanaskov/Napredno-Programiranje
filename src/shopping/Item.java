package shopping;

public abstract class Item {
    Product product;
    double itemPrice;

    public Item(Product product) {
        this.product = product;
        this.itemPrice = 0;
    }

    public static Item createItem(String itemData) throws InvalidOperationException {
        String[] parts = itemData.split(";");
        String type = parts[0];
        int productID = Integer.parseInt(parts[1]);
        String productName = parts[2];
        double productPrice = Double.parseDouble(parts[3]);

        if (type.equals("WS")) {
            int quantity = Integer.parseInt(parts[4]);
            if (quantity == 0)
                throw new InvalidOperationException("The quantity of the product with id " + productID + "can not be 0.");
            return new WSItem(new Product(productID, productName, productPrice), quantity);
        } else {
            double quantity = Double.parseDouble(parts[4]);
            if (quantity == 0)
                throw new InvalidOperationException("The quantity of the product with id " + productID + "can not be 0.");
            return new PSItem(new Product(productID, productName, productPrice), quantity);
        }
    }

    public abstract double calculatePrice();

    public abstract double discountPrice();
}
