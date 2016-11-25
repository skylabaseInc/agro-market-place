package com.skylabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.skylabase.model.Farmer;
import com.skylabase.service.FarmerService;

/**
 * Implementation of {@link UserService}
 * 
 * @author ivange
 *
 */
@Service
class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;
	
	@Override
	public Farmer findById(String id) {
		return farmerRepository.findOne(id);
	}

	@Override
	public List<Farmer> findAll() {
		return farmerRepository.findAll();
	}

	@Override
	public Farmer create(Farmer instance) {
		return farmerRepository.save(instance);
	}

	@Override
	public Farmer update(Farmer instance) {
		return farmerRepository.save(instance);
	}

	@Override
	public void delete(Farmer instance) {
		farmerRepository.delete(instance);
	}

	@Override
	public boolean exists(Farmer instance) {
		if (instance.getId() == null)
			return false;
		return farmerRepository.exists(instance.getId());
	}

	@Override
	public void deleteAll() {
		farmerRepository.deleteAll();
	}
}

interface FarmerRepository extends MongoRepository<Farmer, String> {
	
}
