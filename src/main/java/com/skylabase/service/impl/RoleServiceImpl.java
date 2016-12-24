package com.skylabase.service.impl;

import com.skylabase.model.Role;
import com.skylabase.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}

@Service
class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role instance) {
        return roleRepository.save(instance);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Role update(Role instance) {
        return roleRepository.save(instance);
    }

    @Override
    public void delete(Long roleId) {
        roleRepository.delete(roleId);
    }

    @Override
    public boolean exists(Long roleId) {
        return roleRepository.exists(roleId);
    }
}