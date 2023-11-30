package Application.mysqlControllers;

import java.io.Serializable;

public class Ticket implements Serializable{

    Long id;

    String type;

    String code;

    String price;

    Event event;

    User users;

    public Ticket(){}

    public Ticket(String type, String code, String price, Event event, User users){
        this.type = type;
        this.code = code;
        this.price = price;
        this.event = event;
        this.users = users;
    }

    public Ticket(Long id, String type, String code, String price, Event event, User users){
        this.id = id;
        this.type = type;
        this.code = code;
        this.price = price;
        this.event = event;
        this.users = users;
    }

    public Long getId() {
        return id;
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

    public String getEvent() {
        return event.name_event;
    }

    public void setEvent(String event) {
        this.event.name_event = event;
    }

    public String getUser() {
        return users.username;
    }

    public void setUser(String user) {
        this.users.username = user;
    }

}
