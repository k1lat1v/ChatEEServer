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

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private UsersList usrList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = User.fromJSON(bufStr);
        if(user != null){
            OutputStream os = resp.getOutputStream();
            if(usrList.add(user)){
                os.write("User successfully registered!".getBytes(StandardCharsets.UTF_8));
            }else{
                os.write("User already registered!".getBytes(StandardCharsets.UTF_8));
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }


}
