package com.example.vocab_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordCreateDto {
    private Long deckId;
    private String name;
}
