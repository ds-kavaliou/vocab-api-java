package com.example.vocab_api.controller;

import com.example.vocab_api.dto.DeckCreateDto;
import com.example.vocab_api.exception.AlreadyExistsException;
import com.example.vocab_api.exception.NotFoundException;
import com.example.vocab_api.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decks")
public class DecksController {
    private final DeckService service;

    public DecksController(DeckService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> read() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(service.getById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("something went wrong");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeckCreateDto model) {
        try {
            return ResponseEntity.ok().body(service.add(model));
        } catch(AlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("something went wrong.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(service.removeById(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("something went wrong.");
        }
    }
}
