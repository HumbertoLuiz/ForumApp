package com.br.forum.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.forum.dtos.RoleDto;
import com.br.forum.exceptions.RoleNotFoundException;
import com.br.forum.mappers.RoleMapper;
import com.br.forum.models.Role;
import com.br.forum.repository.RoleRepository;
import com.br.forum.services.RoleService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role save(RoleDto form) {
        var model= roleMapper.toModel(form);

        return roleRepository.save(model);
    }

    public Role findById(Long id) {
        var roleFound= roleRepository.findById(id);

        if (roleFound.isPresent()) { return roleFound.get(); }

        var message= String.format("Role with ID %d not found", id);
        throw new RoleNotFoundException(message);
    }

    public Role update(RoleDto form, Long id) {
        var roleFound= findById(id);

        var model= roleMapper.toModel(form);
        ((Role) model).setId(roleFound.getId());

        return roleRepository.save(model);
    }

    public void deleteById(Long id) {
        var roleFound= findById(id);

        roleRepository.delete(roleFound);
    }
}