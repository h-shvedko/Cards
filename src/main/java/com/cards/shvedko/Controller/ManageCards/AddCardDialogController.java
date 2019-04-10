package com.cards.shvedko.Controller.ManageCards;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

public class AddCardDialogController extends A_Controller {

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {
        String value = speechPart.getValue();

        switch (value){
            case ModelsDAO.NOUN:
                goToPage("ManageCards/addCardNoun.fxml", A_Controller.ADD_NOUN_PAGE, "");
                break;
            case ModelsDAO.VERB:
                goToPage("ManageCards/addCardVerb.fxml", A_Controller.ADD_VERB_PAGE, "");
                break;
            case ModelsDAO.ADJECTIVE:
                goToPage("ManageCards/addCardAdjective.fxml", A_Controller.ADD_ADJECTIVE_PAGE, "");
                break;
            case ModelsDAO.ADVERB:
                goToPage("ManageCards/addCardAdverb.fxml", A_Controller.ADD_ADVERB_PAGE, "");
                break;
            case ModelsDAO.NUMERAL:
                goToPage("ManageCards/addCardNumeral.fxml", A_Controller.ADD_NUMERAL_PAGE, "");
                break;
            case ModelsDAO.PARTICIPLE:
                goToPage("ManageCards/addCardParticiple.fxml", A_Controller.ADD_PARTICIPLE_PAGE, "");
                break;
            case ModelsDAO.PRONOUN:
                goToPage("ManageCards/addCardPronoun.fxml", A_Controller.ADD_PRONOUN_PAGE, "");
                break;
            default:
                goToPage("ManageCards/addCardOther.fxml", A_Controller.ADD_OTHER_PAGE, "");
                break;
        }
        return null;
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
