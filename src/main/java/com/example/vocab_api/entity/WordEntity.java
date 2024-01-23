package com.example.vocab_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private DeckEntity deck;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    private List<DefinitionEntity> definitions = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    private WordState state = WordState.STATE1;

    public enum WordState {
        STATE1, STATE2, STATE3, STATE4, STATE5
    }
}
