package canvas;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Canvas {
    private Set<IShape> shapes;
    private Map<String, Set<IShape>> shapesByUser;

    public Canvas() {
        this.shapes = new TreeSet<IShape>(Comparator.comparing(IShape::getArea));
        this.shapesByUser = new TreeMap<String, Set<IShape>>();
    }

    public void readShapes(InputStream is) throws InvalidDimensionException {
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("END"))
                break;
            try {
                String shapeId = ShapeFactory.extractId(line);
                IShape newShape = ShapeFactory.createShape(line);
                this.shapes.add(newShape);
                shapesByUser.putIfAbsent(shapeId, new TreeSet<>(Comparator.comparing(IShape::getPerimeter)));
                shapesByUser.get(shapeId).add(newShape);
            } catch (InvalidIdException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void scaleShapes(String userID, double coef) {
        shapesByUser.getOrDefault(userID, new HashSet<>()).forEach(iShape -> iShape.scale(coef));
    }

    public void printAllShapes(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        shapes.forEach(pw::println);
        pw.flush();
    }

    public void printByUserId(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        Comparator<Map.Entry<String, Set<IShape>>> entryComparator = Comparator.comparing(entry -> entry.getValue().size());

        shapesByUser.entrySet().stream()
                .sorted(entryComparator.reversed().thenComparing(entry -> entry.getValue().stream().mapToDouble(IShape::getArea).sum()))
                .forEach(entry -> {
                    pw.println("Shapes of user: " + entry.getKey());
                    entry.getValue().forEach(pw::println);
                });

        pw.flush();
    }

    public void statistics(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        DoubleSummaryStatistics dss = shapes.stream().mapToDouble(IShape::getArea).summaryStatistics();
        pw.println(String.format("count: %d\nsum: %.2f\nmin: %.2f\naverage: %.2f\nmax: %.2f", dss.getCount(), dss.getSum(), dss.getMin(), dss.getAverage(), dss.getMax()));
        pw.flush();
    }
}
