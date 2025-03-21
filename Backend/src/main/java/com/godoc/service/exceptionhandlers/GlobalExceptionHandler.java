package com.godoc.service.exceptionhandlers;

import com.godoc.service.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

//@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    String baseType = "https://httpstatuses.com/";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        log.error(e.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create(baseType+HttpStatus.NOT_FOUND.value()));

        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e){
        log.error("{} {}", e.getClass().getName(), e.getMessage());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
        problemDetail.setType(URI.create(baseType+HttpStatus.SERVICE_UNAVAILABLE.value()));

        return new ResponseEntity<>(problemDetail, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
