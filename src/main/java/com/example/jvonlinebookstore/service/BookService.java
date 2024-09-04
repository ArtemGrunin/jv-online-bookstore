package com.example.jvonlinebookstore.service;

import com.example.jvonlinebookstore.openapi.model.dto.BookDto;
import com.example.jvonlinebookstore.openapi.model.dto.BookSearchParametersDto;
import com.example.jvonlinebookstore.openapi.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.openapi.model.dto.UpdateBookRequestDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto request);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    BookDto update(Long id, UpdateBookRequestDto dto);

    void delete(Long id);

    List<BookDto> search(BookSearchParametersDto parameters);
}
