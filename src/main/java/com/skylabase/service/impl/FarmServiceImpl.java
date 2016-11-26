package com.skylabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skylabase.model.Farm;
import com.skylabase.service.FarmService;

/**
 * RestController for that handles requests {@link Farm} objects
 * 
 * @see Farm
 * @see FarmRepository
 * 
 * @author ivange
 */
@Repository
class FarmServiceImpl implements FarmService {

	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public Farm findById(String id) {
		return farmRepository.findOne(id);
	}

	@Override
	public List<Farm> findAll() {
		return farmRepository.findAll();
	}

	@Override
	public Farm create(Farm farm) {
		return farmRepository.save(farm);
	}

	@Override
	public Farm update(Farm farm) {
		return farmRepository.save(farm);
	}

	@Override
	public void delete(Farm farm) {
		farmRepository.delete(farm);
	}

	@Override
	public boolean exists(Farm farm) {
		if (farm.getId() == null)
			return false;
		return farmRepository.exists(farm.getId());
	}

	@Override
	public void deleteAll() {
		farmRepository.deleteAll();
	}

	@Override
	public List<Farm> findByOwnerId(String ownerId) {
		return farmRepository.findByOwnerId(ownerId);
	}
}

/**
 * Repository used by UserRepositoryImpl to access database.
 * 
 * @author ivange
 */
interface FarmRepository extends MongoRepository<Farm, String> {
	
	List<Farm> findByOwnerId(@Param("ownerId") String ownerId);
}
