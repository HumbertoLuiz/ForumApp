package com.br.forum.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.forum.dtos.TopicDetailsDto;
import com.br.forum.dtos.TopicDto;
import com.br.forum.dtos.TopicUpdateDto;
import com.br.forum.models.Topic;
import com.br.forum.services.TopicService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topics")
public class TopicRestController {

    @Autowired
    private TopicService topicService;

    // -------------------------------------------------------------------------------------------//

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @GetMapping
    @Cacheable(value= "topicsList")
    public Page<TopicDto> list(@RequestParam(required= false) String course,
        @PageableDefault(sort= "creationDate", direction= Sort.Direction.DESC, page= 0, size= 10) Pageable pageable) {
        return topicService.list(course, pageable);
    }

    // -------------------------------------------------------------------------------------------//

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody @Valid TopicDto form) {
        var topic= topicService.save(form);
        URI uri= URI.create("/topics/" + topic.getId());
        return ResponseEntity.created(uri).body(topic);
    }

    // -------------------------------------------------------------------------------------------//

    @RolesAllowed({ "ROLE_AUTHOR" })
    @GetMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<TopicDetailsDto> detail(@PathVariable Long id) {
        var topic= topicService.detail(id);
        return ResponseEntity.ok(topic);
    }

    // -------------------------------------------------------------------------------------------//

    @RolesAllowed({ "ROLE_AUTHOR", "ROLE_ADMIN" })
    @PutMapping("/{id}")
    @ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable Long id,
        @RequestBody @Valid TopicUpdateDto form) {
        var topic= topicService.update(id, form);
        return ResponseEntity.ok(topic);
    }

    // -------------------------------------------------------------------------------------------//

    @RolesAllowed({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public ResponseEntity<Topic> deleteById(@PathVariable Long id) {
        topicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
