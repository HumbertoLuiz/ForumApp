package com.br.forum.services;

import java.util.List;

import com.br.forum.dtos.UserDto;
import com.br.forum.models.User;

public interface UserService {

    public List<User> findAll();

    public User save(UserDto userDto);
    
//    User findById(Long id);
//
//    User update(UserDTO form, Long id);
//
//    void deleteById(Long id);
	
}

