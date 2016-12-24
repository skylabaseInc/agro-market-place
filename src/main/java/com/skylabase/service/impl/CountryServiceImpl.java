package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Country;
import com.skylabase.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country create(Country instance) {
        if (exists(instance.getId())) {
            throw new ItemAlreadyExistsException("cannot create Country that already exists");
        }
        return countryRepository.save(instance);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override
    public Country update(Country instance) {
        if (!exists(instance.getId())) {
            throw new ItemNotFoundException("cannot update non existent Country");
        }
        return countryRepository.save(instance);
    }

    @Override
    public void delete(Long countryId) {
        countryRepository.delete(countryId);
    }

    @Override
    public boolean exists(Long countryId) {
        return countryRepository.exists(countryId);
    }
}

interface CountryRepository extends PagingAndSortingRepository<Country, Long> {}