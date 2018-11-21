package com.cards.shvedko.Helpers;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import com.cards.shvedko.Services.DBService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.hibernate.Session;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FillDatabase extends A_Controller {

    public static void fillCardsFromCSV(List<String> content) throws UnsupportedEncodingException, ArrayIndexOutOfBoundsException, InterruptedException {

        int i = 0;
        if (content.size() > 0) {
            final Session session = DBService.sessionFactory.getCurrentSession();

            StringBuilder insertString = null;
            TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();

            for (String line : content) {
                i++;
                List values;
//                if(i % 200 == 0){
//                    System.runFinalization();
//                    System.gc();
//                    Thread.sleep(6000);
//                    tmpCardsDAO.commit();
//                }
                try {
                    insertString = new StringBuilder("INSERT INTO TMP_CARDS (example, foreign_example, foreign_name, " +
                            "foreign_nama_infinitive, foreign_name_perfect, foreign_name_preteritum, " +
                            "is_perfect_with_haben, is_reflexiv_verb, is_regular_verb," +
                            "is_trembare_prefix_verb, kind_of_noun, name, plural_endung, preposition_gen," +
                            "proceed, category_id, preposition_akk, preposition_dat, type_id) VALUES ");

                    insertString.append("(");
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
                    if (values.size() > 17) {
                        CardsPrepositionAkkusativDAO cardsPrepositionAkkusativDAO = new CardsPrepositionAkkusativDAO();
                        akkObject = cardsPrepositionAkkusativDAO.select("where name='" + (String)values.get(17) + "'");
                    }

                    A_Models dativObject = null;
                    if (values.size() > 18) {
                        CardsPrepositionDativDAO cardsPrepositionDativDAO = new CardsPrepositionDativDAO();
                        dativObject = cardsPrepositionDativDAO.select("where name='" + (String)values.get(18) + "'");
                    }

                    //example field
                    if (values.size() > 8) {
                        insertString.append("'");
                        insertString.append((String) values.get(8));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //foreign example field
                    if (values.size() > 9) {
                        insertString.append("'");
                        insertString.append((String) values.get(9));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //foreign name field
                    if (values.size() > 2) {
                        insertString.append("'");
                        insertString.append((String) values.get(2));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //foreign name infinitive field
                    if (values.size() > 5) {
                        insertString.append("'");
                        insertString.append((String) values.get(5));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //foreign name perfect field
                    if (values.size() > 7) {
                        insertString.append("'");
                        insertString.append((String) values.get(7));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //foreign name preteritum field
                    if (values.size() > 6) {
                        insertString.append("'");
                        insertString.append((String) values.get(6));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //is perfect with haben field
                    if (values.size() > 13) {
                        insertString.append("'");
                        insertString.append((String) values.get(13));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //is reflexiv verb field
                    if (values.size() > 14) {
                        insertString.append("'");
                        insertString.append((String) values.get(14));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //is regular verb field
                    if (values.size() > 16) {
                        insertString.append("'");
                        insertString.append((String) values.get(16));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //is trembare prefix verb field
                    if (values.size() > 15) {
                        insertString.append("'");
                        insertString.append((String) values.get(15));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //kind of noun field
                    if (values.size() > 12) {
                        insertString.append("'");
                        insertString.append((String) values.get(12));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //name field
                    if (values.size() > 1) {
                        insertString.append("'");
                        insertString.append((String) values.get(1));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //plural endung field
                    if (values.size() > 3) {
                        insertString.append("'");
                        insertString.append((String) values.get(3));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //genetiv preposition field
                    if (values.size() > 19) {
                        insertString.append("'");
                        insertString.append((String) values.get(19));
                        insertString.append("',");
                    } else {
                        insertString.append("'',");
                    }

                    //proceed field
                    insertString.append(1);
                    insertString.append(",");

                    //category field
                    if (categoryObject != null) {
                        insertString.append((Integer) categoryObject.getId());
                        insertString.append(",");
                    } else {
                        insertString.append("null,");
                    }

                    //akkusative preposition field
                    if (akkObject != null) {
                        insertString.append((Integer) akkObject.getId());
                        insertString.append(",");
                    } else {
                        insertString.append("null,");
                    }

                    //dative preposition field
                    if (dativObject != null) {
                        insertString.append((Integer) dativObject.getId());
                        insertString.append(",");
                    } else {
                        insertString.append("null,");
                    }

                    //dative preposition field
                    if (typeObject != null) {
                        insertString.append((Integer) typeObject.getId());
                        insertString.append(")");
                    } else {
                        insertString.append("null)");
                    }

                    insertString.append(";");
                    tmpCardsDAO.insert(insertString, session);

                } catch (OutOfMemoryError out){
                    System.runFinalization();
                    System.gc();
                    Thread.sleep(60000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                tmpCardsDAO.commit();
            } catch (OutOfMemoryError out){
                System.runFinalization();
                System.gc();
                Thread.sleep(60000);
            } catch (Exception ex) {
                ex.printStackTrace();
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
