package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.CardTypes;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CardTypesDAO extends ModelsDAO {

    public static ObservableList<String> setAllTypes(ObservableList<String> data) {
        CardTypesDAO cardTypesDAO = new CardTypesDAO();
        List cardTypes = new ArrayList();
        try {
            cardTypes = cardTypesDAO.selectAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (cardTypes.size() > 0) {
            for (Object cardType : cardTypes) {
                data.add(((CardTypes) cardType).getName());
            }
        }

        return data;
    }
}
