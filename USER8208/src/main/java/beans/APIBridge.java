package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.*;

public class APIBridge {
	URL url;
	HttpURLConnection urlConn;
	Gson gson = new Gson();
	BufferedReader bufferReader;
	String line;
	
	public APIBridge() {}
	
	public void setUrl(URL url) {
		this.url = url;
		return;
	}
	
	public Chat[] getChatList(User user) {
		if (url == null) {
			return new Chat[0];
		}
		return gson.fromJson(this.post(user), Chat[].class);
	}
	
	public Message[] getMessages() {
		if (url == null) {
			return new Message[0];
		}
		return gson.fromJson(this.getRaw(), Message[].class);
	}
	
	public Ticket[] getTicketList(Object obj) {
		if (url == null) {
			return new Ticket[0];
		}
		String result = this.post(obj);
		if (result.isEmpty()) {
			return new Ticket[0];
		}
		return gson.fromJson(result, Ticket[].class);
	}
	
	public Event[] getEventList() {
		return gson.fromJson(this.getRaw(), Event[].class);
	}
	
	public Event getEvent() {
		return gson.fromJson(this.getRaw(), Event.class);
	}
	
	public User getUser() {
		return gson.fromJson(this.getRaw(), User.class);		
	}
	
	protected String getRaw() {
		if (url == null) {
			return "";
		}
		StringBuilder content = new StringBuilder();
		try {
			urlConn = (HttpURLConnection) url.openConnection();
			bufferReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
	
			while ((line = bufferReader.readLine()) != null) {
				content.append(line);
			}
			
			bufferReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		urlConn.disconnect();
		
		return content.toString();
	}

	public String uploadTicket(Ticket t) {
		return this.post(t).isEmpty() ? "" : "success";
	}
	
	public String postMessage(Message msg) {
		return this.post(msg);
	}
	
	public String postChat(Chat chat) {
		return this.post(chat);
	}
	
	protected String post(Object obj) {
		if (url == null) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		
		try {
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.setDoOutput(true);
			OutputStream outStream = urlConn.getOutputStream();
			if (obj instanceof User) {
				outStream.write( gson.toJson(obj, User.class)
						.getBytes());
			} else if (obj instanceof Event) {
				outStream.write( gson.toJson(obj, Event.class)
						.getBytes());
			} else if (obj instanceof Ticket) {
				outStream.write( gson.toJson(obj, Ticket.class)
						.getBytes());
			} else if (obj instanceof Chat) {
				outStream.write( gson.toJson(obj, Chat.class)
						.getBytes());
			} else if (obj instanceof Message) {
				outStream.write( gson.toJson(obj, Message.class)
						.getBytes());
			}
			outStream.flush();
			outStream.close();
			
			bufferReader = new BufferedReader(
							new InputStreamReader(urlConn.getInputStream())
							);
			
			while((line = bufferReader.readLine()) != null) {
				buffer.append(line);
			}
	
			bufferReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		urlConn.disconnect();
		
		return buffer.toString();
	}
}
