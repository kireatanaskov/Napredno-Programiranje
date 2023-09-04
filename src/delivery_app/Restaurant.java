package delivery_app;

public class Restaurant {
    private String id;
    private String name;
    private Location location;

    private int numDeliveries;
    private float moneyEarned;

    public Restaurant(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.numDeliveries = 0;
        this.moneyEarned = 0;
    }

    public Location getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public void processDelivery(float cost) {
        this.numDeliveries += 1;
        this.moneyEarned += cost;
    }

    public float averageCost() {
        return this.numDeliveries > 0 ? moneyEarned / numDeliveries : 0;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Total orders: %d Total amount earned: %.2f Average amount earned: %.2f",
                this.id,
                this.name,
                this.numDeliveries,
                this.moneyEarned,
                this.averageCost());
    }
}
