package com.example.jvonlinebookstore.service.impl;

import com.example.jvonlinebookstore.exception.BookAlreadyExistsException;
import com.example.jvonlinebookstore.exception.BookNotFoundException;
import com.example.jvonlinebookstore.mapper.BookMapper;
import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.openapi.model.dto.BookDto;
import com.example.jvonlinebookstore.openapi.model.dto.BookSearchParametersDto;
import com.example.jvonlinebookstore.openapi.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.openapi.model.dto.UpdateBookRequestDto;
import com.example.jvonlinebookstore.repository.book.BookSpecificationBuilder;
import com.example.jvonlinebookstore.repository.book.JpaBookRepository;
import com.example.jvonlinebookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final JpaBookRepository repository;
    private final BookMapper mapper;
    private final BookSpecificationBuilder specificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto request) {
        try {
            Book book = mapper.toModel(request);
            return mapper.toDto(repository.save(book));
        } catch (DataIntegrityViolationException ex) {
            throw new BookAlreadyExistsException(
                    "Book with isbn %s already exists.".formatted(request.getIsbn()), ex);
        }
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).stream()
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

    @Override
    public BookDto update(Long id, UpdateBookRequestDto dto) {
        Book book = isBookPresent(id);
        mapper.updateBookFromDto(dto, book);
        try {
            book = repository.save(book);
            return mapper.toDto(book);
        } catch (DataIntegrityViolationException ex) {
            throw new BookAlreadyExistsException(
                    "Another book with isbn %s already exists.".formatted(dto.getIsbn()), ex);
        }
    }

    @Override
    public void delete(Long id) {
        isBookPresent(id);
        repository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto parameters) {
        Specification<Book> specification = specificationBuilder.build(parameters);
        return repository.findAll(specification).stream()
                .map(mapper::toDto)
                .toList();
    }

    private Book isBookPresent(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Cannot find Book with id: %s"
                        .formatted(id)));
    }
}
