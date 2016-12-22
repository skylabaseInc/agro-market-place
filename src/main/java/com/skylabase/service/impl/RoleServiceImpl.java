package com.skylabase.service.impl;

import com.skylabase.model.Role;
import com.skylabase.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findAll() {
        final List<Role> roles = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            roles.add(role);
        }
        return roles;
    }

    @Override
    public Role create(Role instance) {
        return roleRepository.save(instance);
    }

    @Override
    public Role update(Role instance) {
        return roleRepository.save(instance);
    }

    @Override
    public void delete(Role instance) {
        roleRepository.delete(instance);
    }

    @Override
    public boolean exists(Role instance) {
        return roleRepository.exists(instance.getId());
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }
}

interface RoleRepository extends PagingAndSortingRepository<Role, Long> {}