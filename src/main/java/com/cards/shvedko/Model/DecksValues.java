package com.cards.shvedko.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "DECKS_VALUES")
public class DecksValues extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @ManyToOne(optional=false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "deck_id", referencedColumnName="id")
    private Decks decks;

    @ManyToOne(optional=false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards_id", referencedColumnName="id")
    private Cards cards;

    @SuppressWarnings("UnusedDeclaration")
    public DecksValues() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Decks getDecks() {
        return decks;
    }

    public void setDecks(Decks decks) {
        this.decks = decks;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }
}
