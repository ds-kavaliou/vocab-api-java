package com.example.vocab_api.controller;

import com.example.vocab_api.dto.DefinitionCreateDto;
import com.example.vocab_api.exception.NotFoundException;
import com.example.vocab_api.service.DefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/definitions")
public class DefinitionsController {
    private final DefinitionService service;
    @Autowired
    public DefinitionsController(DefinitionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DefinitionCreateDto model) {
        try {
            return ResponseEntity.ok().body(service.add(model));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam Long wordId) {
        return ResponseEntity.ok().body(service.getAll(wordId));
    }
}
