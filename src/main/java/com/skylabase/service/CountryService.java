package com.skylabase.service;

import com.skylabase.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Country create(Country instance);

    Country findById(Long id);

    Page<Country> findAll(Pageable pageable);

    Country update(Country instance);

    void delete(Long countryId);

    boolean exists(Long countryId);
}
