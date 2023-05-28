package com.br.forum.services;

import java.util.List;

import com.br.forum.dtos.CourseDto;
import com.br.forum.models.Course;

public interface CourseService {

	List<Course> findAll();

	Course save(CourseDto form);

	Course findById(Long id);

	Course update(CourseDto form, Long id);

	void deleteById(Long id);
}
