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

    protected static Validator validator;
    protected static final String NOUN = "noun";
    protected static final String VERB = "verb";
    protected static final String ADJECTIVE = "adjective";
    protected static final String PRONOUN = "pronoun";
    protected static final String ADVERB = "adverb";
    protected static final String NUMERAL = "numeral";
    protected static final String PARTICIPLE = "participle";
    protected static final String OTHER_PART_OF_SPEECH = "other";

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
        }

        return table;
    }

//    public String getTableByClassName(){
//
//    }

}
