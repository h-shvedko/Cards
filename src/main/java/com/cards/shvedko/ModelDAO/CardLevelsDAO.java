package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.CardLevels;
import javafx.collections.ObservableList;

import java.util.List;

public class CardLevelsDAO extends ModelsDAO {

    public static ObservableList<String> setAllLevels(ObservableList<String> dataTopic) throws Exception {

        CardLevelsDAO cardLevelsDAO = new CardLevelsDAO();
        List cardsLevels;
        try {
            cardsLevels = cardLevelsDAO.selectAll();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

        if (cardsLevels.size() > 0) {
            for (Object cardLevel : cardsLevels) {
                dataTopic.add(((CardLevels) cardLevel).getName());
            }
        }

        return dataTopic;
    }
}
