package com.example.vocab_api.dto;

import com.example.vocab_api.entity.WordEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WordReadDto {
    private Long id;
    private String name;
    private Long deckId;
    private List<String> definitions = new ArrayList<String>();
    private WordEntity.WordState state;
}
