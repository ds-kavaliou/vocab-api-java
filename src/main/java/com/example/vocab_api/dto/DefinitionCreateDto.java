package com.example.vocab_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefinitionCreateDto {
    private String text;
    private Long wordId;
}
