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

    public ModelsDAO() {
        dbServise = new DBService();
        session = DBService.sessionFactory.openSession();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public boolean save() throws Exception {
        return false;
    }

    public void update(int id, String[] record) {
    }

    public void delete(int id) {
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
    public Object select(String criteria) {
        String table = this.getClassName(this.getClass());
        Query result = session.createQuery("from " + table + " " + criteria);
        return result.list().get(0);
    }

    @Override
    public List selectAll() {
        String table = this.getClassName(this.getClass());
        Query result = session.createQuery("from " + table);
        return result.list();
    }

    @Override
    public List selectAllBy(String criteria) {
        String table = this.getClassName(this.getClass());
        Query result = session.createQuery("from " + table + " " + criteria);
        return result.list();
    }


    public String getClassName(Class classObject){
        Class<?> enclosingClass = classObject.getClass().getEnclosingClass();
        if (enclosingClass != null) {
            return enclosingClass.getName();
        } else {
            return getClass().getName();
        }
    }

//    public String getTableByClassName(){
//
//    }

}
