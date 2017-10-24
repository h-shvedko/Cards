package com.cards.shvedko.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TYPES")
public class CardTypes extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "is_visible", length = 1, nullable = false)
    private int isVisible;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", targetEntity = Cards.class)
    private List<Cards> typeRecords = new ArrayList<>(
            0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", targetEntity = Decks.class)
    private List<Decks> typeDecks = new ArrayList<>(
            0);

    @SuppressWarnings("UnusedDeclaration")
    public CardTypes() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardTypes(Integer id, String name, int isVisible) {
        this.setId(id);
        this.setName(name);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardTypes(String name, int isVisible) {
        this.setId(-1);
        this.setName(name);
        this.setIsVisible(isVisible);
    }

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
    public List<Cards> getTypeRecords() {
        return typeRecords;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setTypeRecords(List<Cards> typeRecords) {
        this.typeRecords = typeRecords;
    }

    public List<Decks> getTypeDecks() {
        return typeDecks;
    }

    public void setTypeDecks(List<Decks> typeDecks) {
        this.typeDecks = typeDecks;
    }
}
