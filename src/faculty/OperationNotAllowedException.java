package faculty;

public class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
