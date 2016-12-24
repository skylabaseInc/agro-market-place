package com.skylabase.service;

import com.skylabase.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

    Address create(Address address);

    Address findById(Long id);

    Page<Address> findAll(Pageable pageable);

    Page<Address> findByCountryId(long countryId, Pageable pageable);

    Address update(Address instance);

    void delete(long addressId);

    boolean exists(long addressId);
}
