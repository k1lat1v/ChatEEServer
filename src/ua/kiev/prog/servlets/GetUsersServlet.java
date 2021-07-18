package ua.kiev.prog.servlets;

import ua.kiev.prog.user.UsersList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/users"})
public class GetUsersServlet extends HttpServlet {

    private static final UsersList usrList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String exceptUser = req.getParameter("except");
        OutputStream os = resp.getOutputStream();
        byte[] buf = usrList.getUsers(exceptUser).getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }
}
