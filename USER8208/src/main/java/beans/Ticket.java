package beans;

import java.io.Serializable;

public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String type;
	private Integer price;
	private String code;
	private Event event;
	private User users;
	
	public Ticket() {}

    public Event getEvent() { 
    	return event; 
    }
	
	public void setEvent(Event e) {
		this.event = e;
	}
    
    public User getUsers() { 
    	return users; 
    }
	
	public void setUsers(User u) {
		this.users = u;
	}
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
