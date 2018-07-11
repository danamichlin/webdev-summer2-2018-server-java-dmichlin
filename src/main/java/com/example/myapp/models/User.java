package com.example.myapp.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username; 
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String role; 
	private String phone;
//	private Date dateOfBirth;
	
	// getters + setters
	public void setId(int id) {
	    this.id = id;
	}
	public int getId() {
	    return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	} 
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password; 
	}
	public String getPassword() {
		return this.password;
	}

	public void setEmail(String email) {
		this.email = email; 
	}
	public String getEmail() {
		return this.email;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName; 
	}
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName; 
	}
	public String getLastName() {
		return this.lastName;
	}
	
	public void setRole(String role) {
		this.role = role; 
	}
	public String getRole() {
		return this.role;
	}
	
	public void setPhone(String phone) {
		this.phone = phone; 
	}
	public String getPhone() {
		return this.phone;
	}
	
//	public void setDateOfBirth(Date dateOfBirth) {
//		this.dateOfBirth = dateOfBirth; 
//	}
//	public Date getDateOfBirth() {
//		return this.dateOfBirth;
//	}

	// constructor
	public User(String username, String password, String email, String firstName, 
			String lastName, String role, String phone, Date dateOfBirth) {
		this.username = username;
		this.password = password;
		this.email = email; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.phone = phone; 
//		this.dateOfBirth = dateOfBirth;
	}

}
