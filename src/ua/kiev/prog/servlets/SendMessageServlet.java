package ua.kiev.prog.servlets;

import ua.kiev.prog.message.Message;
import ua.kiev.prog.message.MessageList;
import ua.kiev.prog.util.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessageServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Message.fromJSON(bufStr);
		if (msg != null) {
            msgList.add(msg);
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
	}
}
