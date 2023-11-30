
package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String photo_path;
	private String photo;
	private String sala;
	private String city;
	private String name_event;
	private String fecha;
	private String category;
	private LocalDate createdAt;
	private Ticket[] ticketsList;

	public Event() {}

	public ArrayList<String> getTicketList() {
		return new ArrayList<String>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setidEvent(Integer idEvent) {
		this.id = idEvent;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto_Path() {
		return photo_path;
	}
	
	public void setPhoto_Path(String photo_path) {
		this.photo_path = photo_path;
	}
	
	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName_event() {
		return name_event;
	}

	public void setName_event(String name_event) {
		this.name_event = name_event;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Ticket[] getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(Ticket[] tickets) {
		this.ticketsList = tickets;
	}
	
	public Integer getNumberOfTickets() {
		return (this.ticketsList == null) ? 0 : this.ticketsList.length;
	}
}
