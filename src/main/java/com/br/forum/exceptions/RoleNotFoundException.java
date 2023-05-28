package com.br.forum.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(String msg) {
		super(msg);
	}
}
