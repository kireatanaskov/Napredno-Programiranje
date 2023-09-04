package box_generic;

import java.util.stream.IntStream;

public class BoxTest {
    public static void main(String[] args) {
        Box<Circle> box = new Box<Circle>();

        IntStream.range(0, 100)
                .forEach(i -> box.addElement(new Circle()));

        IntStream.range(0, 103)
                .forEach(element -> System.out.println(box.drawElement()));
    }
}
