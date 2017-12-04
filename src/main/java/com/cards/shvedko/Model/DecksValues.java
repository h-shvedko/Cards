package com.cards.shvedko.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DECKS_VALUES")
public class DecksValues extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "is_favorite", nullable = false)
    private int isFavorite;

    @Column(name = "is_anchor", nullable = false)
    private int isAnchor;

    @Column(name = "is_ready", nullable = false)
    private int isReady;

    @Column(name = "count_of_appearence", nullable = false)
    private int countOfAppearance;

    @Column(name = "order_in_card", nullable = false)
    private int orderInCard;

    @Column(name = "date_ready", nullable = false)
    private String dateReady;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "deck_id", referencedColumnName="id")
    private Decks decks;

    @ManyToOne(optional=false, cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
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

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getIsAnchor() {
        return isAnchor;
    }

    public void setIsAnchor(int isAnchor) {
        this.isAnchor = isAnchor;
    }

    public int getIsReady() {
        return isReady;
    }

    public void setIsReady(int isReady) {
        this.isReady = isReady;
    }

    public String getDateReady() {
        return dateReady;
    }

    public void setDateReady(String dateReady) {
        this.dateReady = dateReady;
    }

    public int getOrderInCard() {
        return orderInCard;
    }

    public void setOrderInCard(int orderInCard) {
        this.orderInCard = orderInCard;
    }

    public int getCountOfAppearance() {
        return countOfAppearance;
    }

    public void setCountOfAppearance(int countOfAppearance) {
        this.countOfAppearance = countOfAppearance;
    }
}
