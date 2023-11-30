package Application.mysqlControllers;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "users")
// @ XmlRootElement indica que Spring tiene que tratar de convertir un objeto del tipo anotado a XML si se requiere.
@XmlRootElement

public class User implements Serializable{
    @Id
    @Column(name = "idUsers")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Boolean isAdmin;
    String name_user;
    String password_user;
    String username;
    String surname;
    String email;
    String phone;


    public User(){}

    public User(Long id, Boolean isAdmin, String name_user, String password_user, String username, String surname,
                String email, String phone){

        this.id = id;
        this.isAdmin = isAdmin;
        this.name_user = name_user;
        this.password_user = password_user;
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;

    }

    @PersistenceConstructor
    public User(Boolean isAdmin, String name_user, String password_user, String username, String surname,
                String email, String phone){
        this.isAdmin = isAdmin;
        this.name_user = name_user;
        this.password_user = password_user;
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
