package com.br.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.forum.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(Course courses);
}
