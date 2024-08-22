package com.example.jvonlinebookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchParametersDto {
    private String[] titles;
    private String[] authors;
    private String[] isbns;
}
