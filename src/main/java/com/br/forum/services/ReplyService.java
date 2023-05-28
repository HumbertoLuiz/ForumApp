package com.br.forum.services;

import java.util.List;

import com.br.forum.dtos.ReplyDto;
import com.br.forum.models.Reply;

public interface ReplyService {

	List<Reply> findAll();

	Reply save(ReplyDto form);

	Reply findById(Long id);

	Reply update(ReplyDto form, Long id);

	Reply deleteById(Long id);
}
