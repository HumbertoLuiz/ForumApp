package com.br.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.forum.dtos.TopicDetailsDto;
import com.br.forum.dtos.TopicDto;
import com.br.forum.dtos.TopicUpdateDto;
import com.br.forum.models.Topic;

public interface TopicService {

    Page<TopicDto> list(String course, Pageable pageable);

    Topic save(TopicDto form);

    TopicDetailsDto detail(Long id);

    Topic update(Long id, TopicUpdateDto form);

    void deleteById(Long id);

}
