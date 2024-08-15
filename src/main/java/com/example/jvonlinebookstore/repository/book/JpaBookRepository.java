package com.example.jvonlinebookstore.repository.book;

import com.example.jvonlinebookstore.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {
    Optional<Book> findByIsbn(String isbn);
}
