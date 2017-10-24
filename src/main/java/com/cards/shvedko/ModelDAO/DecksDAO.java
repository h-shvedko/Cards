package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import org.hibernate.HibernateException;

/**
 * Created by hennadii.shvedko on 14/07/2017.
 */
public class DecksDAO extends ModelsDAO {

    public Decks decks;

    public DecksDAO() {
        super();
        decks = new Decks();
    }

    public DecksDAO(int id) {
        super();
        decks = (Decks) session.get(Decks.class, id);
    }

    public Decks getDeckById(int id) throws HibernateException {
        return (Decks) session.get(Decks.class, id);
    }

    public boolean save() throws Exception {
        if (errorMsg != null) {
            session.persist(decks);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}
