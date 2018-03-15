package com.cards.shvedko.Helpers;

import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import javafx.collections.ObservableList;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FillDatabase {

    public static void fillCardsFromCSV(List<String> content) throws UnsupportedEncodingException {

        if (content.size() > 0) {
            for (String line : content) {
                String[] values = line.split(",");

                CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
                A_Models categoryObject = null;
                try {
                    categoryObject = cardCategoriesDAO.select("where name='" + values[10] + "'");
                    if(categoryObject == null){
                        CardCategoriesDAO cardCategoriesEmptyDAO = new CardCategoriesDAO();
                        categoryObject = cardCategoriesEmptyDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                CardTypesDAO cardTypesDAO = new CardTypesDAO();
                A_Models typeObject = null;
                try {
                    typeObject = cardTypesDAO.select("where name='" + values[11] + "'");
                    if(typeObject == null){
                        CardTypesDAO cardTypesEmptyDAO = new CardTypesDAO();
                        typeObject = cardTypesEmptyDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                CardsPrepositionAkkusativDAO cardsPrepositionAkkusativDAO = new CardsPrepositionAkkusativDAO();
                A_Models akkObject = null;
                try {
                    akkObject = cardsPrepositionAkkusativDAO.select("where name='" + values[16] + "'");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                CardsPrepositionDativDAO cardsPrepositionDativDAO = new CardsPrepositionDativDAO();
                A_Models dativObject = null;
                try {
                    dativObject = cardsPrepositionDativDAO.select("where name='" + values[17] + "'");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


                TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();
                tmpCardsDAO.tmpCards.setName(values[1]);
                tmpCardsDAO.tmpCards.setForeignName(values[2]);
                tmpCardsDAO.tmpCards.setPluralEndung(values[3]);
                tmpCardsDAO.tmpCards.setForeignNameInfinitive(values[5]);
                tmpCardsDAO.tmpCards.setForeignNamePreteritum(values[6]);
                tmpCardsDAO.tmpCards.setForeignNamePerfect(values[7]);
                tmpCardsDAO.tmpCards.setExample(values[8]);
                tmpCardsDAO.tmpCards.setForeignExample(values[9]);

                if(categoryObject != null){
                    tmpCardsDAO.tmpCards.setCategory((CardCategories) categoryObject);
                }

                if(typeObject != null){
                    tmpCardsDAO.tmpCards.setType((CardTypes) typeObject);
                }

                tmpCardsDAO.tmpCards.setKindOfNoun(getNounType(values[12]));
                tmpCardsDAO.tmpCards.setIsPerfectWithHaben(getPerfectType(values[13]));
                tmpCardsDAO.tmpCards.setIsTrembarePrefixVerb(getPrefixType(values[14]));
                tmpCardsDAO.tmpCards.setIsRegularVerb(getVerbType(values[14]));
                tmpCardsDAO.tmpCards.setProceed(true);

                if (akkObject != null) {
                    tmpCardsDAO.tmpCards.setPrepositionAkk((CardsPrepositionAkkusativ) akkObject);
                }
                if (akkObject != null) {
                    tmpCardsDAO.tmpCards.setPrepositionDativ((CardsPrepositionDativ) dativObject);

                }
                if(values.length > 18){
                    tmpCardsDAO.tmpCards.setPrepositionGen(values[18]);
                }

                if (tmpCardsDAO.validate(tmpCardsDAO.tmpCards)) {
                    try {
                        if (!tmpCardsDAO.save()) {
                            throw new Exception(tmpCardsDAO.errorMsg);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showError(tmpCardsDAO);
                }
            }
        }
    }


    public static void fillMainTableFromTmpTable(ObservableList<TmpCards> content) throws UnsupportedEncodingException {

        if (content.size() > 0) {
            for (TmpCards values : content) {

                CardsDAO cardsDAO = new CardsDAO();
                cardsDAO.cards.setName(values.getName());
                cardsDAO.cards.setForeignName(values.getForeignName());
                cardsDAO.cards.setPluralEndung(values.getPluralEndung());
                cardsDAO.cards.setForeignNameInfinitive(values.getForeignNameInfinitive());
                cardsDAO.cards.setForeignNamePreteritum(values.getForeignNamePreteritum());
                cardsDAO.cards.setForeignNamePerfect(values.getForeignNamePerfect());
                cardsDAO.cards.setExample(values.getExample());
                cardsDAO.cards.setForeignExample(values.getForeignExample());
                cardsDAO.cards.setCategory(values.getCategory());
                cardsDAO.cards.setType(values.getType());
                cardsDAO.cards.setKindOfNoun(values.getKindOfNoun());
                cardsDAO.cards.setIsPerfectWithHaben(values.getIsPerfectWithHaben());
                cardsDAO.cards.setIsTrembarePrefixVerb(values.getIsTrembarePrefixVerb());
                cardsDAO.cards.setIsRegularVerb(values.getIsRegularVerb());
                cardsDAO.cards.setPrepositionAkk(values.getPrepositionAkk());
                cardsDAO.cards.setPrepositionDativ(values.getPrepositionDativ());
                cardsDAO.cards.setPrepositionGen(values.getPrepositionGen());

                if (cardsDAO.validate(cardsDAO.cards)) {
                    try {
                        if (!cardsDAO.save()) {
                            throw new Exception(cardsDAO.errorMsg);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showError(cardsDAO);
                }
            }
        }
    }

    private static void showError(ModelsDAO model){
        if (model.errorSet != null) {
            for (ConstraintViolation violation : model.errorSet) {
                Path wrongAttribute = violation.getPropertyPath();
                String message = violation.getMessage();
                if (wrongAttribute.iterator().hasNext()) {
                    for (Path.Node attribute : wrongAttribute) {
                        String nameOfAttrib = attribute.getName();
                        System.out.println(nameOfAttrib + "-----------" + message + "\n\n\n\n\n\n\n");
                    }
                }
            }
        }
    }

    private static int getNounType(String value) {
        int typeOfNounIntoDB;
        switch (value){
            case ModelsDAO.NEUTRUM:
                typeOfNounIntoDB = ModelsDAO.NEUTRUM_INTO_DB;
                break;
            case ModelsDAO.MUSKULINUM:
                typeOfNounIntoDB = ModelsDAO.MUSKULINUM_INTO_DB;
                break;
            case ModelsDAO.FEMININUM:
                typeOfNounIntoDB = ModelsDAO.FEMININUM_INTO_DB;
                break;
            default:
                typeOfNounIntoDB = ModelsDAO.MUSKULINUM_INTO_DB;

        }
        return typeOfNounIntoDB;
    }

    private static int getPerfectType(String value) {
        int typeOfPerfectIntoDB;
        switch (value) {
            case ModelsDAO.HABEN_PERFECT:
                typeOfPerfectIntoDB = ModelsDAO.HABEN_PERFECT_TO_DB;
                break;
            case ModelsDAO.SEIN_PERFECT:
                typeOfPerfectIntoDB = ModelsDAO.SEIN_PERFECT_TO_DB;
                break;
            default:
                typeOfPerfectIntoDB = ModelsDAO.HABEN_PERFECT_TO_DB;
                break;
        }

        return typeOfPerfectIntoDB;
    }

    private static int getVerbType(String value) {
        int typeOfVerbIntoDB;
        switch (value) {
            case ModelsDAO.REGELMESSIG_VERB:
                typeOfVerbIntoDB = ModelsDAO.REGELMESSIG_VERB_TO_DB;
                break;
            case ModelsDAO.UNREGELMESSIG_VERB:
                typeOfVerbIntoDB = ModelsDAO.UNREGELMESSIG_VERB_TO_DB;
                break;
            default:
                typeOfVerbIntoDB = ModelsDAO.REGELMESSIG_VERB_TO_DB;
                break;
        }

        return typeOfVerbIntoDB;
    }

    private static int getPrefixType(String value) {
        int typeOfPrefixIntoDB;
        switch (value) {
            case ModelsDAO.TREMBARE_PREFIX_VERB:
                typeOfPrefixIntoDB = ModelsDAO.TREMBARE_PREFIX_VERB_TO_DB;
                break;
            case ModelsDAO.UMTREMBARE_PREFIX_VERB_VERB:
                typeOfPrefixIntoDB = ModelsDAO.UMTREMBARE_PREFIX_VERB_VERB_TO_DB;
                break;
            default:
                typeOfPrefixIntoDB = ModelsDAO.TREMBARE_PREFIX_VERB_TO_DB;
                break;
        }

        return typeOfPrefixIntoDB;
    }
}
