package generic_map;

public interface MergeStrategy<T> {
    T merge(T left, T right);
}
