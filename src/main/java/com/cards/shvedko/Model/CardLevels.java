package com.cards.shvedko.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LEVELS")
public class CardLevels extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "is_visible", length = 1, nullable = false)
    private int isVisible;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "levels", targetEntity = Cards.class)
    private List<Cards> cardsRecords = new ArrayList<>(
            0);

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "levels", targetEntity = Decks.class)
//    private List<Decks> levelDecks = new ArrayList<>(
//            0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLevels() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLevels(Integer id, String name, int isVisible) {
        this.setId(id);
        this.setName(name);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLevels(String name, int isVisible) {
        this.setId(-1);
        this.setName(name);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLevels(String name) {
        this.setId(-1);
        this.setName(name);
        this.setIsVisible(1);
    }

    @SuppressWarnings("UnusedDeclaration")
    public List<Cards> getCardsRecords() {
        return cardsRecords;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setCardsRecords(List<Cards> cardsRecords) {
        this.cardsRecords = cardsRecords;
    }
//
//    public List<Decks> getLevelDecks() {
//        return levelDecks;
//    }
//
//    public void setLevelDecks(List<Decks> levelDecks) {
//        this.levelDecks = levelDecks;
//    }
}
