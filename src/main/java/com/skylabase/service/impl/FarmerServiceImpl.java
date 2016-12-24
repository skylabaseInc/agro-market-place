package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Farmer;
import com.skylabase.model.User;
import com.skylabase.service.FarmerService;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link FarmerService}
 */
@Service
class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;

    @Autowired
    private UserService userService;

	@Override
	public Farmer create(Farmer instance) {
        if (exists(instance.getId())) {
            throw new ItemAlreadyExistsException("a Farmer already exists with this id");
        }
		return farmerRepository.save(instance);
	}
	
	@Override
	public Farmer findById(long id) {
		return farmerRepository.findById(id);
	}

    @Override
    public Page<Farmer> findByUsername(String username, Pageable pageable) {
        Page<User> users = userService.findByUsername(username, pageable);
        List<Farmer> farmers = new ArrayList<>();
        for (User user : users) {
            farmers.add(farmerRepository.findByUser(user));
        }
        Page<Farmer> returnValue = (Page<Farmer>) farmers;
        return returnValue;
    }

    @Override
	public Page<Farmer> findAll(Pageable pageable) {
		return farmerRepository.findAll(pageable);
	}

	@Override
	public Farmer update(Farmer instance) {
        if (!exists(instance.getId())) {
            throw new ItemNotFoundException("no Farmer with the given id");
        }
		return farmerRepository.save(instance);
	}

	@Override
	public void delete(long farmerId) {
		farmerRepository.delete(farmerId);
	}

	@Override
	public boolean exists(long farmerId) {
		return farmerRepository.exists(farmerId);
	}
}

interface FarmerRepository extends PagingAndSortingRepository<Farmer, Long> {

	Page<Farmer> findByUsernameLike(@Param("username") String username, Pageable pageable);

	Page<Farmer> findByVoidedIsFalse(Pageable pageable);

	Page<Farmer> findByVoidedIsTrue(Pageable pageable);

	Farmer findById(@Param("farmerId") long farmerId);

	Farmer findByEmail(@Param("email") String email);

    Farmer findByUser(@Param("user") User user);
}
