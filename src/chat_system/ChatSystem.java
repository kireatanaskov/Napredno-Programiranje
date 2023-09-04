package chat_system;

import java.util.*;
import java.util.stream.Collectors;

public class ChatSystem {
    private TreeMap<String, ChatRoom> rooms;
    private TreeSet<String> users;

    public ChatSystem() {
        this.rooms = new TreeMap<String, ChatRoom>();
        this.users = new TreeSet<String>();
    }

    public void addRoom(String roomName) {
        this.rooms.put(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName) {
        this.rooms.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if (!this.rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        else return new ChatRoom(this.rooms.get(roomName).getRoomName());
    }

    public void register(String userName) {
        this.users.add(userName);
        List<ChatRoom> rooms1 = this.rooms.values()
                .stream()
                .sorted(Comparator.comparing(ChatRoom::numUsers)
                        .thenComparing(ChatRoom::getRoomName))
                .toList();

        rooms1.get(0).addUser(userName);
    }

    public void registerAndJoin(String userName, String roomName) {
        this.users.add(userName);
        this.rooms.get(roomName).addUser(userName);
    }

    public void joinRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if (!this.users.contains(userName))
            throw new NoSuchUserException(userName);
        if (!this.rooms.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        else
            this.rooms.get(roomName).addUser(userName);
    }

    public void leaveRoom(String username, String roomname) throws NoSuchUserException, NoSuchRoomException {
        if (!this.users.contains(username))
            throw new NoSuchUserException(username);
        if (!this.rooms.containsKey(roomname))
            throw new NoSuchRoomException(roomname);
        else
            this.rooms.get(roomname).removeUser(username);
    }

    public void followFriend(String username, String friend_username) {
        List<ChatRoom> chatRooms = this.rooms.values()
                .stream()
                .filter(chatRoom -> chatRoom.hasUser(friend_username))
                .toList();

        for (ChatRoom cr : chatRooms) {
            cr.addUser(username);
        }
    }
}
