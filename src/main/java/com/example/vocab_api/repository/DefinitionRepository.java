package com.example.vocab_api.repository;

import com.example.vocab_api.entity.DefinitionEntity;
import org.springframework.data.repository.CrudRepository;

public interface DefinitionRepository extends CrudRepository<DefinitionEntity, Long> {
}
