package com.br.forum.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class TopicNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public TopicNotFoundException(String msg) {
		super(msg);
	}
}
