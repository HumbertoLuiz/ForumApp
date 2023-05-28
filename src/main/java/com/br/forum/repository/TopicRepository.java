package com.br.forum.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.forum.dtos.TopicDto;
import com.br.forum.models.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	Set<Topic> findByTitle(String title);

    Page<Topic> findByCourses(String Courses, Pageable pageable);

    Topic save(TopicDto topicDto);

}
