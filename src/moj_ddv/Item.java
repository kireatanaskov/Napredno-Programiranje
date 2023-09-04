package moj_ddv;

public class Item {
    private int price;
    private TaxType type;

    public Item(int price, TaxType type) {
        this.price = price;
        this.type = type;
    }

    public Item() {}

    public void setType(TaxType type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public TaxType getType() {
        return type;
    }

    public double getTaxReturn() {
        switch (type) {
            case A:
                return price * 0.18 * 0.15;
            case B:
                return price * 0.05 * 0.15;
            default:
                return 0;
        }
    }
}
