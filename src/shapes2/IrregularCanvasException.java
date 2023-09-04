package shapes2;

public class IrregularCanvasException extends Exception{
    public IrregularCanvasException(String id, double area) {
        super(String.format("Canvas %s has a shape with area larger than %.2f",
                id, area));
    }
}
