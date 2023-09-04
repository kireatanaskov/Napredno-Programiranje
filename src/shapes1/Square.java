package shapes1;

public class Square {
    private int size;

    public Square(int size) {
        this.size = size;
    }

    public int getPerimeter() {
        return 4 * size;
    }
}
