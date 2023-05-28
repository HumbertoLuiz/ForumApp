package com.br.forum.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.forum.dtos.ReplyDto;
import com.br.forum.exceptions.ReplyNotFoundException;
import com.br.forum.mappers.ReplyMapper;
import com.br.forum.models.Reply;
import com.br.forum.repository.ReplyRepository;
import com.br.forum.services.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private ReplyMapper replyMapper;

	public List<Reply> findAll() {
		return replyRepository.findAll();
	}
	
    public Reply findById(Long id) {
        var replyFound= replyRepository.findById(id);

        if (replyFound.isPresent()) { return replyFound.get(); }

        var message= String.format("Reply with ID %d not found", id);
        throw new ReplyNotFoundException(message);
    }

    public Reply save(ReplyDto form) {
        var model= replyMapper.toModel(form);

        return replyRepository.save(model);
    }

    public Reply update(ReplyDto form, Long id) {
        var replyFound= findById(id);

        var model= replyMapper.toModel(form);
        ((Reply) model).setId(replyFound.getId());

        return replyRepository.save(model);
    }

    public Reply deleteById(Long id) {
        var replyFound= findById(id);

        replyRepository.delete(replyFound);
        return replyFound;

    }
}
