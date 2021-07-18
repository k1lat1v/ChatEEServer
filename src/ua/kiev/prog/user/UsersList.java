package ua.kiev.prog.user;

import java.util.ArrayList;
import java.util.List;

public class UsersList {

    private static final UsersList usrList = new UsersList();

    private final List<User> list = new ArrayList<>();

    public static UsersList getInstance() {
        return usrList;
    }

    private UsersList() {
    }

    public synchronized boolean add(User user) {
        if(list.contains(user)){
            return false;
        }
        list.add(user);
        return true;
    }

    public synchronized String getUsers(String except){
        StringBuilder sb = new StringBuilder();
        for(User user : list){
            if(user.getLogin().equals(except)){
                continue;
            }
            sb.append(user.getLogin());
            if(user.isOnline()){
                sb.append(" (online)");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public synchronized boolean contains(User user){
        return list.contains(user);
    }

    public synchronized User containsLogin(String login){
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getLogin().equals(login)){
                return list.get(i);
            }
        }
        return null;
    }

    public synchronized User get(User user){
        for(User usr : list){
            if(usr.equals(user)){
                return usr;
            }
        }
        return null;
    }
}
