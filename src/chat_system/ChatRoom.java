package chat_system;

import java.util.Set;
import java.util.TreeSet;

public class ChatRoom {
    private String roomName;
    private Set<String> users;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.users = new TreeSet<String>();
    }

    public String getRoomName() {
        return roomName;
    }

    public void addUser(String username) {
        this.users.add(username);
    }

    public void removeUser(String username) {
        if (this.users.contains(username))
            this.users.remove(username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.roomName).append("\n");
        if (!this.users.isEmpty()) {
            for (String s : users) {
                sb.append(s).append("\n");
            }
        } else {
            sb.append("EMPTY");
        }
        return sb.toString();
    }

    public boolean hasUser(String username) {
        return this.users.contains(username);
    }

    public int numUsers() {
        return this.users.size();
    }
}
