package beans;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Chat chat;
	private String message;
	private User sendBy;
	
	public Message() {}
	
	public Message(int idChat, Chat chat, String message, User sendBy) {
		this.id = idChat;
		this.chat = chat;
		this.message = message;
		this.sendBy = sendBy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public int getId() {
		return id;
	}

	public User getSendBy() {
		return sendBy;
	}

	public void setSendBy(User sendBy) {
		this.sendBy = sendBy;
	}

}
