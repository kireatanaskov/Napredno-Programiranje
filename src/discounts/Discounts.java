package discounts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Discounts {
    private List<Store> stores;

    public Discounts() {
        this.stores = new ArrayList<Store>();
    }

    public int readStores(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        this.stores = bufferedReader.lines()
                .map(Store::createStore)
                .collect(Collectors.toList());

        return stores.size();
    }

    public List<Store> byAverageDiscount() {
        return stores.stream()
                .sorted(Comparator.comparing(Store::averageDiscount).reversed()
                        .thenComparing(Store::getStoreName))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount() {
        return this.stores.stream()
                .sorted(Comparator.comparing(Store::totalDiscount)
                        .thenComparing(Store::getStoreName))
                .limit(3)
                .collect(Collectors.toList());
    }
}
