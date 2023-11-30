package Application.mysqlControllers;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "event")
// @ XmlRootElement indica que Spring tiene que tratar de convertir un objeto del tipo anotado a XML si se requiere.
@XmlRootElement
public class Event implements Serializable {

    @Id
    @Column(name = "idEvent")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String photo_path;
    String photo;
    String sala;
    String city;
    String name_event;
    Date fecha;
    String category;

    public Event(){
    }

    public Event(Long id, String photo_path, String photo, String sala, String city, String name_event, String fecha, String category) {
        this.id = id;
        this.photo_path = photo_path;
        this.photo = photo;
        this.sala = sala;
        this.city = city;
        this.name_event = name_event;
        this.fecha = Date.valueOf(fecha);
        this.category = category;
    }

    @PersistenceConstructor
    public Event(String photo_path, String photo, String sala, String city, String name_event, String fecha, String category) {
        this.photo_path = photo_path;
        this.photo = photo;
        this.sala = sala;
        this.city = city;
        this.name_event = name_event;
        this.fecha = Date.valueOf(fecha);
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = Date.valueOf(fecha);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

