package com.example.vocab_api.dto;

import com.example.vocab_api.entity.WordEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DeckReadDto {
    private Long id;
    private String name;
    private List<String> words = new ArrayList<String>();
}
