package com.example.jvonlinebookstore.repository.book.spec;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.repository.SpecificationProvider;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "isbn";
    }

    @Override
    public Specification<Book> getSpecification(List<String> params) {
        return (root, query, criteriaBuilder) -> root.get("isbn")
                .in(params);
    }
}
