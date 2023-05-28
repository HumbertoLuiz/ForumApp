package com.br.forum.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.forum.dtos.ReplyDto;
import com.br.forum.models.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	Set<Reply> findByMessage(String message);

    Reply save(ReplyDto replyDto);

}
