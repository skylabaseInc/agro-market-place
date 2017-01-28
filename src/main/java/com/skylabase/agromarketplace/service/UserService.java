package com.skylabase.agromarketplace.service;

import com.skylabase.agromarketplace.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service that provides CRUD operations for {@link User}
 * 
 * @see User
 */
public interface UserService extends GenericService<User> {

    /**
     * Get all elements of type T in the system.
     *
     * @param pageable a pageable instance that determines
     *                 the number elements to return
     * @return page of all elements requested
     */
    public Page<User> listAllByPage(Pageable pageable);
}
