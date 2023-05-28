package com.br.forum.services;

import java.util.List;

import com.br.forum.dtos.RoleDto;
import com.br.forum.models.Role;

public interface RoleService {

	List<Role> findAll();

	Role save(RoleDto form);

    Role update(RoleDto form, Long id);

	Role findById(Long id);

	void deleteById(Long id);

}