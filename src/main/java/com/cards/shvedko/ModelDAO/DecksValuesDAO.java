package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import org.hibernate.HibernateException;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class DecksValuesDAO extends ModelsDAO {

    public DecksValues decksValues;

    public DecksValuesDAO() {
        super();
        decksValues = new DecksValues();
    }

    public DecksValuesDAO(int id) {
        super();
        decksValues = (DecksValues) session.get(DecksValues.class, id);
    }

    public DecksValues getDeckValueById(int id) throws HibernateException {
        return (DecksValues) session.get(DecksValues.class, id);
    }

    public boolean save() throws Exception {
        if (errorMsg != null) {
            session.persist(decksValues);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}
