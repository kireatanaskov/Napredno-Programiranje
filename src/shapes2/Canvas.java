package shapes2;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Canvas {
    private String id;
    private List<Shape> shapes;

    public Canvas(String id, List<Shape> shapes) {
        this.id = id;
        this.shapes = shapes;
    }

    public static Canvas createCanvas(String line, double maxArea) throws IrregularCanvasException {
        String[] parts = line.split("\\s+");
        String id = parts[0];
        List<Shape> shapes1 = new ArrayList<Shape>();
        for (int i=1; i<parts.length; i+=2) {
            Shape s = Shape.createShape(Integer.parseInt(parts[i + 1]), parts[i].charAt(0), maxArea);
            if (s.area() > maxArea)
                throw new IrregularCanvasException(id, maxArea);
            shapes1.add(s);
        }
        return new Canvas(id, shapes1);
    }

    private int getCirclesCount() {
        return (int) shapes.stream().filter(s -> s.getType().equals(Type.CIRCLE)).count();
    }

    public double getSum() {
        return shapes.stream()
                .mapToDouble(Shape::area)
                .sum();
    }

    @Override
    public String toString() {
        DoubleSummaryStatistics dss = shapes.stream()
                .mapToDouble(Shape::area)
                .summaryStatistics();

        return String.format("%s %d %d %d %.2f %.2f %.2f",
                this.id,
                this.shapes.size(),
                this.getCirclesCount(),
                this.shapes.size() - this.getCirclesCount(),
                dss.getMin(),
                dss.getMax(),
                dss.getAverage());
    }
}
