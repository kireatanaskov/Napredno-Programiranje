package box_generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//V - value
//E - element
//T - type
public class Box<E extends Drawable> {
    private List<E> elements;
    private static Random random = new Random();

    public Box() {
        elements = new ArrayList<E>();
    }

    public void addElement(E element) {
        elements.add(element);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public E drawElement() {
        if (!isEmpty()) {
//            int index = random.nextInt(elements.size());
//            E temp = elements.get(index);
//            elements.remove(index);
//            return temp;

            return elements.remove(random.nextInt(elements.size()));
        }
        return null;
    }
}
