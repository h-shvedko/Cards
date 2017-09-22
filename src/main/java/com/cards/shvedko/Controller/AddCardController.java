package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

public class AddCardController extends A_Controller {

    @Override
    public void handleAddButton(ActionEvent actionEvent) {
        String value = speechPart.getValue();

        switch (value){
            case ModelsDAO.NOUN:
                goToPage("addCardNoun.fxml");
                break;
            case ModelsDAO.VERB:
                goToPage("addCardVerb.fxml");
                break;
            default:
                goToPage("addCardOther.fxml");
                break;
        }
    }
}
