package br.com.molar.produtos.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}