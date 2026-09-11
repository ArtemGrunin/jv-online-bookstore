package com.example.jvonlinebookstore.mapper;

import com.example.jvonlinebookstore.config.MapperConfig;
import com.example.jvonlinebookstore.model.Book;
import com.example.jvonlinebookstore.openapi.model.dto.BookDto;
import com.example.jvonlinebookstore.openapi.model.dto.CreateBookRequestDto;
import com.example.jvonlinebookstore.openapi.model.dto.UpdateBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toModel(CreateBookRequestDto bookDto);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateBookFromDto(UpdateBookRequestDto dto, @MappingTarget Book book);
}
