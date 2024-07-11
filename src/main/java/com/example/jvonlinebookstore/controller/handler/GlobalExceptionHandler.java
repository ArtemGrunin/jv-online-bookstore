package com.example.jvonlinebookstore.controller.handler;

import com.example.jvonlinebookstore.exception.BookAlreadyExistsException;
import com.example.jvonlinebookstore.exception.BookNotFoundException;
import com.example.jvonlinebookstore.model.dto.ErrorDto;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.example.jvonlinebookstore")
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFoundException(BookNotFoundException e) {
        String errorId = buildErrorId();
        log.error("Book not found, id: {}, message: {}", errorId, e.getMessage(), e);
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), errorId);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleProfileAlreadyExistsException(
            BookAlreadyExistsException e) {
        String errorId = buildErrorId();
        log.error("Book already exists, id: {}", errorId, e);
        return buildErrorResponse(HttpStatus.CONFLICT, e.getMessage(), errorId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception e) {
        String errorId = buildErrorId();
        log.error("Internal server error, id: {}, message: {}", errorId, e.getMessage(), e);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error", errorId);
    }

    private ResponseEntity<ErrorDto> buildErrorResponse(
            HttpStatus status,
            String message,
            String errorId) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setId(errorId);
        errorDto.setMessage(message);

        return ResponseEntity
                .status(status)
                .body(errorDto);
    }

    private String buildErrorId() {
        return UUID.randomUUID().toString();
    }
}
