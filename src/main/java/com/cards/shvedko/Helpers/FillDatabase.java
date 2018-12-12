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
                            "proceed, category_id, preposition_akk, preposition_dat, type_id, level_id) VALUES ");

                    insertString.append("(");
                    values = Arrays.asList(line.split("\t"));

                    CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
                    A_Models categoryObject = null;
                    if (values.size() > 10) {
                        categoryObject = cardCategoriesDAO.selectWithoutClosingSession("where name='" + (String) values.get(10) + "'", session);
                        if (categoryObject == null) {
                            CardCategoriesDAO cardCategoriesEmptyDAO = new CardCategoriesDAO();
                            categoryObject = cardCategoriesEmptyDAO.selectWithoutClosingSession("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'", session);
                        }
                    }

                    CardLevelsDAO cardLevelsDAO = new CardLevelsDAO();
                    A_Models levelObject = null;
                    if (values.size() > 1) {
                        levelObject = cardLevelsDAO.selectWithoutClosingSession("where name='" + (String) values.get(0) + "'", session);
                        if (levelObject == null) {
                            CardLevelsDAO cardLevelsDAOEmpty = new CardLevelsDAO();
                            levelObject = cardLevelsDAOEmpty.selectWithoutClosingSession("where name='" + ModelsDAO.ALL_LEVELS + "'", session);
                        }
                    }

                    CardTypesDAO cardTypesDAO = new CardTypesDAO();
                    A_Models typeObject = null;
                    if (values.size() > 11) {
                        typeObject = cardTypesDAO.selectWithoutClosingSession("where name='" + (String) values.get(11) + "'", session);
                        if (typeObject == null) {
                            CardTypesDAO cardTypesEmptyDAO = new CardTypesDAO();
                            typeObject = cardTypesEmptyDAO.selectWithoutClosingSession("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'", session);
                        }
                    }

                    A_Models akkObject = null;
                    if (values.size() > 17) {
                        CardsPrepositionAkkusativDAO cardsPrepositionAkkusativDAO = new CardsPrepositionAkkusativDAO();
                        akkObject = cardsPrepositionAkkusativDAO.selectWithoutClosingSession("where name='" + (String) values.get(17) + "'", session);
                    }

                    A_Models dativObject = null;
                    if (values.size() > 18) {
                        CardsPrepositionDativDAO cardsPrepositionDativDAO = new CardsPrepositionDativDAO();
                        dativObject = cardsPrepositionDativDAO.selectWithoutClosingSession("where name='" + (String) values.get(18) + "'", session);
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
                        insertString.append("1,");
                    }

                    //dative preposition field
                    if (dativObject != null) {
                        insertString.append((Integer) dativObject.getId());
                        insertString.append(",");
                    } else {
                        insertString.append("1,");
                    }

                    //dative preposition field
                    if (typeObject != null) {
                        insertString.append((Integer) typeObject.getId());
                        insertString.append(",");
                    } else {
                        insertString.append("null,");
                    }

                    //level field
                    if (levelObject != null) {
                        insertString.append((Integer) levelObject.getId());
                        insertString.append(")");
                    } else {
                        insertString.append("null)");
                    }

                    insertString.append(";");
                    tmpCardsDAO.insert(insertString, session);

                } catch (OutOfMemoryError out) {
                    System.runFinalization();
                    System.gc();
                    Thread.sleep(60000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                tmpCardsDAO.commitWithGivenSession(session);
            } catch (OutOfMemoryError out) {
                System.runFinalization();
                System.gc();
                Thread.sleep(60000);
            } catch (Exception ex) {
                ex.printStackTrace();
                if (session.getTransaction() != null) session.getTransaction().rollback();
            }
        }
    }


    public void fillMainTableFromTmpTable(ObservableList<TmpCards> content, ActionEvent actionEvent) throws Exception {

        if (content.size() > 0) {

            final Session session = DBService.sessionFactory.getCurrentSession();
            StringBuilder insertString = null;
            CardsDAO cardsDAO = new CardsDAO();

            for (TmpCards values : content) {

                if (true) {
                    try {
                        insertString = new StringBuilder("INSERT INTO CARDS (example, foreign_example, foreign_name, foreign_nama_infinitive, foreign_name_perfect, foreign_name_preteritum, is_perfect_with_haben, is_reflexiv_verb, is_regular_verb, is_trembare_prefix_verb, kind_of_noun, name, plural_endung, preposition_gen, category_id, preposition_akk, preposition_dat, type_id, is_visible, level_id, user_id) VALUES ");

                        insertString.append("(");

                        insertString.append("'");
                        insertString.append(values.getExample());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getForeignExample());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getForeignName());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getForeignNameInfinitive());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getForeignNamePerfect());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getForeignNamePreteritum());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getIsPerfectWithHaben());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getIsReflexiveVerb());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getIsRegularVerb());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getIsTrembarePrefixVerb());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getKindOfNoun());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getName());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getPluralEndung());
                        insertString.append("',");

                        insertString.append("'");
                        insertString.append(values.getPrepositionGen());
                        insertString.append("',");

                        int categoryId = 0;
                        if(values.getCategory() != null){
                            categoryId = values.getCategory().getId();
                        }
                        insertString.append(categoryId);
                        insertString.append(",");

                        int prepositionAkkId = 0;
                        if(values.getPrepositionAkk() != null){
                            prepositionAkkId = values.getPrepositionAkk().getId();
                        }
                        insertString.append(prepositionAkkId);
                        insertString.append(",");

                        int prepositionDatId = 0;
                        if(values.getPrepositionDativ() != null){
                            prepositionDatId = values.getPrepositionDativ().getId();
                        }
                        insertString.append(prepositionDatId);
                        insertString.append(",");

                        int typeId = 0;
                        if(values.getType() != null){
                            typeId = values.getType().getId();
                        }
                        insertString.append(typeId);
                        insertString.append(",");

                        insertString.append(1);
                        insertString.append(",");

                        int levelId = 0;
                        if(values.getLevel() != null){
                            levelId = values.getLevel().getId();
                        }
                        insertString.append(levelId);
                        insertString.append(",");

                        insertString.append(globalUserModel.getId());

                        insertString.append(")");

                        insertString.append(";");
                        cardsDAO.insert(insertString, session);
                    } catch (OutOfMemoryError out) {
                        System.runFinalization();
                        System.gc();
                        Thread.sleep(60000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            try {
                cardsDAO.commitWithGivenSession(session);
            } catch (OutOfMemoryError out) {
                System.runFinalization();
                System.gc();
                Thread.sleep(60000);
            } catch (Exception ex) {
                ex.printStackTrace();
                if (session.getTransaction() != null) session.getTransaction().rollback();
            }

            clearTmpData(content);

            showSuccessProfile(actionEvent);
        }
    }

    private void clearTmpData(ObservableList<TmpCards> content) {
        final Session session = DBService.sessionFactory.getCurrentSession();
        StringBuilder deleteString = null;
        TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();

        try {
            deleteString = new StringBuilder("DELETE FROM TMP_CARDS;");
            tmpCardsDAO.deleteWithNativeQuery(deleteString, session);
            tmpCardsDAO.commitWithGivenSession(session);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session.getTransaction() != null) session.getTransaction().rollback();
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

    public void fillMainTableFromTmpTableWithAllData(ActionEvent actionEvent) throws UnsupportedEncodingException, ArrayIndexOutOfBoundsException, InterruptedException {
        final Session session = DBService.sessionFactory.getCurrentSession();
        StringBuilder insertString = null;
        CardsDAO cardsDAO = new CardsDAO();

        try {
            insertString = new StringBuilder("INSERT INTO CARDS SELECT example, foreign_example, foreign_name, \" +\n" +
                    "                            \"foreign_nama_infinitive, foreign_name_perfect, foreign_name_preteritum, \" +\n" +
                    "                            \"is_perfect_with_haben, is_reflexiv_verb, is_regular_verb,\" +\n" +
                    "                            \"is_trembare_prefix_verb, kind_of_noun, name, plural_endung, preposition_gen,\" +\n" +
                    "                            \"proceed, category_id, preposition_akk, preposition_dat, type_id, level_id FROM TMP_CARDS");

            cardsDAO.insert(insertString, session);

        } catch (OutOfMemoryError out) {
            System.runFinalization();
            System.gc();
            Thread.sleep(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cardsDAO.commitWithGivenSession(session);
        } catch (OutOfMemoryError out) {
            System.runFinalization();
            System.gc();
            Thread.sleep(60000);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session.getTransaction() != null) session.getTransaction().rollback();
        }
    }
}
