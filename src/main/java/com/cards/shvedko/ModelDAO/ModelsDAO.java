package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Services.DBService;
import org.hibernate.Session;

import javax.validation.*;
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
    protected String errorMsg;

    public ModelsDAO() {
        dbServise = new DBService();
        session = dbServise.sessionFactory.openSession();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void save() {
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

            for (ConstraintViolation violation : constraintViolations) {
                errorMsg += violation.getMessage() + "\n";
            }
        } catch (ConstraintViolationException ex) {
            return false;
        }

        return false;
    }

    public I_DAO select(String criteria) {
        return null;
    }

}
