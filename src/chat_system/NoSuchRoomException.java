package chat_system;

public class NoSuchRoomException extends Exception{
    public NoSuchRoomException(String roomName) {
        super(roomName);
    }
}
