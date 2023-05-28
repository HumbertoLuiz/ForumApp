package com.br.forum.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.br.forum.enums.StatusTopic;
import com.br.forum.models.Topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicDetailsDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String author;
    private StatusTopic status;
    private Set<ReplyDto> replys;

    public TopicDetailsDto(Topic topics) {
        this.id= topics.getId();
        this.title= topics.getTitle();
        this.message= topics.getMessage();
        this.creationDate= topics.getCreationDate();
        this.author= topics.getAuthor().getName();
        this.status= topics.getStatus();
        this.replys= new HashSet<>();
    }

    public Set<ReplyDto> getReplys() {
        return replys;
    }
}