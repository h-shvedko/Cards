package com.cards.shvedko.ModelDAO;

import com.cards.shvedko.Model.CardCategories;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CardCategoriesDAO extends ModelsDAO {

    public static ObservableList<String> setAllTypes(ObservableList<String> dataTopic) {

        CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
        List cardsCategories = new ArrayList();
        try {
            cardsCategories = cardCategoriesDAO.selectAll();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (cardsCategories.size() > 0) {
            for (Object cardCategory : cardsCategories) {
                dataTopic.add(((CardCategories) cardCategory).getName());
            }
        }

        return dataTopic;
    }
}
