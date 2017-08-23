package com.cards.shvedko.Model;

import javax.persistence.*;

@Entity
@Table(name = "FILES_DE")
public class CardFilesDe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "card_id", nullable = false, length = 1)
    private int cardId;

    @Column(name = "lang_id", nullable = false, length = 1)
    private int langId;

    @Column(name = "path_to_value_file", unique = true, nullable = false)
    private String pathToValueFile;

    @Column(name = "path_to_example_file", unique = true, nullable = false)
    private String pathToExampleFile;

    @Column(name = "path_to_verb_infinitive_file", unique = true, nullable = false)
    private String pathToVerbInfinitiveFile;

    @Column(name = "path_to_verb_presance_file", unique = true, nullable = false)
    private String pathToVerbPresanceFile;

    @Column(name = "path_to_verb_preteritum_file", unique = true, nullable = false)
    private String pathToVerbPreteritumFile;

    @Column(name = "path_to_verb_perfect_file", unique = true, nullable = false)
    private String pathToVerbPerfectFile;

    @Column(name = "is_visible", length = 1, nullable = false)
    private int isVisible;

    @SuppressWarnings("UnusedDeclaration")
    public CardFilesDe() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardFilesDe(Integer id, int cardId, int langId, int isVisible) {
        this.setId(id);
        this.setCardId(cardId);
        this.setLangId(langId);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public CardFilesDe(int cardId, int langId, int isVisible) {
        this.setId(-1);
        this.setCardId(cardId);
        this.setLangId(langId);
        this.setIsVisible(isVisible);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPathToValueFile() {
        return pathToValueFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPathToValueFile(String pathToValueFile) {
        this.pathToValueFile = pathToValueFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPathToExampleFile() {
        return pathToExampleFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPathToExampleFile(String pathToExampleFile) {
        this.pathToExampleFile = pathToExampleFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPathToVerbInfinitiveFile() {
        return pathToVerbInfinitiveFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPathToVerbInfinitiveFile(String pathToVerbInfinitiveFile) {
        this.pathToVerbInfinitiveFile = pathToVerbInfinitiveFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPathToVerbPresanceFile() {
        return pathToVerbPresanceFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPathToVerbPresanceFile(String pathToVerbPresanceFile) {
        this.pathToVerbPresanceFile = pathToVerbPresanceFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPathToVerbPreteritumFile() {
        return pathToVerbPreteritumFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPathToVerbPreteritumFile(String pathToVerbPreteritumFile) {
        this.pathToVerbPreteritumFile = pathToVerbPreteritumFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPathToVerbPerfectFile() {
        return pathToVerbPerfectFile;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPathToVerbPerfectFile(String pathToVerbPerfectFile) {
        this.pathToVerbPerfectFile = pathToVerbPerfectFile;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }
}
