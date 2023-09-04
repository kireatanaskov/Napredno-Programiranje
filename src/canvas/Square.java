package canvas;

public class Square implements IShape{
    double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    public double getArea() {
        return Math.pow(a, 2);
    }

    @Override
    public double getPerimeter() {
        return 4 * a;
    }

    @Override
    public void scale(double coef) {
        a *= coef;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f", a, getArea(), getPerimeter());
    }
}