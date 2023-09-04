package shapes2;

public abstract class Shape {
    int size;

    public Shape(int size) {
        this.size = size;
    }

    abstract double area();

    abstract Type getType();

    public static Shape createShape(int size, char type, double maxArea) {
        switch (type) {
            case 'S': return new Square(size);
            case 'C': return new Circle(size);
            default: return null;
        }
    }
}
