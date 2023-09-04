package canvas1;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
    private List<Shape> shapes;

    public Canvas() {
        this.shapes = new ArrayList<Shape>();
    }

    private int find(float weight) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).weight() < weight) {
                return i;
            }
        }
        return shapes.size();
    }

    public void add(String id, Color color, float radius) {
        Circle c = new Circle(id, color, radius);
        int index = find(c.weight());
        this.shapes.add(index, c);
    }

    public void add(String id, Color color, float width, float height) {
        Rectangle rect = new Rectangle(id, color, width, height);
        int index = find(rect.weight());
        this.shapes.add(index, rect);
    }

    void scale(String id, float scaleFactor) {
        Shape temp = null;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).id.equals(id)) {
                temp = shapes.remove(i);
                break;
            }
        }
        temp.scale(scaleFactor);
        int index = find(temp.weight());
        this.shapes.add(index, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : shapes) {
            sb.append(shape);
        }
        return sb.toString();
    }
}
