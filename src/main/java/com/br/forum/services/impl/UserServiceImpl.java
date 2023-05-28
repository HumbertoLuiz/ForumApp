package com.br.forum.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.forum.dtos.UserDto;
import com.br.forum.mappers.UserMapper;
import com.br.forum.models.User;
import com.br.forum.repository.UserRepository;
import com.br.forum.services.UserService;
import com.br.forum.validation.UserValidator;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserValidator validator;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDto userDto) {
        var model= userMapper.toModel(userDto);
        model.setReal_password(model.getPassword());
        var passwordHash= passwordEncoder.encode(model.getPassword());
        model.setPassword(passwordHash);
        validator.validate(model);
        return userRepository.save(model);
    }
}
