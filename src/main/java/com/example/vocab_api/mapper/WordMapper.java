package com.example.vocab_api.mapper;

import com.example.vocab_api.dto.WordCreateDto;
import com.example.vocab_api.dto.WordReadDto;
import com.example.vocab_api.entity.DefinitionEntity;
import com.example.vocab_api.entity.WordEntity;

import java.util.stream.Collectors;

public class WordMapper {
    public static WordReadDto toReadDto(WordEntity entity) {
        WordReadDto dto = new WordReadDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        dto.setDeckId(entity.getDeck().getId());
        dto.setState(entity.getState());
        dto.setDefinitions(entity
                .getDefinitions()
                .stream()
                .map(DefinitionEntity::getText).collect(Collectors.toList())
        );
        return dto;
    }

    public static WordEntity toEntity(WordCreateDto model) {
        WordEntity entity = new WordEntity();
        entity.setName(model.getName());
        return entity;
    }
}
