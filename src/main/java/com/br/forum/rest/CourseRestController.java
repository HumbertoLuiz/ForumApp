package com.br.forum.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.forum.dtos.CourseDto;
import com.br.forum.models.Course;
import com.br.forum.services.CourseService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @GetMapping
    public List<Course> findAll() {
        return courseService.findAll();
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<Course> create(@RequestBody @Valid CourseDto courseDto) {
        Course savedCourse= courseService.save(courseDto);
        URI courseURI= URI.create("/courses/" + savedCourse.getId());
        return ResponseEntity.created(courseURI).body(savedCourse);
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        Course course= courseService.findById(id);
        return ResponseEntity.ok(course);
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@RequestBody @Valid CourseDto courseDto,
        @PathVariable Long id) {
        Course course= courseService.update(courseDto, id);

        return ResponseEntity.ok(course);
    }

    @RolesAllowed({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
