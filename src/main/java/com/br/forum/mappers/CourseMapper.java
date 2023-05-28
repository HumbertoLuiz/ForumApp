package com.br.forum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.br.forum.dtos.CourseDto;
import com.br.forum.models.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

	Course toModel(CourseDto form);

	CourseDto toForm(Course model);
}
