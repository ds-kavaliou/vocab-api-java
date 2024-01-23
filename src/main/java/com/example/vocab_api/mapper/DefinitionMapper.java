package com.example.vocab_api.mapper;

import com.example.vocab_api.dto.DefinitionCreateDto;
import com.example.vocab_api.dto.DefinitionReadDto;
import com.example.vocab_api.entity.DefinitionEntity;

public class DefinitionMapper {
    public static DefinitionEntity toEntity(DefinitionCreateDto model) {
        DefinitionEntity entity = new DefinitionEntity();
        entity.setText(model.getText());
        return entity;
    }

    public static DefinitionReadDto toReadDto(DefinitionEntity entity) {
        DefinitionReadDto model = new DefinitionReadDto();
        model.setId(entity.getId());
        model.setText(entity.getText());
        model.setWordId(entity.getWord().getId());
        return model;
    }
}
