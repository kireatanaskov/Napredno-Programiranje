package generic_map;

import java.util.Map;
import java.util.TreeMap;

public class MapOps {
    public static <K extends Comparable<K>, V> Map<K, V> merge(Map<K, V> left, Map<K, V> right, MergeStrategy<V> strategy) {
        Map<K, V> result = new TreeMap<>(left);
        right.forEach((k, v) -> result.merge(k, v, strategy::merge));

        return result;
    }
}
