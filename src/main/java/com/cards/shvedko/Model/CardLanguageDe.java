package com.cards.shvedko.Model;

import javax.persistence.*;

@Entity
@Table(name = "LANGUAGE_DE")
public class CardLanguageDe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "card_id", nullable = false, length = 10)
    private int cardId;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "file_id", length = 10)
    private int fileId;

    @Column(name = "is_visible", length = 1)
    private int isVisible;

    @SuppressWarnings("UnusedDeclaration")
    public CardLanguageDe() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLanguageDe(Integer id, int cardId, String value, int fileId, int isVisible) {
        this.setId(id);
        this.setCardId(cardId);
        this.setValue(value);
        this.setFileId(fileId);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardLanguageDe(int cardId, String value, int fileId, int isVisible) {
        this.setId(-1);
        this.setCardId(cardId);
        this.setValue(value);
        this.setFileId(fileId);
        this.setIsVisible(isVisible);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }
}
