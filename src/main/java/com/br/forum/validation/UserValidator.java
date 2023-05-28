package com.br.forum.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.br.forum.exceptions.UserAlreadyRegisteredException;
import com.br.forum.models.User;
import com.br.forum.repository.UserRepository;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(User user) {
        validateEmail(user);
    }

    private void validateEmail(User user) {
        if (userRepository.isEmailAlreadyRegistered(user)) {
            var message= "User Registered Already exists with this e-mail";
            var fieldError= new FieldError(user.getClass().getName(), "email", user.getEmail(),
                false, null, null, message);

            throw new UserAlreadyRegisteredException(message, fieldError);
        }
    }

}
