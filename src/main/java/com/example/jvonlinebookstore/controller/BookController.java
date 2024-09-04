package com.example.jvonlinebookstore.controller;

import com.example.jvonlinebookstore.openapi.api.BooksApi;
import com.example.jvonlinebookstore.openapi.model.dto.BookDto;
import com.example.jvonlinebookstore.openapi.model.dto.BookSearchParametersDto;
import com.example.jvonlinebookstore.openapi.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.openapi.model.dto.UpdateBookRequestDto;
import com.example.jvonlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController implements BooksApi {
    private final BookService bookService;

    @Override
    public ResponseEntity<BookDto> create(CreateBookRequestDto request) {
        log.debug("Request for create new book: {}", request);
        BookDto bookDto = bookService.save(request);
        return ResponseEntity.ok(bookDto);
    }

    @Override
    public ResponseEntity<List<BookDto>> getAll(Pageable pageable) {
        log.debug("Request for get books");
        List<BookDto> bookDtos = bookService.findAll(pageable);
        return ResponseEntity.ok(bookDtos);
    }

    @Override
    public ResponseEntity<BookDto> get(Long id) {
        log.debug("Request for get book: {}", id);
        BookDto bookDto = bookService.findById(id);
        return ResponseEntity.ok(bookDto);
    }

    @Override
    public ResponseEntity<BookDto> update(Long id, UpdateBookRequestDto request) {
        log.debug("Request for update book: {}", request);
        BookDto bookDto = bookService.update(id, request);
        return ResponseEntity.ok(bookDto);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        log.debug("Request for delete book: {}", id);
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<BookDto>> search(BookSearchParametersDto dto, Pageable pageable) {
        log.debug("Request for search book by parameters: {}", dto);
        List<BookDto> bookDtos = bookService.search(dto, pageable);
        return ResponseEntity.ok(bookDtos);
    }
}
