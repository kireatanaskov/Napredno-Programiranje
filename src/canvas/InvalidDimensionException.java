package canvas;

public class InvalidDimensionException extends Exception{
    public InvalidDimensionException() {
        super("Dimension 0 is not allowed!");
    }
}
