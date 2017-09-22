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
            case ModelsDAO.ADJECTIVE:
                goToPage("addCardAdjective.fxml");
                break;
            case ModelsDAO.ADVERB:
                goToPage("addCardAdverb.fxml");
                break;
            case ModelsDAO.NUMERAL:
                goToPage("addCardNumeral.fxml");
                break;
            case ModelsDAO.PARTICIPLE:
                goToPage("addCardParticiple.fxml");
                break;
            case ModelsDAO.PRONOUN:
                goToPage("addCardPronoun.fxml");
                break;
            default:
                goToPage("addCardOther.fxml");
                break;
        }
    }
}
