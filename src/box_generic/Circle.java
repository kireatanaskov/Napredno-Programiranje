package box_generic;

public class Circle implements Drawable<Circle> {
    @Override
    public Circle draw() {
        return this;
    }

    @Override
    public String toString() {
        return "Iscrtuvam krug";
    }
}
