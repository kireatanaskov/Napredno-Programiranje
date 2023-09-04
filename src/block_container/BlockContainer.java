package block_container;

import java.util.*;

public class BlockContainer<T extends Comparable<T>> {
    private List<Set<T>> elements;
    private int numBlocks;

    public BlockContainer(int n) {
        this.numBlocks = n;
        this.elements = new ArrayList<Set<T>>();
    }

    public void add(T a) {
        if (elements.size() == 0) {
            Set<T> s = new TreeSet<T>();
            s.add(a);
            elements.add(s);
        } else {
            Set<T> s = elements.get(elements.size() - 1);
            if (s.size() < numBlocks) {
                s.add(a);
            } else {
                s = new TreeSet<T>();
                s.add(a);
                elements.add(s);
            }
        }
    }

    public boolean remove(T a) {
        boolean res = false;
        if (elements.size() > 0) {
            Set<T> s = elements.get(elements.size() - 1);
            res = s.remove(a);
            if (s.size() == 0) {
                elements.remove(elements.size() - 1);
            }
        }
        return res;
    }

    public void sort() {
        ArrayList<T> all = new ArrayList<T>();
        for (int i = 0; i < elements.size(); i++) {
            Iterator<T> it = elements.get(i).iterator();
            while (it.hasNext()) {
                all.add(it.next());
            }
        }
        Collections.sort(all);
        elements = new ArrayList<Set<T>>();
        for (T element : all) {
            add(element);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i).toString());
            if (i < elements.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
