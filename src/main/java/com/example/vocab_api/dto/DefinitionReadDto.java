package com.example.vocab_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefinitionReadDto {
    private Long id;
    private String text;
    private Long wordId;
}
