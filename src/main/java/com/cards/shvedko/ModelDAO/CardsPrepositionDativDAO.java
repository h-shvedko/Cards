package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.CardsPrepositionDativ;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

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
        if (errorMsg.equals("")) {
            session.persist(cardsPrepositionDativ);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }

    public static ObservableList<String> setAllPrepositions(ObservableList<String> dataDat) throws Exception {
        CardsPrepositionDativDAO cardsPrepositionDativDAO = new CardsPrepositionDativDAO();
        List prepositionDat = new ArrayList();
        try {
            prepositionDat = cardsPrepositionDativDAO.selectAll();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

        if (prepositionDat.size() > 0) {
            for (Object prepDat : prepositionDat) {
                dataDat.add(((CardsPrepositionDativ) prepDat).getName());
            }
        }

        return dataDat;
    }
}
