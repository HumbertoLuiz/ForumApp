package com.br.forum.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.forum.exceptions.BusinessRuleException;
import com.br.forum.exceptions.TopicNotFoundException;

@RestControllerAdvice
public class ApiErrorAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleReRegraNegocioException(BusinessRuleException ex) {
        String errorMessage= ex.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiErrors> handle(MethodArgumentNotValidException exception) {
        List<ApiErrors> dto= new ArrayList<>();

        List<FieldError> fieldErrors= exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String errorMessage= messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ApiErrors errors= new ApiErrors(e.getField(), errorMessage);
            dto.add(errors);
        });
        return dto;
    }

    @ExceptionHandler(TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNaoEncontradoException(TopicNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

}