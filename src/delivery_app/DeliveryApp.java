package delivery_app;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DeliveryApp {
    private String name;
    private Map<String, User> users;
    private Map<String, Restaurant> restaurants;
    private Map<String, DeliveryPerson> deliveryPersons;

    public DeliveryApp(String name) {
        this.name = name;
        this.users = new HashMap<String, User>();
        this.restaurants = new HashMap<String, Restaurant>();
        this.deliveryPersons = new HashMap<String, DeliveryPerson>();
    }

    public void registerDeliveryPerson(String id, String name, Location currentLocation) {
        this.deliveryPersons.putIfAbsent(id, new DeliveryPerson(id, name, currentLocation));
    }

    public void addRestaurant(String id, String name, Location location) {
        this.restaurants.putIfAbsent(id, new Restaurant(id, name, location));
    }

    public void addUser(String id, String name) {
        this.users.putIfAbsent(id, new User(id, name));
    }

    public void addAddress(String id, String addressName, Location location) {
        this.users.get(id).addAddress(addressName, location);
    }

    public void orderFood(String usedId, String userAddressName, String restaurantId, float cost) {
        User user = this.users.get(usedId);
        Address address = user.getAddress(userAddressName);
        Restaurant restaurant = this.restaurants.get(restaurantId);

        DeliveryPerson deliveryPerson = deliveryPersons.values().stream()
                .min((l, r) -> l.compareDistanceToRestaurant(r, restaurant.getLocation()))
                .get();

        int distance = deliveryPerson.getCurrentLocation().distance(restaurant.getLocation());

        deliveryPerson.processDelivery(distance, address.location);
        restaurant.processDelivery(cost);
        user.processDelivery(cost);
    }

    public void printUsers() {
        this.users.values()
                .stream()
                .sorted(Comparator.comparing(User::getMoneySpent)
                        .thenComparing(User::getId).reversed())
                .forEach(System.out::println);
    }

    public void printRestaurants() {
        this.restaurants.values()
                .stream()
                .sorted(Comparator.comparing(Restaurant::averageCost)
                        .thenComparing(Restaurant::getId).reversed())
                .forEach(System.out::println);
    }

    public void printDeliveryPeople() {
        this.deliveryPersons.values()
                .stream()
                .sorted(Comparator.comparing(DeliveryPerson::getMoneyEarned)
                        .thenComparing(DeliveryPerson::getId).reversed())
                .forEach(System.out::println);
    }
}
