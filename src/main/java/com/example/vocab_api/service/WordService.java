package com.example.vocab_api.service;

import com.example.vocab_api.dto.WordCreateDto;
import com.example.vocab_api.dto.WordReadDto;
import com.example.vocab_api.entity.DeckEntity;
import com.example.vocab_api.entity.WordEntity;
import com.example.vocab_api.exception.AlreadyExistsException;
import com.example.vocab_api.exception.NotFoundException;
import com.example.vocab_api.mapper.WordMapper;
import com.example.vocab_api.repository.DeckRepository;
import com.example.vocab_api.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final DeckRepository deckRepository;


    @Autowired
    public WordService(WordRepository wordRepository, DeckRepository deckRepository) {
        this.wordRepository = wordRepository;
        this.deckRepository = deckRepository;
    }

    public WordReadDto add(WordCreateDto model) throws NotFoundException, AlreadyExistsException {


        Optional<DeckEntity> deck = deckRepository.findById(model.getDeckId());

        if (deck.isEmpty()) {
            throw new NotFoundException("you must provide correct deck id.");
        }

        WordEntity candidate = wordRepository.findOneByName(model.getName());

        if (candidate != null) {
            throw new AlreadyExistsException(String.format("word '%s' already exists. provide another word", model.getName()));
        }

        WordEntity word = WordMapper.toEntity(model);
        word.setDeck(deck.get());

        return WordMapper.toReadDto(wordRepository.save(word));
    }

    public Long removeById(Long id) throws NotFoundException {
        Optional<WordEntity> entity = wordRepository.findById(id);

        if (entity.isEmpty()) {
            throw new NotFoundException(String.format("word with id: %s not found", id));
        }

        wordRepository.deleteById(id);
        return id;
    }

    public Iterable<WordReadDto> getAll() {
        return StreamSupport
                .stream(wordRepository
                        .findAll()
                        .spliterator(), false)
                .map(WordMapper::toReadDto)
                .collect(Collectors.toList());
    }

    public Iterable<WordReadDto> getAll(Long deckId) {
        return wordRepository
                .findByDeckId(deckId)
                .stream()
                .map(WordMapper::toReadDto)
                .collect(Collectors.toList());
    }
}
