package beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

public class Tickets implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idTickets;
	private String type;
	private Integer price;
	private String code;
	private LocalDate createdAt;
	private Event event;
	private User user;
	
	public Tickets() {}

    public Event getEvent() { 
    	return event; 
    }
    
    public User getUser() { 
    	return user; 
    }
	
	public Integer getIdTickets() {
		return idTickets;
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

	public Event getEvent_id() {
		return event;
	}

	public void setEvent_id(Event e) {
		this.event = e;
	}
	
	public void setUser(User u) {
		this.user = u;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
