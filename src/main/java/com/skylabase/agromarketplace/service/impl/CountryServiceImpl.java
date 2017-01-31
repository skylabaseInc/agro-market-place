package com.skylabase.agromarketplace.service.impl;

import com.skylabase.agromarketplace.service.CountryService;
import com.skylabase.agromarketplace.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country findById(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public List<Country> findAll() {
        final List<Country> countries = new ArrayList<>();
        for (Country country: countryRepository.findAll()) {
            countries.add(country);
        }
        return countries;
    }

    @Override
    public Country create(Country instance) {
        return countryRepository.save(instance);
    }

    @Override
    public Country update(Country instance) {
        return countryRepository.save(instance);
    }

    @Override
    public void delete(Country instance) {
        countryRepository.delete(instance);
    }

    @Override
    public boolean exists(Country instance) {
        return countryRepository.exists(instance.getId());
    }

    @Override
    public Page<Country> listAllByPage(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}

interface CountryRepository extends PagingAndSortingRepository<Country, Long> {}