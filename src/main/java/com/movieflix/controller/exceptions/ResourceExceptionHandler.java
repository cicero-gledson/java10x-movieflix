package com.movieflix.controller.exceptions;

import com.movieflix.service.exceptions.ResourceNotFoundException;
import com.movieflix.service.exceptions.UsernameOrPasswordInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.util.List;

//@ControllerAdvice
@RestControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);
    }

  //  Forma mais simples de manipular a exception
//    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleUserNotFoundException(UsernameOrPasswordInvalidException e) {
//        return e.getMessage();
//    }
    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<StandardError> invalidCredentials(UsernameOrPasswordInvalidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setError("Invalid username or password");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setError("Validation failed");
        err.setMessage(e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).toList().toString());
        err.setPath(request.getRequestURI());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);



    }

}
