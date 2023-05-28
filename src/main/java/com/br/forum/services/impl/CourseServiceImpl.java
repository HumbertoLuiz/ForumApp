package com.br.forum.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.forum.dtos.CourseDto;
import com.br.forum.exceptions.CourseNotFoundException;
import com.br.forum.mappers.CourseMapper;
import com.br.forum.models.Course;
import com.br.forum.repository.CourseRepository;
import com.br.forum.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMapper courseMapper;

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course save(CourseDto form) {
		var model = courseMapper.toModel(form);

		return courseRepository.save(model);
	}

	public Course findById(Long id) {
		var courseFound = courseRepository.findById(id);

		if (courseFound.isPresent()) {
			return courseFound.get();
		}

		var message = String.format("Course with ID %d not found", id);
		throw new CourseNotFoundException(message);
	}

	public Course update(CourseDto form, Long id) {
		var courseFound = findById(id);

		var model = courseMapper.toModel(form);
		((Course) model).setId(courseFound.getId());

		return courseRepository.save(model);
	}

	public void deleteById(Long id) {
		var courseFound = findById(id);

		courseRepository.delete(courseFound);
	}
}
