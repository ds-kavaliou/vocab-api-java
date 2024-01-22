package com.example.vocab_api.service;

import com.example.vocab_api.dto.DeckCreateDto;
import com.example.vocab_api.dto.DeckReadDto;
import com.example.vocab_api.entity.DeckEntity;
import com.example.vocab_api.exception.AlreadyExistsException;
import com.example.vocab_api.exception.NotFoundException;
import com.example.vocab_api.mapper.DeckMapper;
import com.example.vocab_api.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeckService {
    private final DeckRepository deckRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public DeckReadDto add(DeckCreateDto model) throws AlreadyExistsException {
        DeckEntity candidate = deckRepository.findOneByName(model.getName());

         if (candidate != null) {
             throw new AlreadyExistsException(String.format("deck with '%s' name already exists", model.getName()));
         }

         return DeckMapper.toReadDto(deckRepository.save(DeckMapper.toEntity(model)));
    }

    public Iterable<DeckReadDto> getAll() {
        return StreamSupport
                .stream(deckRepository
                        .findAll()
                        .spliterator(), false)
                .map(DeckMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public DeckReadDto getById(Long id) throws NotFoundException {

       Optional<DeckEntity> entity = deckRepository.findById(id);
       if (entity.isEmpty()) {
           throw new NotFoundException(String.format("deck with id:%s not found", id));
       }

       return DeckMapper.toReadDto(entity.get());
    }

    public long removeById(Long id) throws NotFoundException {
        Optional<DeckEntity> entity = deckRepository.findById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException(String.format("deck with id:%s not found", id));
        }

        deckRepository.deleteById(id);
        return id;
    }
}
