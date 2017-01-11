package com.skylabase.service.impl;

import com.skylabase.model.Country;
import com.skylabase.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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
}

interface CountryRepository extends PagingAndSortingRepository<Country, Long> {}