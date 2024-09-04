package com.example.jvonlinebookstore.controller.handler;

import com.example.jvonlinebookstore.exception.BookAlreadyExistsException;
import com.example.jvonlinebookstore.exception.BookNotFoundException;
import com.example.jvonlinebookstore.openapi.model.dto.ErrorDto;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.example.jvonlinebookstore")
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFoundException(BookNotFoundException e) {
        String errorId = buildErrorId();
        log.error("Book not found, id: {}, message: {}", errorId, e.getMessage(), e);
        return buildErrorResponse(HttpStatus.NOT_FOUND, List.of(e.getMessage()), errorId);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleProfileAlreadyExistsException(
            BookAlreadyExistsException e) {
        String errorId = buildErrorId();
        log.error("Book already exists, id: {}", errorId, e);
        return buildErrorResponse(HttpStatus.CONFLICT, List.of(e.getMessage()), errorId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException e) {
        String errorId = buildErrorId();
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();
        log.error("Validation error, id: {}, messages: {}", errorId, errorMessages, e);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessages, errorId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception e) {
        String errorId = buildErrorId();
        log.error("Internal server error, id: {}, message: {}", errorId, e.getMessage(), e);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                List.of("Internal server error"), errorId);
    }

    private ResponseEntity<ErrorDto> buildErrorResponse(
            HttpStatus status,
            List<String> messages,
            String errorId) {
        ErrorDto errorDto = buildErrorDto(messages, errorId);
        return ResponseEntity
                .status(status)
                .body(errorDto);
    }

    private ErrorDto buildErrorDto(List<String> messages, String errorId) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setId(errorId);
        errorDto.setMessages(messages);
        return errorDto;
    }

    private String buildErrorId() {
        return UUID.randomUUID().toString();
    }
}
