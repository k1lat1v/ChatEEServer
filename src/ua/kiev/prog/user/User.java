package ua.kiev.prog.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.chatroom.ChatRoom;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String login;
    private String password;
    private boolean online;
    private List<ChatRoom> userChats = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String toJSON(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    public void addChatRoom(ChatRoom chatRoom){
        if(!userChats.contains(chatRoom)){
            userChats.add(chatRoom);
        }
    }

    public String getStringChats(){
        StringBuilder sb = new StringBuilder();
        for(ChatRoom cr : userChats){
            sb.append(cr);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public List<ChatRoom> getUserChats() {
        return userChats;
    }

    public void setUserChats(List<ChatRoom> userChats) {
        this.userChats = userChats;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", online=" + online +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }
        User usr = (User) obj;

        return this.login.equals(usr.login);
    }
}
