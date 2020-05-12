package com.ata.flo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private final UUID id;
	@NotBlank
	private final String username;
	@NotBlank
	private final String lastname;
	@NotBlank
	private final String password;
	@NotBlank
	private final String email;
	private final User father;
	private final User mother;
	private final String location;
	private final boolean isAccountExpired;
	private final boolean isAccountLocked;
	private final boolean isCredentielExpired;
	private final boolean isEnabled;
	private final boolean isAdmin;
	
	public User(@JsonProperty("id") UUID id,
			@JsonProperty("username") String username,
			@JsonProperty("lastname") String lastname,
			@JsonProperty("password") String password,
			@JsonProperty("email") String email) {
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.father = null;
		this.mother = null;
		this.location = "";
		this.isAccountExpired = true;
		this.isAccountLocked = true;
		this.isCredentielExpired = true;
		this.isEnabled = true;
		this.isAdmin = true;
	}
	
	public User(UUID id, String username, String lastname, String password, String email, User father, User mother,
			String location, boolean isAccountExpired, boolean isAccountLocked, boolean isCredentielExpired,
			boolean isEnabled, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.father = father;
		this.mother = mother;
		this.location = location;
		this.isAccountExpired = isAccountExpired;
		this.isAccountLocked = isAccountLocked;
		this.isCredentielExpired = isCredentielExpired;
		this.isEnabled = isEnabled;
		this.isAdmin = isAdmin;
	}
	
	public UUID getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getLastname() {
		return lastname;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public User getFather() {
		return father;
	}
	public User getMother() {
		return mother;
	}
	public String getLocation() {
		return location;
	}
	public boolean isAccountExpired() {
		return isAccountExpired;
	}
	public boolean isAccountLocked() {
		return isAccountLocked;
	}
	public boolean isCredentielExpired() {
		return isCredentielExpired;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	
	

}
