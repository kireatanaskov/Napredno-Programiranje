package canvas;

public class InvalidIdException extends Exception{
    public InvalidIdException(String id) {
        super(String.format("ID %s is not valid", id));
    }
}
