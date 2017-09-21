package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Cards;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

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
        if (errorMsg != null) {
            session.persist(cards);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}
