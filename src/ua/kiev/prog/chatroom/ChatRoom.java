package ua.kiev.prog.chatroom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.user.User;

import java.util.Arrays;

public class ChatRoom {
    private String name;
    private String[] users;

    public ChatRoom(String name, String... users) {
        this.name = name;
        this.users = users;
        Arrays.sort(users);
    }

    public static ChatRoom fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, ChatRoom.class);
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ChatRoom)) {
            return false;
        }
        ChatRoom cr = (ChatRoom) obj;

        return Arrays.equals(this.users, cr.users);
    }

    @Override
    public String toString() {
        return name + " " + Arrays.toString(users);
    }
}
