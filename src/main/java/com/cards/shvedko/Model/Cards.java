package com.cards.shvedko.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CARDS")
public class Cards extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @NotNull(message = "Native word value of Card can't be empty!")
    @Size(min = 2, message = "Native word value of Card can't be less then 2 symbols!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Foreign word value of Card can't be empty!")
    @Size(min = 2, message = "Foreign word value of Card can't be less then 2 symbols!")
    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "example")
    private String example;

    @NotNull(message = "You have chosen (or even haven't chosen any) wrong type of Card!")
    @Min(value = 1, message = "Type value of Card can't be less then 1!")
    @Column(name = "type_id", nullable = false)
    private int typeId;

    @NotNull(message = "You have chosen (or even haven't chosen any) wrong category of Card!")
    @Min(value = 1, message = "Category value of Card can't be less then 1!")
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private CardCategories category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId", nullable = false)
    private CardTypes type;

    @Column(name = "kind_of_noun", length = 1)
    private int kindOfNoun;

    @Column(name = "is_regular_verb", length = 1)
    private int isRegularVerb;

    @Column(name = "is_trembare_prefix_verb", length = 1)
    private int isTrembarePrefixVerb;

    @Column(name = "is_perfect_with_haben", length = 1)
    private int isPerfectWithHaben;

    @Column(name = "is_reflexiv_verb", length = 1)
    private int isReflexiveVerb;

    @Column(name = "preposition_akk")
    private String prepositionAkk;

    @Column(name = "preposition_dat")
    private String prepositionDat;

    @Column(name = "preposition_gen")
    private String prepositionGen;

    @NotNull(message = "Is Visible field in cards table can't be empty!")
    @Min(value = 1, message = "Is Visible value of Card can't be less then 1!")
    @Column(name = "is_visible", nullable = false)
    private int isVisible;

    @SuppressWarnings("UnusedDeclaration")
    public Cards() {
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

    public String getValue() {
        return value;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setValue(String value) {
        this.value = value;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getExample() {
        return example;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setExample(String example) {
        this.example = example;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getTypeId() {
        return typeId;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getCategoryId() {
        return categoryId;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getKindOfNoun() {
        return kindOfNoun;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setKindOfNoun(int kindOfNoun) {
        this.kindOfNoun = kindOfNoun;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getIsRegularVerb() {
        return isRegularVerb;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setIsRegularVerb(int isRegularVerb) {
        this.isRegularVerb = isRegularVerb;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getIsTrembarePrefixVerb() {
        return isTrembarePrefixVerb;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setIsTrembarePrefixVerb(int isTrembarePrefixVerb) {
        this.isTrembarePrefixVerb = isTrembarePrefixVerb;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getIsPerfectWithHaben() {
        return isPerfectWithHaben;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setIsPerfectWithHaben(int isPerfectWithHaben) {
        this.isPerfectWithHaben = isPerfectWithHaben;
    }

    @SuppressWarnings("UnusedDeclaration")
    public int getIsReflexiveVerb() {
        return isReflexiveVerb;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setIsReflexiveVerb(int isReflexiveVerb) {
        this.isReflexiveVerb = isReflexiveVerb;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPrepositionAkk() {
        return prepositionAkk;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPrepositionAkk(String prepositionAkk) {
        this.prepositionAkk = prepositionAkk;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPrepositionDat() {
        return prepositionDat;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPrepositionDat(String prepositionDat) {
        this.prepositionDat = prepositionDat;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPrepositionGen() {
        return prepositionGen;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setPrepositionGen(String prepositionGen) {
        this.prepositionGen = prepositionGen;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
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
}
