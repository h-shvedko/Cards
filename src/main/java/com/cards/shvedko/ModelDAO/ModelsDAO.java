package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Services.DBService;
import org.hibernate.Query;
import org.hibernate.Session;

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

    public ModelsDAO() {
        dbServise = new DBService();
        session = DBService.sessionFactory.openSession();

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

        return object;
    }

    @Override
    public List selectAll() throws Exception {
        String table = this.getClassName();
        Query result = session.createQuery("from " + table);
        return result.list();
    }

    @Override
    public List selectAllBy(String criteria) throws Exception {
        String table = this.getClassName();
        Query result = session.createQuery("from " + table + " " + criteria);
        return result.list();
    }


    public String getClassName() {
        String className =  this.getClass().getSimpleName();
        String table = "";

        switch (className){
            case "CardCategoriesDAO":
                table = "CardCategories";
                break;
            case "CardFilesDAO":
                table = "CardFiles";
                break;
            case "CardFilesDeDAO":
                table = "CardFilesDe";
                break;
            case "CardLanguageDAO":
                table = "CardLanguages";
                break;
            case "CardLanguageDeDAO":
                table = "CardLanguageDe";
                break;
            case "CardsDAO":
                table = "Cards";
                break;
            case "CardTypesDAO":
                table = "CardTypes";
                break;
            case "SystemConfigsDAO":
                table = "SystemConfigs";
                break;
            case "UsersDAO":
                table = "Users";
                break;
        }

        return table;
    }

//    public String getTableByClassName(){
//
//    }

}
