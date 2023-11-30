package beans;

import java.io.Serializable;

public class Chat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private User user1;
	private User user2;
	
	public Chat() {}
	
	public Chat(Integer id, User user1, User user2) {
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
	}

	public Integer getId() {
		return id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}
	
	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
}
