package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public DecksValuesDAO(int id, boolean ifLoad) {
        super();

        if(ifLoad){
            decksValues = (DecksValues) session.load(DecksValues.class, id);
        } else {
            decksValues = (DecksValues) session.get(DecksValues.class, id);
        }
    }

    public boolean save() throws Exception {
        if (errorMsg== null || errorMsg.equals("")) {
            session.persist(decksValues);
            transaction.commit();
            session.close();
        } else {
            return false;
        }
        return true;
    }

    public boolean saveOrUpdate() {
        if (errorMsg == null || errorMsg.equals("")) {
            session.save(decksValues);
            transaction.commit();
            session.close();
        } else {
            return false;
        }
        return true;
    }

    public boolean saveOrUpdateDeckValues() {
        if (errorMsg == null || errorMsg.equals("")) {
            session.save(decksValues);
            transaction.commit();
        } else {
            return false;
        }
        return true;
    }

    public boolean commit() {
        if (errorMsg == null || errorMsg.equals("")) {
            transaction.commit();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void delete(int id) throws Exception{
        decksValues = session.load(DecksValues.class, id);
        session.delete(decksValues);
        transaction.commit();
        session.close();

    }

    public void insert(StringBuilder query, Session session){
        if(session.isConnected()){
            Query queryToInsert = session.createNativeQuery(String.valueOf(query));
            int result = queryToInsert.executeUpdate();
        }
    }
}
