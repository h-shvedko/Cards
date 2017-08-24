package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Cards;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class CardsDAO extends ModelsDAO {

    public Cards cards;
    private Transaction transaction;

    public CardsDAO() {
        super();
        cards = new Cards();
        transaction = session.beginTransaction();
    }

    public CardsDAO(int id) {
        super();
        cards = (Cards) session.get(Cards.class, id);
        transaction = session.beginTransaction();
    }

    public Cards getCardById(int id) throws HibernateException {
        return (Cards) session.get(Cards.class, id);
    }

//    public Cards getCardById(int id) throws HibernateException{
//        Criteria criteria = session.createCriteria(Cards.class);
//        Cards cardsModel = (Cards) criteria.add(Restrictions.eq("id", id)).uniqueResult();
//        return cardsModel;
//    }

    public void save() throws Exception {
        if (this.validate(cards)) {
            session.persist(cards);
            transaction.commit();
        } else {
          throw new Exception(errorMsg);
        }
    }
}
