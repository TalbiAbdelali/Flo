package com.ata.flo.model;

import java.util.List;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
	
	private final String id;
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
	
	private int active;
	
	private List<String> roles;
	
	private List<String> permissions;
	
	/*private final boolean isAccountExpired;
	private final boolean isAccountLocked;
	private final boolean isCredentielExpired;
	private final boolean isEnabled;
	private final boolean isAdmin;*/
	
	public User(@JsonProperty("id") String id,
			@JsonProperty("username") String username,
			@JsonProperty("lastname") String lastname,
			@JsonProperty("password") String password,
			@JsonProperty("email") String email,
			@JsonProperty("active") int active,
			@JsonProperty("roles") List<String> roles,
			@JsonProperty("permissions") List<String> permissions) {
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.father = null;
		this.mother = null;
		this.location = "";
		this.active = active;
		this.roles = roles;
		this.permissions = permissions;
	}
	
	public User(String id, String username, String lastname, String password, String email, User father, User mother,
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
	}
	
	public String getId() {
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}
