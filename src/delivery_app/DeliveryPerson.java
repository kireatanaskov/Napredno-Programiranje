package delivery_app;

public class DeliveryPerson {
    private String id;
    private String name;
    private Location currentLocation;

    private int numDeliveries;
    private int moneyEarned;

    public DeliveryPerson(String id, String name, Location currentLocation) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.numDeliveries = 0;
        this.moneyEarned = 0;
    }

    public int compareDistanceToRestaurant(DeliveryPerson other, Location restaurantLocation) {
        int thisDistance = currentLocation.distance(restaurantLocation);
        int otherDistance = other.currentLocation.distance(restaurantLocation);
        if (thisDistance == otherDistance) {
            return Integer.compare(this.numDeliveries, other.numDeliveries);
        } else {
            return thisDistance - otherDistance;
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void processDelivery(int distance, Location location) {
        this.currentLocation = location;
        this.numDeliveries += 1;
        this.moneyEarned = moneyEarned + 90 + ((distance / 10) * 10);
    }

    public int getMoneyEarned() {
        return moneyEarned;
    }

    public float getAverage() {
        return this.numDeliveries > 0 ? this.moneyEarned / (float) this.numDeliveries : 0;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Total deliveries: %d Total delivery fee: %.2f Average delivery fee: %.2f",
                this.id,
                this.name,
                this.numDeliveries,
                this.moneyEarned * 1.0,
                this.getAverage());
    }
}
