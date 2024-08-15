package com.example.jvonlinebookstore.service.impl;

import com.example.jvonlinebookstore.exception.BookAlreadyExistsException;
import com.example.jvonlinebookstore.exception.BookNotFoundException;
import com.example.jvonlinebookstore.mapper.BookMapper;
import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.model.dto.BookDto;
import com.example.jvonlinebookstore.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.repository.JpaBookRepository;
import com.example.jvonlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final JpaBookRepository repository;
    private final BookMapper mapper;

    @Override
    public BookDto save(CreateBookRequestDto request) {
        if (isBookExists(request)) {
            throw new BookAlreadyExistsException(
                    "Book with isbn %s is already exists.".formatted(request.getIsbn()));
        }
        Book book = mapper.toModel(request);
        return mapper.toDto(repository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Cannot find Book with id: %s"
                        .formatted(id)));
        return mapper.toDto(book);
    }

    private boolean isBookExists(CreateBookRequestDto request) {
        return repository.findByIsbn(request.getIsbn()).isPresent();
    }
}
