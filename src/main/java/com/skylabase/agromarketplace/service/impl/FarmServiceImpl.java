package com.skylabase.agromarketplace.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.skylabase.agromarketplace.model.Farm;
import com.skylabase.agromarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.skylabase.agromarketplace.service.FarmService;

/**
 * RestController for that handles requests {@link Farm} objects
 *
 * @see Farm
 * @see FarmRepository
 */
@Repository
class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private UserService userService;

    @Override
    public Farm findById(Long id) {
        return farmRepository.findOne(id);
    }

    @Override
    public List<Farm> findAll() {
        final List<Farm> farms = new ArrayList<>();
        for (Farm farm: farmRepository.findAll()) {
            farms.add(farm);
        }
        return farms;
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
        return farmRepository.exists(farm.getId());
    }
}

/**
 * Repository used by UserRepositoryImpl to access database.
 */
interface FarmRepository extends PagingAndSortingRepository<Farm, Long> {

}
