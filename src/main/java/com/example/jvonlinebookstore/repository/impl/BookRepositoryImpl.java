package com.example.jvonlinebookstore.repository.impl;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.repository.BookRepository;
import com.example.jvonlinebookstore.repository.JpaBookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JpaBookRepository jpaBookRepository;

    @Override
    public Book save(Book book) {
        return jpaBookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll();
    }
}
