package com.example.jvonlinebookstore.repository;

import com.example.jvonlinebookstore.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}
