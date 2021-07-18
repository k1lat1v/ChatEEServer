package ua.kiev.prog.servlets;

import ua.kiev.prog.user.User;
import ua.kiev.prog.user.UsersList;
import ua.kiev.prog.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final UsersList usrList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String strBuf = new String(buf, StandardCharsets.UTF_8);

        User user = User.fromJSON(strBuf);
        OutputStream os = resp.getOutputStream();
        if(usrList.contains(user)){
            if(usrList.get(user).getPassword().equals(user.getPassword())) {
                usrList.get(user).setOnline(true);
                os.write("Successfully logged in!".getBytes(StandardCharsets.UTF_8));
            }else{
                os.write("Wrong password!".getBytes(StandardCharsets.UTF_8));
            }
        }else{
            os.write("User is not registered!".getBytes(StandardCharsets.UTF_8));
        }
    }
}
