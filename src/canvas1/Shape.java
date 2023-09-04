package canvas1;

public abstract class Shape implements Scalable, Stackable{
    protected String id;
    protected Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }
}
