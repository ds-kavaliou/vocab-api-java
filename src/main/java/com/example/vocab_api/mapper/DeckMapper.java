package com.example.vocab_api.mapper;

import com.example.vocab_api.dto.DeckCreateDto;
import com.example.vocab_api.dto.DeckReadDto;
import com.example.vocab_api.entity.DeckEntity;
import com.example.vocab_api.entity.WordEntity;

import java.util.stream.Collectors;

public class DeckMapper {
    public static DeckEntity toEntity(DeckCreateDto model) {
        DeckEntity deck = new DeckEntity();
        deck.setName(model.getName());
        return deck;
    }

    public static DeckReadDto toReadDto(DeckEntity entity) {
        DeckReadDto dto = new DeckReadDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setWords(
                entity
                        .getWords()
                        .stream()
                        .map(WordEntity::getName)
                        .collect(Collectors.toList()));

        return dto;
    }
}
