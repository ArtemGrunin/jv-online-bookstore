package com.example.jvonlinebookstore.service;

import com.example.jvonlinebookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
