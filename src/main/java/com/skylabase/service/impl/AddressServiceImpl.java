package com.skylabase.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.skylabase.model.Address;
import com.skylabase.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Address findById(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public List<Address> findAll() {
		final List<Address> addresses = new ArrayList<>();
		for (Address address: addressRepository.findAll()) {
			addresses.add(address);
		}
		return addresses;
	}

	@Override
	public Address create(Address instance) {
		return addressRepository.save(instance);
	}

	@Override
	public Address update(Address instance) {
		return addressRepository.save(instance);
	}

	@Override
	public void delete(Address instance) {
		addressRepository.delete(instance);
	}

	@Override
	public boolean exists(Address instance) {
		return addressRepository.exists(instance.getId());
	}

	@Override
	public void deleteAll() {
		addressRepository.deleteAll();
	}
}

interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
	
}
