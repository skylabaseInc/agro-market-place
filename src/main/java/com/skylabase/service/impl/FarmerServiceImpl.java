package com.skylabase.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.skylabase.model.Farmer;
import com.skylabase.service.FarmerService;

/**
 * Implementation of {@link FarmerService}
 */
@Service
class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;
	
	@Override
	public Farmer findById(Long id) {
		return farmerRepository.findOne(id);
	}

	@Override
	public List<Farmer> findAll() {
		final List<Farmer> farmers = new ArrayList<>();
		for (Farmer farmer: farmerRepository.findAll()) {
			farmers.add(farmer);
		}
		return farmers;
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
		return farmerRepository.exists(instance.getId());
	}

	@Override
	public void deleteAll() {
		farmerRepository.deleteAll();
	}
}

interface FarmerRepository extends PagingAndSortingRepository<Farmer, Long> {
	
}
