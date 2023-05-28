package com.br.forum.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.br.forum.dtos.RoleDto;
import com.br.forum.models.Role;
import com.br.forum.services.RoleService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleRestController {

	@Autowired
	private RoleService roleService;
	
    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<Role> create(@RequestBody @Valid RoleDto roleDto) {
        Role savedRole= roleService.save(roleDto);
        URI roleURI= URI.create("/roles/" + savedRole.getId());
        return ResponseEntity.created(roleURI).body(savedRole);
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        Role role= roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@RequestBody @Valid RoleDto roleDto,
        @PathVariable Long id) {
        Role role= roleService.update(roleDto, id);

        return ResponseEntity.ok(role);
    }

    @RolesAllowed({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Role> deleteById(@PathVariable Long id) {
        roleService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
