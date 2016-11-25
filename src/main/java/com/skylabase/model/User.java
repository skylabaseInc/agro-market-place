package com.skylabase.model;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;
	private String username;
	private String email;
	private String phoneNumber; 
	private String city;
	private String country;
	
	public User() {}

	public User(User user) {
		this.username = user.username;
		this.email = user.email;
		this.phoneNumber = user.phoneNumber;
		this.country = user.phoneNumber;
		this.city = user.city;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
