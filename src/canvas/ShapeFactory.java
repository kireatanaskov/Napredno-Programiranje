package canvas;

public class ShapeFactory {
    private static boolean checkId(String id) {
        if (id.length() != 6)
            return false;

        for (char c : id.toCharArray()) {
            if (!Character.isLetterOrDigit(c))
                return false;
        }

        return true;
    }

    public static IShape createShape(String line) throws InvalidDimensionException {
        String[] parts = line.split("\\s+");
        int type = Integer.parseInt(parts[0]);
        double firstDimension = Double.parseDouble(parts[2]);
        if (firstDimension == 0.0) {
            throw new InvalidDimensionException();
        }
        if (type == 1) {
            return new Circle(firstDimension);
        } else if (type == 2) {
            return new Square(firstDimension);
        } else {
            double secondDimension = Double.parseDouble(parts[3]);
            if (secondDimension == 0.0)
                throw new InvalidDimensionException();
            return new Rectangle(firstDimension, secondDimension);
        }
    }

    public static String extractId(String line) throws InvalidIdException {
        String[] parts = line.split("\\s+");
        String id = parts[1];
        if (!checkId(id))
            throw new InvalidIdException(id);
        return id;
    }
}
