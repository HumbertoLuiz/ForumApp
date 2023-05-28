package com.br.forum.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.br.forum.dtos.TopicDetailsDto;
import com.br.forum.dtos.TopicDto;
import com.br.forum.dtos.TopicUpdateDto;
import com.br.forum.exceptions.TopicNotFoundException;
import com.br.forum.mappers.TopicMapper;
import com.br.forum.models.Topic;
import com.br.forum.repository.TopicRepository;
import com.br.forum.services.TopicService;

import jakarta.transaction.Transactional;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    // -------------------------------------------------------------------------------------------//

    public Page<TopicDto> list(String courses, Pageable pageable) {
        if (courses == null) {
            Page<Topic> topics= topicRepository.findAll(pageable);
            return TopicDto.convert(topics);
        } else {
            Page<Topic> topics= topicRepository.findByCourses(courses, pageable);
            return TopicDto.convert(topics);
        }
    }

    // -------------------------------------------------------------------------------------------//

    @Transactional
    public Topic save(TopicDto form) {
        var topic= topicMapper.toModel(form);
        return topicRepository.save(topic);
    }

    // -------------------------------------------------------------------------------------------//

    public TopicDetailsDto detail(Long id) {
        Optional<Topic> topicFound= topicRepository.findById(id);
        return topicFound.map(value -> (new TopicDetailsDto(value)))
            .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    }

    // -------------------------------------------------------------------------------------------//

    @Transactional
    public Topic update(Long id, TopicUpdateDto form) {
        Optional<Topic> topicFound= topicRepository.findById(id);
        if (topicFound.isEmpty()) { throw new TopicNotFoundException("Topic not found"); }
        var model= topicMapper.toModel(form);
        ((Topic) model).setId(topicFound.get().getId());
        return topicRepository.save(new TopicDto(model));
    }

    // -------------------------------------------------------------------------------------------//

    @Transactional
    public void deleteById(Long id) {
        topicRepository
            .findById(id)
            .map(topic -> {
                topicRepository.delete(topic);
                return Void.TYPE;
            }).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

}
