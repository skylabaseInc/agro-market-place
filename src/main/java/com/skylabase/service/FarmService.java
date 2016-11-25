package com.skylabase.service;

import java.util.List;

import com.skylabase.model.Farm;

/**
 * Service methods for {@link} model
 * 
 * @author ivange
 */
public interface FarmService extends GenericService<Farm>{
	
	public List<Farm> findByOwnerId(String ownerId);
	
}
