package com.skylabase.service;

import java.util.List;

/**
 * Generic Service api that will be implemented by all services
 *  
 * @author ivange
 *
 * @param <T>
 */
public interface GenericService<T> {

	/**
	 * Get an element of type T with given id.
	 * 
	 * @param id the id of the element to get
	 * @return the element if found
	 */
	public T findById(Long id);
	
	/**
	 * Get all elements of type T in the system.
	 * 
	 * @return list of all elements found
	 */
	public List<T> findAll();
	
	/**
	 * Creates a new element of type T in the system
	 * 
	 * @param instance an instance of the element been created
	 * @return the created element
	 */
	public T create(T instance);
	
	/**
	 * Update an existing element in the system.
	 * 
	 * @param instance the updated version of the existing element
	 * @return the updated version of the existing element
	 */
	public T update(T instance);
	
	/**
	 * Deletes and element of type T from the system.
	 * 
	 * @param instance the element been deleted
	 */
	public void delete(T instance);

	/**
	 * Checks if element exists in the system.
	 *
	 * @param instance the element
	 * @return true if element exists else return false
	 */
	public boolean exists(T instance);
}
