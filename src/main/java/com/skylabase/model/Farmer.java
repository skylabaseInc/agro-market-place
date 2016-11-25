package com.skylabase.model;

import java.util.List;

/**
 * Domain object that represents a farm in the system.
 * 
 * @author ivange
 */
public class Farmer extends User {

	private List<String> farms;

	public List<String> getFarms() {
		return farms;
	}

	public void setFarms(List<String> farms) {
		this.farms = farms;
	}
	
	public void addFarm(String farm) {
		this.farms.add(farm);
	}
}
