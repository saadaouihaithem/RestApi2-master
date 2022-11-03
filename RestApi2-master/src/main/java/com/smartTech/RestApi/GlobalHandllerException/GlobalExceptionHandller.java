package com.smartTech.RestApi.GlobalHandllerException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ControllerAdvice
public class GlobalExceptionHandller extends ResponseEntityExceptionHandler {



    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,

        HttpHeaders headers , HttpStatus status , WebRequest request){
    Map<String , Object> body = new LinkedHashMap<>();
    body.put("timestamp",System.currentTimeMillis());
    body.put("status",status.value());
    // get all the Errors

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x->x.getDefaultMessage())
                .collect(Collectors.toList() );
        body.put(PAGE_NOT_FOUND_LOG_CATEGORY,errors);

                body.put("errors",errors);
        return new ResponseEntity<>(body,status);

}}
