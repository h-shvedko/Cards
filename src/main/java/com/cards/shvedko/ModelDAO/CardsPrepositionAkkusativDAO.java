package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.CardsPrepositionAkkusativ;
import com.cards.shvedko.Model.CardsPrepositionDativ;
import org.hibernate.HibernateException;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class CardsPrepositionAkkusativDAO extends ModelsDAO {

    public CardsPrepositionAkkusativ cardsPrepositionAkkusativ;

    public CardsPrepositionAkkusativDAO() {
        super();
        cardsPrepositionAkkusativ = new CardsPrepositionAkkusativ();
    }

    public CardsPrepositionAkkusativDAO(int id) {
        super();
        cardsPrepositionAkkusativ = session.get(CardsPrepositionAkkusativ.class, id);
    }

    public CardsPrepositionAkkusativ getCardById(int id) throws HibernateException {
        return session.get(CardsPrepositionAkkusativ.class, id);
    }

    public boolean save() throws Exception {
        if (errorMsg != null) {
            session.persist(cardsPrepositionAkkusativ);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}
