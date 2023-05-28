package com.br.forum.exceptions;

public class BusinessRuleException extends RuntimeException {

    private static final long serialVersionUID= 1L;

    public BusinessRuleException(String message) {
        super(message);
    }
}