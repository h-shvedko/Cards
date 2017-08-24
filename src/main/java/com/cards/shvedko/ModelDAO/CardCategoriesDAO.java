package com.cards.shvedko.ModelDAO;

import javafx.collections.ObservableList;

public class CardCategoriesDAO extends ModelsDAO {
    public static ObservableList<String> setAllTypes(ObservableList<String> dataTopic) {
        dataTopic.add("Vehicle");
        dataTopic.add("Education");
        dataTopic.add("Food");
        dataTopic.add("Relationship");
        dataTopic.add("Medicine");
        dataTopic.add("Sport");
        dataTopic.add("Human");
        dataTopic.add("Weather");
        dataTopic.add("Finance");
        dataTopic.add("Business");
        dataTopic.add("Home");
        dataTopic.add("Other");

        return dataTopic;
    }
}
