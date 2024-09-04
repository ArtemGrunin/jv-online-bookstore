package com.example.jvonlinebookstore.model.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
    private String id;
    private List<String> messages;
}
