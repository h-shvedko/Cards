package com.cards.shvedko.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DECKS")
public class Decks extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @NotNull(message = "Native word value of Card can't be empty!")
    @Size(min = 2, message = "Native word value of Card can't be less then 2 symbols!")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne(optional = false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne(optional = false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CardCategories category;

    @ManyToOne(optional = false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private CardLevels levels;

    @ManyToOne(optional = false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private CardTypes type;

    @Column(name = "is_visible")
    private int isVisible;

    @Column(name = "is_anchor")
    private int isAnchore;

    @Column(name = "is_favorite")
    private int isFavorite;

    @Column(name = "trembare_prefix", nullable = false)
    private int trembarePrefix;

    @Column(name = "regelmassig", nullable = false)
    private int regelmessig;

    @Column(name = "reflexive", nullable = false)
    private int reflexive;

    @Column(name = "perfect", nullable = false)
    private int perfect;

    @Column(name = "preposition_akkusative", nullable = false)
    private int prepositionAkkusative;

    @Column(name = "preposition_dative", nullable = false)
    private int prepositionDative;

    @Column(name = "preposition_genetive", nullable = false)
    private int prepositionGenetive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "decks", targetEntity = DecksValues.class)
    private List<DecksValues> decksValues = new ArrayList<>(
            0);

    @SuppressWarnings("UnusedDeclaration")
    public Decks() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public List<DecksValues> getDecksValues() {
        return decksValues;
    }

    public void setDecksValues(List<DecksValues> decksValues) {
        this.decksValues = decksValues;
    }

    public int getIsAnchore() {
        return isAnchore;
    }

    public void setIsAnchore(int isAnchore) {
        this.isAnchore = isAnchore;
    }

    public CardCategories getCategory() {
        return category;
    }

    public void setCategory(CardCategories category) {
        this.category = category;
    }

    public CardTypes getType() {
        return type;
    }

    public void setType(CardTypes type) {
        this.type = type;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getTrembarePrefix() {
        return trembarePrefix;
    }

    public void setTrembarePrefix(int trembarePrefix) {
        this.trembarePrefix = trembarePrefix;
    }

    public int getRegelmessig() {
        return regelmessig;
    }

    public void setRegelmessig(int regelmessig) {
        this.regelmessig = regelmessig;
    }

    public int getReflexive() {
        return reflexive;
    }

    public void setReflexive(int reflexive) {
        this.reflexive = reflexive;
    }

    public int getPerfect() {
        return perfect;
    }

    public void setPerfect(int perfect) {
        this.perfect = perfect;
    }

    public int getPrepositionAkkusative() {
        return prepositionAkkusative;
    }

    public void setPrepositionAkkusative(int prepositionAkkusative) {
        this.prepositionAkkusative = prepositionAkkusative;
    }

    public int getPrepositionDative() {
        return prepositionDative;
    }

    public void setPrepositionDative(int prepositionDative) {
        this.prepositionDative = prepositionDative;
    }

    public int getPrepositionGenetive() {
        return prepositionGenetive;
    }

    public void setPrepositionGenetive(int prepositionGenetive) {
        this.prepositionGenetive = prepositionGenetive;
    }

    public CardLevels getLevels() {
        return levels;
    }

    public void setLevels(CardLevels levels) {
        this.levels = levels;
    }
}
