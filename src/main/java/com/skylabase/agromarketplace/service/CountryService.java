package com.skylabase.agromarketplace.service;

import com.skylabase.agromarketplace.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService extends GenericService<Country> {

    Page<Country> listAllByPage(Pageable pageable);
}
