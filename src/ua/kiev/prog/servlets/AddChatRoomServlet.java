package ua.kiev.prog.servlets;

import ua.kiev.prog.chatroom.ChatRoom;
import ua.kiev.prog.user.User;
import ua.kiev.prog.user.UsersList;
import ua.kiev.prog.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/addchatroom"})
public class AddChatRoomServlet extends HttpServlet {

    private static final UsersList usrList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String strBuf = new String(buf, StandardCharsets.UTF_8);

        ChatRoom chatRoom = ChatRoom.fromJSON(strBuf);
        System.out.println("chatroom received :" + chatRoom);

        if(chatRoom != null){
            for(String login : chatRoom.getUsers()){
                User user = usrList.containsLogin(login);
                usrList.get(user).addChatRoom(chatRoom);
                System.out.println("YES");
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
