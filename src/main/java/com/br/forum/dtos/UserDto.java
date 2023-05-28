package com.br.forum.dtos;

import java.util.Set;

import com.br.forum.models.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotNull
    @Size(min= 3, max= 255)
    private String name;

    @NotNull
    @Size(min= 3, max= 255)
    @Email
    private String email;

    @NotNull
    private String password;

    public UserDto(Long id, String email) {

    }

    @NotNull
    private Set<Role> roles;

}
