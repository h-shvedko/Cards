package com.cards.shvedko.ModelDAO;

import javafx.collections.ObservableList;

public class CardTypesDAO extends ModelsDAO {
    public static ObservableList<String> setAllTypes(ObservableList<String> data) {
        data.add("noun");
        data.add("verb");
        data.add("other");

        return data;
    }
}
