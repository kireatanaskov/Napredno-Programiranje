package double_matrix;

public class InsufficientElementsException extends Exception{
    public InsufficientElementsException() {
        super("Insufficient number of elements");
    }
}
