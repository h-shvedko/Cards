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

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public String getPathToValueFile() {
        return pathToValueFile;
    }

    public void setPathToValueFile(String pathToValueFile) {
        this.pathToValueFile = pathToValueFile;
    }

    public String getPathToExampleFile() {
        return pathToExampleFile;
    }

    public void setPathToExampleFile(String pathToExampleFile) {
        this.pathToExampleFile = pathToExampleFile;
    }

    public String getPathToVerbInfinitiveFile() {
        return pathToVerbInfinitiveFile;
    }

    public void setPathToVerbInfinitiveFile(String pathToVerbInfinitiveFile) {
        this.pathToVerbInfinitiveFile = pathToVerbInfinitiveFile;
    }

    public String getPathToVerbPresanceFile() {
        return pathToVerbPresanceFile;
    }

    public void setPathToVerbPresanceFile(String pathToVerbPresanceFile) {
        this.pathToVerbPresanceFile = pathToVerbPresanceFile;
    }

    public String getPathToVerbPreteritumFile() {
        return pathToVerbPreteritumFile;
    }

    public void setPathToVerbPreteritumFile(String pathToVerbPreteritumFile) {
        this.pathToVerbPreteritumFile = pathToVerbPreteritumFile;
    }

    public String getPathToVerbPerfectFile() {
        return pathToVerbPerfectFile;
    }

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
