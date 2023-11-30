package beans;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int phone;
	private String email;
	private String surname;
	private String username;
	private String password_user;
	private String name_user;
	private Boolean admin;
	
	public User() {}
	
	public User(int id, int phone, String email, String surname,
				String username, String password_user, String name_user,
				Boolean admin) {
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.surname = surname;
		this.username = username;
		this.password_user = password_user;
		this.name_user = name_user;
		this.admin = admin;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPhone() {
		return phone;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean isAdmin) {
		this.admin = isAdmin;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.id + "\nUsername: " + this.username + "\nSurname: " + this.surname + "\nEmail: " + this.email + 
				"\nPhone: " + this.phone + "\nName: " + this.name_user + "\nPassword: " + this.password_user + 
				"\nIs admin: " + this.admin;
	}
}
