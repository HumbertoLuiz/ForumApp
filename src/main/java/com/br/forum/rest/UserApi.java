package com.br.forum.rest;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.forum.dtos.UserDto;
import com.br.forum.models.Role;
import com.br.forum.models.User;
import com.br.forum.repository.RoleRepository;
import com.br.forum.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;
	
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UserDto userDto) {
        User createdUser= userService.save(userDto);
        URI uri= URI.create("/users/" + createdUser.getId());
        UserDto Dto= new UserDto(createdUser.getId(), createdUser.getEmail());
        return ResponseEntity.created(uri).body(Dto);
	}

    @ModelAttribute("roles")
    public Iterator<Role> getRoles() {
        List<Role> role= roleRepository.findAll();
        Iterator<Role> getRole= role.iterator();
        return getRole;
    }
}
