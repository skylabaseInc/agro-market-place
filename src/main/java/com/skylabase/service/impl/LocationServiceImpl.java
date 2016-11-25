package com.skylabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.skylabase.model.Location;
import com.skylabase.service.LocationService;

@Service
class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public Location findById(String id) {
		return locationRepository.findOne(id);
	}

	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	@Override
	public Location create(Location instance) {
		return locationRepository.save(instance);
	}

	@Override
	public Location update(Location instance) {
		return locationRepository.save(instance);
	}

	@Override
	public void delete(Location instance) {
		locationRepository.delete(instance);
	}

	@Override
	public boolean exists(Location instance) {
		if (instance.getId() == null)
			return false;
		return locationRepository.exists(instance.getId());
	}

	@Override
	public void deleteAll() {
		locationRepository.deleteAll();
	}
}

interface LocationRepository extends MongoRepository<Location, String> {
	
}
