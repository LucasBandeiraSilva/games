package com.lucas.bandeira.games.games.controller;

import com.lucas.bandeira.games.games.exception.GameCreationDataInvalidException;
import com.lucas.bandeira.games.games.exception.GameNotFoundException;
import com.lucas.bandeira.games.games.infra.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<RestErrorMessage>notFound(GameNotFoundException exception, HttpServletRequest request){
        var errorMessage = RestErrorMessage.builder()
                .data(Instant.now())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .error("Game not found")
                .message(exception.getMessage())
                .path("http://localhost:8080" + request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(GameCreationDataInvalidException.class)
    public ResponseEntity<RestErrorMessage>creationError(GameCreationDataInvalidException exception, HttpServletRequest request){
        var errorMessage = RestErrorMessage.builder()
                .data(Instant.now())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Invalid fields")
                .message(exception.getMessage())
                .path("http://localhost:8080" + request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
