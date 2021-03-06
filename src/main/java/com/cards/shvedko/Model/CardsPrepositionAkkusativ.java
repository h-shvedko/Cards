package com.cards.shvedko.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PREPOSITION_AKKUSATIV")
public class CardsPrepositionAkkusativ extends A_Models implements Serializable {

    public static final String PREPOSITION_AKKUSATIVE_NO = "No";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @NotNull(message = "Value of preposition can't be empty!")
    @Size(min = 2, message = "Value of preposition can't be less then 2 symbols!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Is Visible field in preposition table can't be empty!")
    @Min(value = 1, message = "Is Visible value of preposition can't be less then 1!")
    @Column(name = "is_visible", nullable = false)
    private int isVisible;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "prepositionAkk", targetEntity = Cards.class)
    private List<Cards> prepositionAkkusativ = new ArrayList<>(
            0);

    @SuppressWarnings("UnusedDeclaration")
    public CardsPrepositionAkkusativ() {
    }

    @Override
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

    public List<Cards> getPrepositionAkkusativ() {
        return prepositionAkkusativ;
    }

    public void setPrepositionAkkusativ(List<Cards> prepositionAkkusativ) {
        this.prepositionAkkusativ = prepositionAkkusativ;
    }
}
