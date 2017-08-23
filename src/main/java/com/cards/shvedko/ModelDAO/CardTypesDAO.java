package com.cards.shvedko.ModelDAO;

import javafx.collections.ObservableList;

public class CardTypesDAO extends ModelsDAO {
    public static ObservableList<Object> setAllTypes(ObservableList<Object> data) {
        data.add("noun");
        data.add("verb");
        data.add("other");

        return data;
    }
}
