package com.br.forum.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ReplyNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ReplyNotFoundException(String msg) {
		super(msg);
	}
}
