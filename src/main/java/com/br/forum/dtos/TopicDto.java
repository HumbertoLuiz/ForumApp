package com.br.forum.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.br.forum.enums.StatusTopic;
import com.br.forum.models.Topic;
import com.br.forum.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {

    private Long id;

    @NotNull
	private String title;

    @NotNull
    @Lob
	private String message;

    private User author;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime creationDate= LocalDateTime.now();

	@Enumerated(EnumType.STRING)
    private StatusTopic status= StatusTopic.NOT_ANSWERED;

    public TopicDto(Topic topics) {
        this.id= topics.getId();
        this.title= topics.getTitle();
        this.message= topics.getMessage();
        this.author= topics.getAuthor();
        this.creationDate= topics.getCreationDate();
    }

    public static Page<TopicDto> convert(Page<Topic> topics) {
        return topics.map(TopicDto::new);
    }

}
