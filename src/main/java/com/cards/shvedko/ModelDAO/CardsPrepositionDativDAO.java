package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.CardsPrepositionDativ;
import org.hibernate.HibernateException;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class CardsPrepositionDativDAO extends ModelsDAO {

    public CardsPrepositionDativ cardsPrepositionDativ;

    public CardsPrepositionDativDAO() {
        super();
        cardsPrepositionDativ = new CardsPrepositionDativ();
    }

    public CardsPrepositionDativDAO(int id) {
        super();
        cardsPrepositionDativ = session.get(CardsPrepositionDativ.class, id);
    }

    public CardsPrepositionDativ getCardById(int id) throws HibernateException {
        return session.get(CardsPrepositionDativ.class, id);
    }

    public boolean save() throws Exception {
        if (errorMsg != null) {
            session.persist(cardsPrepositionDativ);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}
