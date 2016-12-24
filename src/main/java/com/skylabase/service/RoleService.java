package com.skylabase.service;

import com.skylabase.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Role create(Role instance);

    Role findById(Long id);

    Page<Role> findAll(Pageable pageable);

    Role update(Role instance);

    void delete(Long roleId);

    boolean exists(Long roleId);
}
