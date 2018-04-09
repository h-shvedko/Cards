package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.TmpCards;
import org.hibernate.HibernateException;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class TmpCardsDAO extends ModelsDAO {

    public TmpCards tmpCards;

    public TmpCardsDAO() {
        super();
        tmpCards = new TmpCards();
    }

    public TmpCardsDAO(int id) {
        super();
        tmpCards = (TmpCards) session.get(TmpCards.class, id);
    }

    public TmpCards getCardById(int id) throws HibernateException {
        return (TmpCards) session.get(TmpCards.class, id);
    }

//    public Cards getCardById(int id) throws HibernateException{
//        Criteria criteria = session.createCriteria(Cards.class);
//        Cards tmpCardsModel = (Cards) criteria.add(Restrictions.eq("id", id)).uniqueResult();
//        return tmpCardsModel;
//    }

    public boolean save() throws Exception {
        if (errorMsg== null || errorMsg.equals("")) {
            session.persist(tmpCards);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }

    public boolean saveOrUpdate() {
        if (errorMsg == null || errorMsg.equals("")) {
            session.save(tmpCards);
            transaction.commit();
            session.close();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void delete(int id) throws Exception{
        tmpCards = session.get(TmpCards.class, id);
        session.delete(tmpCards);
        transaction.commit();
        session.close();

    }
}
