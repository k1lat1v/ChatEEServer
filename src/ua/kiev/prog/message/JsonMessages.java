package ua.kiev.prog.message;

import ua.kiev.prog.chatroom.ChatRoom;
import ua.kiev.prog.user.User;
import ua.kiev.prog.user.UsersList;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {

    private final UsersList usrList = UsersList.getInstance();
    private final List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, String login, int fromIndex) {
        User user = usrList.containsLogin(login);
        for (int i = fromIndex; i < sourceList.size(); i++){
            String receiver = sourceList.get(i).getTo();
            if(receiver.equals(login)){
                list.add(sourceList.get(i));
            }else{
                List<ChatRoom> chatList = user.getUserChats();
                for(int j=0; j < chatList.size(); j++){
                    if(receiver.equals(chatList.get(j).getName())){
                        list.add(sourceList.get(i));
                    }
                }
            }
        }
    }
}
