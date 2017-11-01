package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Services.DBService;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class ModelsDAO implements I_DAO {

    public static final int ANCHOR_OFF = 0;
    public static final int ANCHOR_ON = 1;
    public static final int FAVORITE_OFF = 0;
    public static final int FAVORITE_ON = 1;
    protected static Validator validator;
    public static final String NOUN = "Существительное";
    public static final String VERB = "Глагол";
    public static final String ADJECTIVE = "Прилогательное";
    public static final String PRONOUN = "Местоимение";
    public static final String ADVERB = "Наречие";
    public static final String NUMERAL = "Числительное";
    public static final String PARTICIPLE = "Причастие";
    public static final String OTHER_PART_OF_SPEECH = "Другие";
    public static final String ALL_PART_OF_SPEECH = "Все";
    public static final String MUSKULINUM = "мужской";
    public static final String FEMININUM = "женский";
    public static final String NEUTRUM = "средний";
    public static final int MUSKULINUM_INTO_DB = 1;
    public static final int FEMININUM_INTO_DB= 2;
    public static final int NEUTRUM_INTO_DB = 3;

    public static final String HABEN_PERFECT = "haben";
    public static final int HABEN_PERFECT_TO_DB = 1;
    public static final String SEIN_PERFECT = "sein";
    public static final int SEIN_PERFECT_TO_DB = 0;

    public static final String REGELMESSIG_VERB = "правильный";
    public static final int REGELMESSIG_VERB_TO_DB = 1;
    public static final String UNREGELMESSIG_VERB = "неправильный";
    public static final int UNREGELMESSIG_VERB_TO_DB = 0;

    public static final String TREMBARE_PREFIX_VERB = "отделяемая";
    public static final int TREMBARE_PREFIX_VERB_TO_DB = 1;
    public static final String UMTREMBARE_PREFIX_VERB_VERB = "не отделяемая";
    public static final int UMTREMBARE_PREFIX_VERB_VERB_TO_DB = 0;

    public static final int IS_REFLEXIVE = 1;


    protected final DBService dbServise;
    protected final Session session;
    public String errorMsg;
    public Set<ConstraintViolation<A_Models>> errorSet;
    private String table;
    protected Transaction transaction;

    public ModelsDAO() {
        dbServise = new DBService();

        session = DBService.sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public boolean save() throws Exception {
        return false;
    }

    public void update(int id, String[] record) throws Exception {
    }

    public void delete(int id) throws Exception{
    }

    public boolean validate(A_Models model) throws ConstraintViolationException {

        errorMsg = "";
        try {
            Set<ConstraintViolation<A_Models>> constraintViolations =
                    validator.validate(model);

            if (constraintViolations.size() == 0) {
                return true;
            }

            errorSet = constraintViolations;

            for (ConstraintViolation violation : constraintViolations) {
                errorMsg += violation.getMessage() + "\n";
            }
        } catch (ConstraintViolationException ex) {
            return false;
        }

        return false;
    }

    @Override
    public A_Models select(String criteria) throws Exception {
        A_Models object = null;
        String table = this.getClassName();

        Query result = session.createQuery("from " + table + " " + criteria);

        if (result.list().size() > 0) {
            object = (A_Models) result.list().get(0);
        }
        session.close();
        return object;
    }

    @Override
    public List selectAll() throws Exception {
        String table = this.getClassName();
        Query result = session.createQuery("from " + table);
        List ret = result.list();
        session.close();
        return ret;
    }

    @Override
    public List selectAllBy(String criteria) throws Exception {
        String table = this.getClassName();
        Query result = session.createQuery("from " + table + " " + criteria);
        List ret = result.list();
        session.close();
        return ret;
    }


    public String getClassName() {
        String className =  this.getClass().getSimpleName();
        String table = "";

        switch (className){
            case "CardCategoriesDAO":
                table = "com.cards.shvedko.Model.CardCategories";
                break;
            case "CardFilesDAO":
                table = "com.cards.shvedko.Model.CardFiles";
                break;
            case "CardFilesDeDAO":
                table = "com.cards.shvedko.Model.CardFilesDe";
                break;
            case "CardLanguageDAO":
                table = "com.cards.shvedko.Model.CardLanguages";
                break;
            case "CardLanguageDeDAO":
                table = "com.cards.shvedko.Model.CardLanguageDe";
                break;
            case "CardsDAO":
                table = "com.cards.shvedko.Model.Cards";
                break;
            case "CardTypesDAO":
                table = "com.cards.shvedko.Model.CardTypes";
                break;
            case "SystemConfigsDAO":
                table = "com.cards.shvedko.Model.SystemConfigs";
                break;
            case "UsersDAO":
                table = "com.cards.shvedko.Model.Users";
                break;
            case "CardsPrepositionAkkusativDAO":
                table = "com.cards.shvedko.Model.CardsPrepositionAkkusativ";
                break;
            case "CardsPrepositionDativDAO":
                table = "com.cards.shvedko.Model.CardsPrepositionDativ";
                break;
            case "DecksDAO":
                table = "com.cards.shvedko.Model.Decks";
                break;
            case "DecksValuesDAO":
                table = "com.cards.shvedko.Model.DecksValues";
                break;
        }

        return table;
    }

    public void closeSession(){
        session.close();
    }

//    public String getTableByClassName(){
//
//    }

}
