package com.skylabase.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.skylabase.model.User;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.skylabase.model.Farm;
import com.skylabase.service.FarmService;

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

    @Override
    public void deleteAll() {
        farmRepository.deleteAll();
    }
}

/**
 * Repository used by UserRepositoryImpl to access database.
 */
interface FarmRepository extends PagingAndSortingRepository<Farm, Long> {

}
