package com.example.jvonlinebookstore.repository.book;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.model.dto.BookSearchParametersDto;
import com.example.jvonlinebookstore.repository.SpecificationBuilder;
import com.example.jvonlinebookstore.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = Specification.where(null);
        if (searchParameters != null && searchParameters.getAuthors().length > 0) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider("author")
                    .getSpecification(searchParameters.getAuthors()));
        }
        if (searchParameters != null && searchParameters.getTitles().length > 0) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider("title")
                    .getSpecification(searchParameters.getTitles()));
        }
        if (searchParameters != null && searchParameters.getIsbns().length > 0) {
            specification = specification.and(specificationProviderManager
                    .getSpecificationProvider("isbn")
                    .getSpecification(searchParameters.getIsbns()));
        }
        return specification;
    }
}
