package com.project.contactlist.application.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ContactListExceptionHandler extends ResponseEntityExceptionHandler{

    private final MessageSource messageSource;

    @Autowired
    public ContactListExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Error> erros = createErrorMessagBody(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.menssage", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ GenericException.class })
    public ResponseEntity<Object> handleGenericException(GenericException ex, WebRequest request) {
        String userMessage = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<Error> erros = Collections.singletonList(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("contact.notFound", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<Error> erros = Collections.singletonList(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<Error>createErrorMessagBody(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            erros.add(new Error(userMessage, developerMessage));
        }

        return erros;
    }

    public static class Error {

        private String userMessage;
        private String developerMessage;

        public Error (String messageUser, String messageDeveloper) {
            this.userMessage = messageUser;
            this.developerMessage = messageDeveloper;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

    }
}