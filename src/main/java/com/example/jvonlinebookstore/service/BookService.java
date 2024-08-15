package com.example.jvonlinebookstore.service;

import com.example.jvonlinebookstore.model.dto.BookDto;
import com.example.jvonlinebookstore.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.model.dto.UpdateBookRequestDTO;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto request);

    List<BookDto> findAll();

    BookDto findById(Long id);

    BookDto update(Long id, UpdateBookRequestDTO dto);

    void delete(Long id);
}
