package com.br.forum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.br.forum.dtos.UserDto;
import com.br.forum.models.User;

@Mapper(componentModel= "spring")
public interface UserMapper {

    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    @Mapping(target= "password", source= "password")
    User toModel(UserDto form);

    UserDto toForm(User model);
}