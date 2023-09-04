package cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarCollection {
    private List<Car> cars;

    public CarCollection() {
        this.cars = new ArrayList<Car>();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void sortByPrice(boolean ascending) {
        if (ascending)
            cars.sort(Comparator.comparing(Car::getPrice).thenComparing(Car::getPower));
        else
            cars.sort(Comparator.comparing(Car::getPrice).thenComparing(Car::getPower).reversed());
    }

    public List<Car> filterByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    public List<Car> getList() {
        return this.cars;
    }
}
