package com.skylabase.service.impl;

import com.skylabase.exceptions.ItemAlreadyExistsException;
import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Address;
import com.skylabase.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Address create(Address address) {
		if (exists(address.getId())) {
            throw new ItemAlreadyExistsException("cannot create already existing Address");
        }
		return addressRepository.save(address);
	}
	
	@Override
	public Address findById(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

    @Override
    public Page<Address> findByCountryId(long countryId, Pageable pageable) {
        return addressRepository.findByCountryId(countryId, pageable);
    }

	@Override
	public Address update(Address instance) {
        if (!exists(instance.getId())) {
            throw new ItemNotFoundException("cannot update Address which does not exist");
        }
		return addressRepository.save(instance);
	}

	@Override
	public void delete(long addressId) {
		addressRepository.delete(addressId);
	}

	@Override
	public boolean exists(long addressId) {
		return addressRepository.exists(addressId);
	}
}

interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

    Address findById(Long id);

    Page<Address> findByCountryId(@Param("country_id") long countryId, Pageable pageable);
}
