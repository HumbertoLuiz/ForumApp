package com.br.forum.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.forum.dtos.ReplyDto;
import com.br.forum.models.Reply;
import com.br.forum.services.ReplyService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/replys")
public class ReplyRestController {

	@Autowired
	private ReplyService replyService;

	@RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
	@GetMapping
    public List<Reply> findAll() {
        return replyService.findAll();
	}

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<Reply> create(@RequestBody @Valid ReplyDto replyDto) {
        Reply savedReply= replyService.save(replyDto);
        URI replyURI= URI.create("/replys/" + savedReply.getId());
        return ResponseEntity.created(replyURI).body(savedReply);
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @GetMapping("/{id}")
    public ResponseEntity<Reply> findById(@PathVariable Long id) {
        Reply reply= replyService.findById(id);
        return ResponseEntity.ok(reply);
    }

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PutMapping("/{id}")
    public ResponseEntity<Reply> update(@RequestBody @Valid ReplyDto replyDto,
        @PathVariable Long id) {
        Reply reply= replyService.update(replyDto, id);

        return ResponseEntity.ok(reply);
    }

    @RolesAllowed({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Reply> deleteById(@PathVariable Long id) {
        replyService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
