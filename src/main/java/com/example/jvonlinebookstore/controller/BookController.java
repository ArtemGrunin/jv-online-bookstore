package com.example.jvonlinebookstore.controller;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.model.dto.BookDto;
import com.example.jvonlinebookstore.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/books")
@RestController
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody CreateBookRequestDto request) {
        log.debug("Request for create new book: {}", request);
        BookDto bookDto = bookService.save(request);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        log.debug("Request for get books");
        List<BookDto> bookDtos = bookService.findAll();
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable Long id) {
        log.debug("Request for get book: {}", id);
        BookDto bookDto = bookService.findById(id);
        return ResponseEntity.ok(bookDto);
    }
}
