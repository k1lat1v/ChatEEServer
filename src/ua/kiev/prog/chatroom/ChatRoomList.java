package ua.kiev.prog.chatroom;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomList {
    private static final ChatRoomList crList = new ChatRoomList();

    private final List<ChatRoom> list = new ArrayList<>();

    public static ChatRoomList getInstance() {
        return crList;
    }

    private ChatRoomList() {
    }

    public synchronized boolean add(ChatRoom chatRoom) {
        if(list.contains(chatRoom)){
            return false;
        }
        list.add(chatRoom);
        return true;
    }
}
