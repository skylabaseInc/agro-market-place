package com.skylabase.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Farm {

	@Id
	private String id;
	private String name;
	private String ownerId;
	private String description;
	private String locationId;
	private List<String> products;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocationId() {
		return locationId;
	}
	
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public List<String> getProducts() {
		return products;
	}
	
	public void setProducts(List<String> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
