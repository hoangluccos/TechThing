package com.learning.model;

import java.io.Serializable;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id  
	private String username;

//	, referencedColumnName = "role_id"
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false )
	@JsonManagedReference
	private Role role;
	
	private String password;
	
	private String fullname;
	
	private String mail;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(String username, Integer role_id, String password, String fullname, String mail, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.mail = mail;
		this.role = role;
	}

	public User() {
		super();
	}

	
	
	
}
