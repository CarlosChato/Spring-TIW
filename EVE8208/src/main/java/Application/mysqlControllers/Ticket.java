package Application.mysqlControllers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "tickets")
// @ XmlRootElement indica que Spring tiene que tratar de convertir un objeto del tipo anotado a XML si se requiere.
@XmlRootElement
public class Ticket implements Serializable {
    @Id
    @Column(name = "idTickets")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String type;
    String code;
    String price;

    @ManyToOne
            @JoinColumn(name="Users_id")
    User users;
    @ManyToOne
        @JoinColumn(name="Event_id")
    @JsonBackReference
    private Event event;

    public Long getId() {
        return id;
    }

    public User getUsers() {
        return users;
    }

    public Event getEvent() {
        return event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Ticket(){
    }

    public Ticket(Long id, String type, String code, String price, User Users_id, Event Event_id) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.price = price;
        this.users = Users_id;
        this.event = Event_id;
    }

    @PersistenceConstructor
    public Ticket(String type, String code, String price, User Users_id, Event Event_id) {
        this.type = type;
        this.code = code;
        this.price = price;
        this.users = Users_id;
        this.event = Event_id;
    }
}

