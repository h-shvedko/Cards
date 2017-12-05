package com.cards.shvedko.Model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "name_voice", nullable = false)
    private String nameVoice;

    @NotNull(message = "Foreign word value of Card can't be empty!")
    @Size(min = 2, message = "Foreign word value of Card can't be less then 2 symbols!")
    @Column(name = "foreign_name", nullable = false)
    private String foreignName;

    @Column(name = "foreign_nama_infinitive")
    private String foreignNameInfinitive;

    @Column(name = "foreign_name_preteritum")
    private String foreignNamePreteritum;

    @Column(name = "foreign_name_perfect")
    private String foreignNamePerfect;

    @Column(name = "foreign_name_voice", nullable = false)
    private String foreignNameVoice;

    @Column(name = "example")
    private String example;

    @Column(name = "example_voice")
    private String exampleVoice;

    @Column(name = "foreign_example")
    private String foreignExample;

    @Column(name = "foreign_example_voice")
    private String foreignExampleVoice;

    @Column(name = "foreign_value_presense_voice")
    private String foreignValuePresenceVoice;

    @Column(name = "foreign_value_preteritum_voice")
    private String foreignValuePreteriturmVoice;

    @Column(name = "foreign_value_plural_voice")
    private String foreignValuePluralVoice;

    @Column(name = "foreign_value_perfect_voice")
    private String foreignValuePerfectVoice;

    @ManyToOne(optional=false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName="id")
    private CardCategories category;

    @ManyToOne(optional=false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName="id")
    private CardTypes type;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "preposition_akk", referencedColumnName="id")
    private CardsPrepositionAkkusativ prepositionAkk;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "preposition_dat", referencedColumnName="id")
    private CardsPrepositionDativ prepositionDativ;

    @ManyToOne(optional=false) //(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private Users user;

    @Column(name = "kind_of_noun", length = 1)
    private int kindOfNoun;

    @Column(name = "plural_endung")
    private String pluralEndung;

    @Column(name = "is_regular_verb", length = 1)
    private int isRegularVerb;

    @Column(name = "is_trembare_prefix_verb", length = 1)
    private int isTrembarePrefixVerb;

    @Column(name = "is_perfect_with_haben", length = 1)
    private int isPerfectWithHaben;

    @Column(name = "is_reflexiv_verb", length = 1)
    private int isReflexiveVerb;

//    @Column(name = "preposition_akk")
//    private String prepositionAkk;
//
//    @Column(name = "preposition_dat")
//    private String prepositionDat;

    @Column(name = "preposition_gen")
    private String prepositionGen;

    @NotNull(message = "Is Visible field in cards table can't be empty!")
    @Min(value = 1, message = "Is Visible value of Card can't be less then 1!")
    @Column(name = "is_visible", nullable = false)
    private int isVisible;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cards", targetEntity = DecksValues.class)
    private List<DecksValues> decksValues = new ArrayList<>(
            0);

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

    public String getForeignName() {
        return foreignName;
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setForeignName(String value) {
        this.foreignName = value;
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

//    @SuppressWarnings("UnusedDeclaration")
//    public String getPrepositionAkk() {
//        return prepositionAkk;
//    }
//
//    @SuppressWarnings("UnusedDeclaration")
//    public void setPrepositionAkk(String prepositionAkk) {
//        this.prepositionAkk = prepositionAkk;
//    }
//
//    @SuppressWarnings("UnusedDeclaration")
//    public String getPrepositionDat() {
//        return prepositionDat;
//    }
//
//    @SuppressWarnings("UnusedDeclaration")
//    public void setPrepositionDat(String prepositionDat) {
//        this.prepositionDat = prepositionDat;
//    }

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

    public String getForeignExample() {
        return foreignExample;
    }

    public void setForeignExample(String foreignExample) {
        this.foreignExample = foreignExample;
    }

    public String getPluralEndung() {
        return pluralEndung;
    }

    public void setPluralEndung(String pluralEndung) {
        this.pluralEndung = pluralEndung;
    }

    public CardsPrepositionDativ getPrepositionDativ() {
        return prepositionDativ;
    }

    public void setPrepositionDativ(CardsPrepositionDativ prepositionDativ) {
        this.prepositionDativ = prepositionDativ;
    }

    public CardsPrepositionAkkusativ getPrepositionAkk() {
        return prepositionAkk;
    }

    public void setPrepositionAkk(CardsPrepositionAkkusativ prepositionAkk) {
        this.prepositionAkk = prepositionAkk;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<DecksValues> getDecksValues() {
        return decksValues;
    }

    public void setDecksValues(List<DecksValues> decksValues) {
        this.decksValues = decksValues;
    }

    public String getNameVoice() {
        return nameVoice;
    }

    public void setNameVoice(String nameVoice) {
        this.nameVoice = nameVoice;
    }

    public String getForeignNameVoice() {
        return foreignNameVoice;
    }

    public void setForeignNameVoice(String foreignNameVoice) {
        this.foreignNameVoice = foreignNameVoice;
    }

    public String getExampleVoice() {
        return exampleVoice;
    }

    public void setExampleVoice(String exampleVoice) {
        this.exampleVoice = exampleVoice;
    }

    public String getForeignExampleVoice() {
        return foreignExampleVoice;
    }

    public void setForeignExampleVoice(String foreignExampleVoice) {
        this.foreignExampleVoice = foreignExampleVoice;
    }

    public String getForeignValuePresenceVoice() {
        return foreignValuePresenceVoice;
    }

    public void setForeignValuePresenceVoice(String foreignValuePresenceVoice) {
        this.foreignValuePresenceVoice = foreignValuePresenceVoice;
    }

    public String getForeignValuePreteriturmVoice() {
        return foreignValuePreteriturmVoice;
    }

    public void setForeignValuePreteriturmVoice(String foreignValuePreteriturmVoice) {
        this.foreignValuePreteriturmVoice = foreignValuePreteriturmVoice;
    }

    public String getForeignValuePerfectVoice() {
        return foreignValuePerfectVoice;
    }

    public void setForeignValuePerfectVoice(String foreignValuePerfectVoice) {
        this.foreignValuePerfectVoice = foreignValuePerfectVoice;
    }

    public String getForeignValuePluralVoice() {
        return foreignValuePluralVoice;
    }

    public void setForeignValuePluralVoice(String foreignValuePluralVoice) {
        this.foreignValuePluralVoice = foreignValuePluralVoice;
    }

    public String getForeignNamePreteritum() {
        return foreignNamePreteritum;
    }

    public void setForeignNamePreteritum(String foreignNamePreteritum) {
        this.foreignNamePreteritum = foreignNamePreteritum;
    }

    public String getForeignNamePerfect() {
        return foreignNamePerfect;
    }

    public void setForeignNamePerfect(String foreignNamePerfect) {
        this.foreignNamePerfect = foreignNamePerfect;
    }

    public String getForeignNameInfinitive() {
        return foreignNameInfinitive;
    }

    public void setForeignNameInfinitive(String foreignNameInfinitive) {
        this.foreignNameInfinitive = foreignNameInfinitive;
    }
}
