package com.cards.shvedko.Model;

import javax.persistence.*;
import javax.smartcardio.Card;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class CardCategories extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "is_visible", length = 1, nullable = false)
    private int isVisible;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", targetEntity = Cards.class)
    private Set<Cards> cardsRecords = new HashSet<>(
            0);

    public Set<Cards> getCards() {
        return this.cardsRecords;
    }

    public void setCards(Set<Cards> cardsRecords) {
        this.cardsRecords = cardsRecords;
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
    public CardCategories() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardCategories(Integer id, String name, int isVisible) {
        this.setId(id);
        this.setName(name);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardCategories(String name, int isVisible) {
        this.setId(-1);
        this.setName(name);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardCategories(String name) {
        this.setId(-1);
        this.setName(name);
        this.setIsVisible(1);
    }
}
