package generic_collection;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GenericCollection<T extends Comparable<T> & IHasTimestamp> {
    private Map<String, Set<T>> genericItems;

    public GenericCollection() {
        this.genericItems = new HashMap<String, Set<T>>();
    }

    public void addGenericItem(String category, T element) {
        this.genericItems.putIfAbsent(category, new TreeSet<T>());
        this.genericItems.get(category).add(element);
    }

    public Collection<T> findAllBetween (LocalDateTime from, LocalDateTime to) {
        return this.genericItems.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(item -> item.getTimestamp().isAfter(from) && item.getTimestamp().isBefore(to))
                .collect(Collectors.toCollection(() -> new TreeSet<T>(Comparator.reverseOrder())));
    }

    public Collection<T> itemsFromCategories(List<String> categories) {
        return this.genericItems.keySet().stream()
                .filter(categories::contains)
                .flatMap(category -> genericItems.get(category).stream())
                .collect(Collectors.toCollection(() -> new TreeSet<T>(Comparator.reverseOrder())));
    }

    public Map<String, Set<T>> byMonthAndDay() {
        return genericItems.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        element -> String.format("%02d-%02d", element.getTimestamp().getMonthValue(), element.getTimestamp().getDayOfMonth()),
                        TreeMap::new,
                        Collectors.toCollection(() -> new TreeSet<T>(Comparator.reverseOrder()))
                ));
    }

    public Map<Integer, Long> countByYear() {
        return this.genericItems.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        element -> element.getTimestamp().getYear(),
                        TreeMap::new,
                        Collectors.counting()
                ));
    }
}
