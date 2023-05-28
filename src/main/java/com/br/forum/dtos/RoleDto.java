package com.br.forum.dtos;

import java.util.Set;

import com.br.forum.models.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    @Size(min= 3, max= 50)
    private String name;

    @NotNull
    private Set<User> users;

}
