package Application.mysqlControllers;

import java.io.Serializable;

public class Event implements Serializable{

    Long id;

    String name_event;
    String category;
    String fecha;
    String city;
    String sala;
    String photo;
    String createdAt;


    public Event(){}

    public Event(String name_event, String category, String fecha, String city, String sala,
                String photo){
        this.name_event = name_event;
        this.category = category;
        this.fecha = fecha;
        this.city = city;
        this.sala = sala;
        this.photo = photo;
    }

    public Event(Long id, String name_event, String category, String fecha, String city, String sala,
                 String photo){
        this.id = id;
        this.name_event = name_event;
        this.category = category;
        this.fecha = fecha;
        this.city = city;
        this.sala = sala;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public String getname_event() {
        return name_event;
    }

    public void setname_event(String name_event) {
        this.name_event = name_event;
    }

    public String getcategory() {
        return category;
    }

    public void setcategory(String category) {
        this.category = category;
    }

    public String getfecha() {
        return fecha;
    }

    public void setfecha(String fecha) {
        this.fecha = fecha;
    }

    public String getcity() {
        return city;
    }

    public void setcity(String city) {
        this.city = city;
    }

    public String getsala() {
        return sala;
    }

    public void setsala(String sala) {
        this.sala = sala;
    }

    public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }

    public String getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
