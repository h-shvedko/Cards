package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.DecksValuesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveIsLearntController extends A_Controller {

    @FXML
    public Button agree;
    @FXML
    public Label cardName;
    @FXML
    public Label messageRemove;

    private boolean deleteAction = false;

    public static int deckValueId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Boolean isReady = getIsReady();

        if (isReady) {
            deleteAction = true;
            messageRemove.setText("Вы хотите учить еще раз эту карточку?");
        } else {
            messageRemove.setText("Вы хотите пометить эту карточку как выученую?");
        }
        cardName.setText(((Cards) globalUserData).getName());
    }

    private Boolean getIsReady() {
        Decks decks = globalDeckData;
        if (decks != null) {
            List<DecksValues> decksValues = decks.getDecksValues();
            if (decksValues.size() > 0) {

                for (Object deckValue : decksValues) {
                    if (((Cards) globalUserData).getId() == ((DecksValues) deckValue).getCards().getId() &&
                            ((DecksValues) deckValue).getIsReady() == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCloseButton(ActionEvent actionEvent) {
        answer = false;
        goToPage("listOfCardsInDeck.fxml", A_Controller.LIST_OF_CARDS_TITLE, globalUserData);
    }

    public void handleAgreeButton(ActionEvent actionEvent) {
        answer = true;
        learnAgain(actionEvent);
        goToPage("listOfCardsInDeck.fxml", A_Controller.LIST_OF_CARDS_TITLE, globalUserData);
    }

    private void learnAgain(ActionEvent actionEvent) {

        DecksValuesDAO decksValuesDAO = new DecksValuesDAO(deckValueId);

        if (decksValuesDAO.decksValues != null) {
            if (deleteAction) {
                decksValuesDAO.decksValues.setIsReady(0);
            } else {
                decksValuesDAO.decksValues.setIsReady(1);
            }
            if (decksValuesDAO.validate(decksValuesDAO.decksValues)) {
                try {
                    if (!decksValuesDAO.saveOrUpdate()) {
                        throw new Exception(decksValuesDAO.errorMsg);
                    }
                    A_Controller.updateGlobalDeckData();
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage());
                }
            } else {
                showErrors(decksValuesDAO);
            }
        }
    }
}