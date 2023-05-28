package com.br.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.br.forum.models.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findUserById(Long id);

    @Transactional
    @Modifying(clearAutomatically= true, flushAutomatically= true)
    @Query("delete from User u where u.id = :id")
    void removeUser(Long id);

    default Boolean isEmailAlreadyRegistered(User user) {
        if (user.getEmail() == null) { return false; }
        return findByEmail(user.getEmail())
            .map(userFound -> !userFound.getId().equals(user.getId()))
            .orElse(false);
    }

}
