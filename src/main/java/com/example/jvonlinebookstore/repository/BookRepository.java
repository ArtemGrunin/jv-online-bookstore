package com.example.jvonlinebookstore.repository;

import com.example.jvonlinebookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
