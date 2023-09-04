package delivery_app;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String name;

    private Map<String, Address> addresses;

    private float moneySpent;
    private int totalOrders;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.addresses = new HashMap<String, Address>();
        this.moneySpent = 0;
        this.totalOrders = 0;
    }

    public void addAddress(String name, Location location) {
        this.addresses.put(name, new Address(name, location));
    }

    public Address getAddress(String userAddressName) {
        return this.addresses.get(userAddressName);
    }

    public void processDelivery(float cost) {
        this.moneySpent += cost;
        this.totalOrders += 1;
    }

    public float getMoneySpent() {
        return moneySpent;
    }

    public float getAverage() {
        return this.totalOrders > 0 ? this.moneySpent / this.totalOrders : 0;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Total orders: %d Total amount spent: %.2f Average amount spent: %.2f",
                this.id,
                this.name,
                this.totalOrders,
                this.moneySpent,
                this.getAverage());
    }
}
