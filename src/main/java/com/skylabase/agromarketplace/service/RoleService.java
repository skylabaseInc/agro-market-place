package com.skylabase.agromarketplace.service;

import com.skylabase.agromarketplace.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService extends GenericService<Role>{

    Page<Role> listAllByPage(Pageable pageable);
}
