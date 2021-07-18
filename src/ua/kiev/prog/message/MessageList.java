package ua.kiev.prog.message;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	private static final MessageList msgList = new MessageList();

    private final Gson gson;
	private final List<Message> list = new LinkedList<>();
	
	public static MessageList getInstance() {
		return msgList;
	}
  
  	private MessageList() {
		gson = new GsonBuilder().create();
	}
	
	public synchronized void add(Message m) {
		if(!list.contains(m)){
			list.add(m);
		}
	}
	
	public synchronized String toJSON(String login, int n) {
		if(n >= list.size()){
			return null;
		}
		return gson.toJson(new JsonMessages(list, login, n));
	}
}
