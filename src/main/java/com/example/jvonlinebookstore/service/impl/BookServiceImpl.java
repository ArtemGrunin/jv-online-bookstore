package com.example.jvonlinebookstore.service.impl;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.repository.BookRepository;
import com.example.jvonlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
