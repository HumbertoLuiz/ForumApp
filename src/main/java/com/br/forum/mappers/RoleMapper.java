package com.br.forum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.br.forum.dtos.RoleDto;
import com.br.forum.models.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

	Role toModel(RoleDto form);

    RoleDto toForm(Role model);
        
}