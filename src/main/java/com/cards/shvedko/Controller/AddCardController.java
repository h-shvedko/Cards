package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

public class AddCardController extends A_Controller {

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {
        String value = speechPart.getValue();

        switch (value){
            case ModelsDAO.NOUN:
                goToPage("addCardNoun.fxml", A_Controller.ADD_NOUN_PAGE, "");
                break;
            case ModelsDAO.VERB:
                goToPage("addCardVerb.fxml", A_Controller.ADD_VERB_PAGE, "");
                break;
            case ModelsDAO.ADJECTIVE:
                goToPage("addCardAdjective.fxml", A_Controller.ADD_ADJECTIVE_PAGE, "");
                break;
            case ModelsDAO.ADVERB:
                goToPage("addCardAdverb.fxml", A_Controller.ADD_ADVERB_PAGE, "");
                break;
            case ModelsDAO.NUMERAL:
                goToPage("addCardNumeral.fxml", A_Controller.ADD_NUMERAL_PAGE, "");
                break;
            case ModelsDAO.PARTICIPLE:
                goToPage("addCardParticiple.fxml", A_Controller.ADD_PARTICIPLE_PAGE, "");
                break;
            case ModelsDAO.PRONOUN:
                goToPage("addCardPronoun.fxml", A_Controller.ADD_PRONOUN_PAGE, "");
                break;
            default:
                goToPage("addCardOther.fxml", A_Controller.ADD_OTHER_PAGE, "");
                break;
        }
        return null;
    }
}
