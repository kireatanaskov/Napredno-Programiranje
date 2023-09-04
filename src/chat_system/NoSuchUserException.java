package chat_system;

public class NoSuchUserException extends Exception
{
    public NoSuchUserException(String message) {
        super(message);
    }
}
