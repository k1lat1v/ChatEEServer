package ua.kiev.prog.servlets;

import ua.kiev.prog.user.User;
import ua.kiev.prog.user.UsersList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/chatroom"})
public class ChatRoomServlet extends HttpServlet {

    private static final UsersList usrList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");

        User user = usrList.containsLogin(login);
        System.out.println(user);
        if(user != null){
            OutputStream os = resp.getOutputStream();
            byte[] chats = user.getStringChats().getBytes(StandardCharsets.UTF_8);
            os.write(chats);
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
