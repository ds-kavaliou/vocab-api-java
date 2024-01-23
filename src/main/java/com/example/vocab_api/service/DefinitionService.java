package com.example.vocab_api.service;

import com.example.vocab_api.dto.DefinitionCreateDto;
import com.example.vocab_api.dto.DefinitionReadDto;
import com.example.vocab_api.entity.DefinitionEntity;
import com.example.vocab_api.entity.WordEntity;
import com.example.vocab_api.exception.NotFoundException;
import com.example.vocab_api.mapper.DefinitionMapper;
import com.example.vocab_api.repository.DefinitionRepository;
import com.example.vocab_api.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefinitionService {
    private final DefinitionRepository definitionRepository;
    private final WordRepository wordRepository;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository, WordRepository wordRepository) {
        this.definitionRepository = definitionRepository;
        this.wordRepository = wordRepository;
    }

    public DefinitionReadDto add(DefinitionCreateDto model) throws NotFoundException {
        Optional<WordEntity> word = wordRepository.findById(model.getWordId());
        if (word.isEmpty()) {
            throw new NotFoundException("provide correct word id.");
        }

        DefinitionEntity entity = DefinitionMapper.toEntity(model);
        entity.setWord(word.get());

        return DefinitionMapper.toReadDto(definitionRepository.save(entity));
    }

    public Iterable<DefinitionReadDto> getAll(Long wordId) {
        return definitionRepository
                .findByWordId(wordId)
                .stream()
                .map(DefinitionMapper::toReadDto)
                .collect(Collectors.toList());
    }
}
