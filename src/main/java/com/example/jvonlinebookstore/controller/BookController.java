package com.example.jvonlinebookstore.controller;

import com.example.jvonlinebookstore.model.dto.BookDto;
import com.example.jvonlinebookstore.model.dto.BookSearchParametersDto;
import com.example.jvonlinebookstore.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.model.dto.UpdateBookRequestDto;
import com.example.jvonlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(
            @PathVariable Long id,
            @RequestBody UpdateBookRequestDto request) {
        log.debug("Request for update book: {}", request);
        BookDto bookDto = bookService.update(id, request);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Request for delete book: {}", id);
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> search(@RequestBody(required = false)
                                                    BookSearchParametersDto parametersDto) {
        log.debug("Request for search book by parameters: {}", parametersDto);
        List<BookDto> bookDtos = bookService.search(parametersDto);
        return ResponseEntity.ok(bookDtos);
    }
}
