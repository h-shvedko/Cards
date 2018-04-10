package com.cards.shvedko.Helpers;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FillDatabase extends A_Controller {

    public static void fillCardsFromCSV(List<String> content) throws UnsupportedEncodingException, ArrayIndexOutOfBoundsException, InterruptedException {

        int i = 0;
        if (content.size() > 0) {
            for (String line : content) {
                i++;
                List values;
                if(i % 200 == 0){
                    System.runFinalization();
                    System.gc();
                    Thread.sleep(60000);
                }

                TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();
                try {
                     values = Arrays.asList(line.split("\t"));

                    CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
                    A_Models categoryObject = null;
                    if (values.size() > 10) {
                        categoryObject = cardCategoriesDAO.select("where name='" + (String) values.get(10) + "'");
                        if (categoryObject == null) {
                            CardCategoriesDAO cardCategoriesEmptyDAO = new CardCategoriesDAO();
                            categoryObject = cardCategoriesEmptyDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
                        }
                    }

                    CardTypesDAO cardTypesDAO = new CardTypesDAO();
                    A_Models typeObject = null;
                    if (values.size() > 11) {
                        typeObject = cardTypesDAO.select("where name='" + (String)values.get(11) + "'");
                        if (typeObject == null) {
                            CardTypesDAO cardTypesEmptyDAO = new CardTypesDAO();
                            typeObject = cardTypesEmptyDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
                        }
                    }

                    A_Models akkObject = null;
                    if (values.size() > 16) {
                        CardsPrepositionAkkusativDAO cardsPrepositionAkkusativDAO = new CardsPrepositionAkkusativDAO();
                        akkObject = cardsPrepositionAkkusativDAO.select("where name='" + (String)values.get(16) + "'");
                    }

                    A_Models dativObject = null;
                    if (values.size() > 17) {
                        CardsPrepositionDativDAO cardsPrepositionDativDAO = new CardsPrepositionDativDAO();
                        dativObject = cardsPrepositionDativDAO.select("where name='" + (String)values.get(17) + "'");
                    }

                    if (values.size() > 1) {
                        tmpCardsDAO.tmpCards.setName((String) values.get(1));
                    }
                    if (values.size() > 2) {
                        tmpCardsDAO.tmpCards.setForeignName((String) values.get(2));
                    }
                    if (values.size() > 3) {
                        tmpCardsDAO.tmpCards.setPluralEndung((String) values.get(3));
                    }
                    if (values.size() > 5) {
                        tmpCardsDAO.tmpCards.setForeignNameInfinitive((String) values.get(5));
                    }
                    if (values.size() > 6) {
                        tmpCardsDAO.tmpCards.setForeignNamePreteritum((String) values.get(6));
                    }
                    if (values.size() > 7) {
                        tmpCardsDAO.tmpCards.setForeignNamePerfect((String) values.get(7));
                    }
                    if (values.size() > 8) {
                        tmpCardsDAO.tmpCards.setExample((String) values.get(8));
                    }
                    if (values.size() > 9) {
                        tmpCardsDAO.tmpCards.setForeignExample((String) values.get(9));
                    }

                    if (categoryObject != null) {
                        tmpCardsDAO.tmpCards.setCategory((CardCategories) categoryObject);
                    }

                    if (typeObject != null) {
                        tmpCardsDAO.tmpCards.setType((CardTypes) typeObject);
                    }
                    if (values.size() > 12) {
                        tmpCardsDAO.tmpCards.setKindOfNoun(getNounType((String) values.get(12)));
                    }
                    if (values.size() > 13) {
                        tmpCardsDAO.tmpCards.setIsPerfectWithHaben(getPerfectType((String) values.get(13)));
                    }
                    if (values.size() > 14) {
                        tmpCardsDAO.tmpCards.setIsTrembarePrefixVerb(getPrefixType((String) values.get(14)));
                    }
                    if (values.size() > 15) {
                        tmpCardsDAO.tmpCards.setIsRegularVerb(getVerbType((String) values.get(15)));
                    }
                    tmpCardsDAO.tmpCards.setProceed(true);

                    if (akkObject != null) {
                        tmpCardsDAO.tmpCards.setPrepositionAkk((CardsPrepositionAkkusativ) akkObject);
                    }
                    if (akkObject != null) {
                        tmpCardsDAO.tmpCards.setPrepositionDativ((CardsPrepositionDativ) dativObject);

                    }
                    if (values.size() > 18) {
                        tmpCardsDAO.tmpCards.setPrepositionGen((String) values.get(18));
                    }
                } catch (OutOfMemoryError out){
                    System.runFinalization();
                    System.gc();
                    Thread.sleep(60000);
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                if (tmpCardsDAO.validate(tmpCardsDAO.tmpCards)) {
                    try {
                        if (!tmpCardsDAO.save()) {
                            throw new Exception(tmpCardsDAO.errorMsg);
                        }
                    } catch (OutOfMemoryError out){
                        System.runFinalization();
                        System.gc();
                        Thread.sleep(60000);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showError(tmpCardsDAO);
                }
            }
        }
    }


    public void fillMainTableFromTmpTable(ObservableList<TmpCards> content, ActionEvent actionEvent) throws Exception {

        if (content.size() > 0) {
            clearTmpData(content);

            for (TmpCards values : content) {

                if (true) {
//                if (values.getProceed()) {
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
                    cardsDAO.cards.setIsVisible(1);

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
            showSuccessProfile(actionEvent);
        }
    }

    private void clearTmpData(ObservableList<TmpCards> content) throws Exception {
        for (TmpCards values : content) {
            if (values.getProceed()) {
                TmpCardsDAO tmpCardsDAO = new TmpCardsDAO(values.getId());
                tmpCardsDAO.delete(values.getId());
            }
        }
    }

    private static void showError(ModelsDAO model) {
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
        switch (value) {
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

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
