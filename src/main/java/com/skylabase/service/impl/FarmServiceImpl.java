package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Farm;
import com.skylabase.service.FarmService;
import com.skylabase.service.ProductService;
import com.skylabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * RestController for that handles requests {@link Farm} objects
 *
 * @see Farm
 * @see FarmRepository
 */
@Service
class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public boolean exists(long farmId) {
        return farmDAO.exists(farmId);
    }

    @Override
    public Farm create(Farm farm) {
        if (exists(farm.getId())) {
            throw new ItemAlreadyExistsException("cannot create already existing Farm");
        }
        return farmDAO.save(farm);
    }

    @Override
    public Farm findById(long id) {
        return farmDAO.findById(id);
    }

    @Override
    public Page<Farm> findAll(Pageable pageable) {
        return farmDAO.findAll(pageable);
    }

    @Override
    public Page<Farm> findByNameLike(String name, Pageable pageable) {
        return farmDAO.findByNameLike(name, pageable);
    }

    @Override
    public Page<Farm> findByOwnerId(long ownerId, Pageable pageable) {
        return farmDAO.findByOwnerId(ownerId, pageable);
    }

    @Override
    public Farm update(Farm farm) {
        if (!exists(farm.getId())) {
            throw new ItemNotFoundException("cannot update Farm which does not exist");
        }
        return farmDAO.save(farm);
    }

    @Override
    public void delete(long farmId) {
        farmDAO.delete(farmId);
    }
}

/**
 * Repository used by UserRepositoryImpl to access database.
 */
interface FarmRepository extends PagingAndSortingRepository<Farm, Long> {

    Farm findById(@Param("farmId") long farmId);

    Page<Farm> findByName(@Param("name") String name, Pageable pageable);

    Page<Farm> findByNameLike(@Param("name") String name, Pageable pageable);

    Page<Farm> findByOwnerId(@Param("ownerId") long ownerId, Pageable pageable);
}
