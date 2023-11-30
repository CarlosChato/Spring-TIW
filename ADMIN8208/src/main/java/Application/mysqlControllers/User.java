package Application.mysqlControllers;

import java.io.Serializable;

public class User implements Serializable{

    Long id;

    Boolean admin;

    String name_user;

    String password_user;

    String username;

    String surname;

    String email;

    String phone;


    public User(){}

    public User(Boolean admin, String name_user, String password_user, String username, String surname,
                String email, String phone){
        this.admin = admin;
        this.name_user = name_user;
        this.password_user = password_user;
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public User(Long id, Boolean admin, String name_user, String password_user, String username, String surname,
                String email, String phone){
        this.id = id;
        this.admin = admin;
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
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getIsAdmin() {
        if (admin) {
            return "SÍ";
        }
        else{
            return "NO";
        }
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
