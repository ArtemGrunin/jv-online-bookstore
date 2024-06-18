package com.example.jvonlinebookstore.repository;

import com.example.jvonlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<Book, Long> {
}
