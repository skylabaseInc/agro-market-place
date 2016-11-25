package com.skylabase.service;

import java.util.List;

import com.skylabase.model.Farm;

/**
 * Service methods for {@link} model
 * 
 * @author ivange
 */
public interface FarmService {

	/**
	 * Find a Farm by Id.
	 * 
	 * @param id the id of the farm to get
	 * @return the farm object if found else return null
	 */
	public Farm findById(String id);
	
	/**
	 * Get a list of all farms from the system.
	 * 
	 * @return list containing all farms in the system
	 */
	public List<Farm> findAll();
	
	/**
	 * Creates a new farm in the system.
	 * 
	 * @param farm the farm to create
	 * @return the farm created
	 */
	public Farm create(Farm farm);
	
	/**
	 * Updates and existing Farm.
	 * 
	 * @param Farm to be updated
	 * @return the updated Farm
	 */
	public Farm update(Farm farm);
	
	/**
	 * Deletes a farm from the system.
	 * 
	 * @param farm the farm to be deleted
	 */
	public void delete(Farm farm);

	/**
	 * Checks if given farm exists.
	 * 
	 * @param farm the farm been checked for existence
	 * @return true if farm exist else false
	 */
	public boolean exists(Farm farm);

	/**
	 * Delete all farms from the system.
	 */
	public void deleteAll();
}
