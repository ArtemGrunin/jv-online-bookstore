package com.example.jvonlinebookstore.repository.book;

import com.example.jvonlinebookstore.exception.SpecificationProviderNotFoundException;
import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.repository.SpecificationProvider;
import com.example.jvonlinebookstore.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> specificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(()
                        -> new SpecificationProviderNotFoundException(
                                "Can't find correct SpecificationProvider for key: %s"
                                        .formatted(key)));
    }
}
