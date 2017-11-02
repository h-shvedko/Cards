package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.CardTypes;
import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

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

    public static ObservableList<String> setAllDecks(ObservableList<String> data) {
        DecksDAO decksDAO = new DecksDAO();
        List decks = new ArrayList();
        try {
            decks = decksDAO.selectAllBy("where is_visible=1 and user_id=" + A_Controller.globalUserModel.getId());
        } catch (Exception e){
            e.printStackTrace();
        }

        if (decks.size() > 0) {
            for (Object deck : decks) {
                data.add(((Decks) deck).getName());
            }
        }

        return data;
    }

    public boolean save() throws Exception {
        if (errorMsg.equals("")) {
            session.persist(decks);
            transaction.commit();
            session.close();
        } else{
            return false;
        }
        return true;
    }
}
