package com.example.jvonlinebookstore.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateBookRequestDto {
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;
    @Size(max = 255, message = "Author cannot be longer than 255 characters")
    private String author;
    @Size(max = 13, message = "ISBN cannot be longer than 13 characters")
    private String isbn;
    @Min(value = 0, message = "Price should be more than or equals 0")
    private BigDecimal price;
    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;
    @Size(max = 255, message = "Cover image URL cannot be longer than 255 characters")
    private String coverImage;
}
