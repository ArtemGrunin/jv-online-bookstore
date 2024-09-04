package com.example.jvonlinebookstore.repository;

import com.example.jvonlinebookstore.openapi.model.dto.BookSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParametersDto searchParameters);
}
