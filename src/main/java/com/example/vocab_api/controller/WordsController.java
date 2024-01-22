package com.example.vocab_api.controller;

import com.example.vocab_api.dto.WordCreateDto;
import com.example.vocab_api.exception.AlreadyExistsException;
import com.example.vocab_api.exception.NotFoundException;
import com.example.vocab_api.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/words")
public class WordsController {
    private final WordService service;

    @Autowired
    public WordsController(WordService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> read(@RequestParam(required = false) Long deckId) {
        if (deckId != null) {
            return ResponseEntity.ok().body(service.getAll(deckId));
        }
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody WordCreateDto model) {
        try {
            return ResponseEntity.ok().body(service.add(model));
        } catch (NotFoundException | AlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().body("something went wrong");
        }
    }
}
