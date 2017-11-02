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
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private CardTypes type;

    @NotNull(message = "Is Visible field in cards table can't be empty!")
    @Column(name = "is_visible", nullable = false)
    private int isVisible;

    @NotNull(message = "Anchor field in Decks table can't be empty!")
    @Column(name = "is_anchor", nullable = false)
    private int isAnchore;

    @NotNull(message = "Favorite field in Decks table can't be empty!")
    @Column(name = "is_favorite", nullable = false)
    private int isFavorite;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "decks", targetEntity = DecksValues.class)
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
}
