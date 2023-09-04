package shapes2;

public class Square extends Shape{
    public Square(int size) {
        super(size);
    }

    @Override
    double area() {
        return size * size;
    }

    @Override
    Type getType() {
        return Type.SQUARE;
    }
}
