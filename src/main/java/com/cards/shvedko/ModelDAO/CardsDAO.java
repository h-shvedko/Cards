package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Cards;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class CardsDAO extends ModelsDAO {

    public Cards cards;

    public CardsDAO() {
        super();
        cards = new Cards();
    }

    public CardsDAO(int id) {
        super();
        cards = (Cards) session.get(Cards.class, id);
    }

    public Cards getCardById(int id) throws HibernateException {
        return (Cards) session.get(Cards.class, id);
    }

//    public Cards getCardById(int id) throws HibernateException{
//        Criteria criteria = session.createCriteria(Cards.class);
//        Cards cardsModel = (Cards) criteria.add(Restrictions.eq("id", id)).uniqueResult();
//        return cardsModel;
//    }

    public boolean save() throws Exception {
        if (errorMsg== null || errorMsg.equals("")) {
            session.persist(cards);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }

    public boolean saveOrUpdate() {
        if (errorMsg == null || errorMsg.equals("")) {
            session.save(cards);
            transaction.commit();
            session.close();
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

    public void insert(StringBuilder query, Session session){
        if(session.isConnected()){
            Query queryToInsert = session.createNativeQuery(String.valueOf(query));
            int result = queryToInsert.executeUpdate();
        }
    }
}
