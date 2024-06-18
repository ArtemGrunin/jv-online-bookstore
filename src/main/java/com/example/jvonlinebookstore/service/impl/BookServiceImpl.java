package com.example.jvonlinebookstore.service.impl;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.repository.BookRepository;
import com.example.jvonlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        log.info("Creating book in DB: {}", book);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        log.info("Finding all books from DB");
        return bookRepository.findAll();
    }
}
