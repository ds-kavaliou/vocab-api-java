package com.example.vocab_api.repository;

import com.example.vocab_api.entity.WordEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<WordEntity, Long> {
    WordEntity findOneByName(String name);
    List<WordEntity> findByDeckId(Long deckId);
}
