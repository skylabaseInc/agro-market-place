package com.skylabase.agromarketplace.service.impl;

import com.skylabase.agromarketplace.model.Role;
import com.skylabase.agromarketplace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
class RoleServiceImpl implements RoleService {

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
    public Page<Role> listAllByPage(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}

interface RoleRepository extends PagingAndSortingRepository<Role, Long> {}