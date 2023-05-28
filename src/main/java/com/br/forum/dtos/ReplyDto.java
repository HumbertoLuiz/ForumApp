package com.br.forum.dtos;

import java.time.LocalDateTime;

import com.br.forum.models.Reply;
import com.br.forum.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private Long id;

    @NotNull
	@Lob
	private String message;

    private User author;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime creationDate= LocalDateTime.now();

    private Boolean solution= false;

    public ReplyDto(Reply replys) {
        this.id= replys.getId();
        this.message= replys.getMessage();
        this.creationDate= replys.getCreationDate();
        this.author= replys.getAuthor();
        this.solution= replys.getSolution();
    }

}
