package com.example.vocab_api.repository;

import com.example.vocab_api.entity.DeckEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<DeckEntity, Long> {
    DeckEntity findOneByName(String name);
}
