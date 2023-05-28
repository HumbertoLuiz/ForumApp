package com.br.forum.validation;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiErrors {

    private String field;

    private List<String> errors;

    private String errorMessage;

    public ApiErrors(String field, String errorMessage) {
        this.field= field;
        this.errorMessage= errorMessage;
    }

    public ApiErrors(List<String> errors) {
        this.errors= errors;
    }

    public ApiErrors(String errorMessage) {
        this.errors= Arrays.asList(errorMessage);
    }

    public String getField() {
        return field;
    }

    public List<String> getErrors() {
        return errors;
    }

}