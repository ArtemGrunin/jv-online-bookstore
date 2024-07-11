package com.example.jvonlinebookstore.mapper;

import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.model.dto.BookDto;
import com.example.jvonlinebookstore.model.dto.CreateBookRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);
}
