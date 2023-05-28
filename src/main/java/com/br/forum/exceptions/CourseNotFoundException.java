package com.br.forum.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class CourseNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String msg) {
		super(msg);
	}
}
