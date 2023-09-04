package shapes2;

public class Circle extends Shape{
    public Circle(int size) {
        super(size);
    }

    @Override
    double area() {
        return size * size * Math.PI;
    }

    @Override
    Type getType() {
        return Type.CIRCLE;
    }
}
