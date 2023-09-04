package min_max;

import java.util.Scanner;

class MinMax<T extends Comparable<T>>  {
    private T min;
    private T max;
    private int total;
    private int maxCount;
    private int minCount;

    public MinMax() {
        total = 0;
        maxCount = 0;
        minCount = 0;
    }

    public void update(T element) {
        if (total == 0) {
            min = element;
            max = element;
        }
        ++total;
        if (element.compareTo(min) < 0) {
            // ako noviot element e pomal od dosegasniot minimum, noviot element e minimum i minCount se stava na 1
            minCount = 1;
            min = element;
        } else {
            if (element.compareTo(min) == 0) {
                // ako novito element e ednakov so dosegasniot element, se zgolemuva brojot na min elementi
                minCount++;
            }
        }
        if (element.compareTo(max) > 0) {
            // ako noviot element e pogolem od maximumot, noviot element e maximum
            maxCount = 1;
            max = element;
        } else {
            if (element.compareTo(max) == 0) {
                // ako se ednakvi se zgolemuva brojot na makismumi
                maxCount++;
            }
        }
    }

    public T min() {
        return min;
    }

    public T max() {
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        T min = min();
        T max = max();
        sb.append(String.format("%s %s %d\n", min, max, total - (maxCount + minCount)));

        return sb.toString();
    }
}

public class MinAndMaxTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}
