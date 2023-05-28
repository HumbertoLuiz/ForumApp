package com.br.forum.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.forum.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> findByName(String string);

}
