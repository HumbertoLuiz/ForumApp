package com.br.forum.dtos;

import com.br.forum.models.Topic;
import com.br.forum.repository.TopicRepository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TopicUpdateDto {

    @NotNull
    private String title;

    @NotEmpty
    private String message;

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topics= topicRepository.getReferenceById(id);
        topics.setTitle(this.title);
        topics.setMessage(this.message);
        return topics;
    }
}