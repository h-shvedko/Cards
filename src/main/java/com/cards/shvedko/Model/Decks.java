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
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional=false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private Users user;

    @NotNull(message = "Is Visible field in cards table can't be empty!")
    @Min(value = 1, message = "Is Visible value of Card can't be less then 1!")
    @Column(name = "is_visible", nullable = false)
    private int isVisible;

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
}
