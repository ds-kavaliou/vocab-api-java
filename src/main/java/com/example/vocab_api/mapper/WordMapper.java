package com.example.vocab_api.mapper;

import com.example.vocab_api.dto.WordReadDto;
import com.example.vocab_api.entity.WordEntity;

public class WordMapper {
    public static WordReadDto toReadDto(WordEntity entity) {
        WordReadDto dto = new WordReadDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setDeckId(entity.getDeck().getId());
        dto.setState(entity.getState());
        return dto;
    }
}
