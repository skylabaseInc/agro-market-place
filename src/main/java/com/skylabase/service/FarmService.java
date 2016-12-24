package com.skylabase.service;

import com.skylabase.model.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service methods for {@link} model
 */
public interface FarmService {

    boolean exists(long farmId);

    Farm create(Farm farm);

    Farm findById(long id);

    Page<Farm> findAll(Pageable pageable);

    Page<Farm> findByNameLike(String name, Pageable pageable);

    Page<Farm> findByOwnerId(long ownerId, Pageable pageable);

    Farm update(Farm farm);

    void delete(long farmId);
}
