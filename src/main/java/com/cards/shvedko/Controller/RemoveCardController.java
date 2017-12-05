package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveCardController extends A_Controller {

    @FXML
    public Button agree;
    @FXML
    public Label cardName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        cardName.setText(((Cards)globalUserData).getName());
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCloseButton(ActionEvent actionEvent) {
        answer = false;
        goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, globalUserData);
    }

    public void handleAgreeButton(ActionEvent actionEvent) {
        answer = true;
        deleteCard(actionEvent);
        goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, globalUserData);
    }

    private void deleteCard(ActionEvent actionEvent) {
        Cards cards = (Cards) globalUserData;

        CardsDAO cardsDAO = new CardsDAO(cards.getId());

        if(cardsDAO.cards != null){
            cardsDAO.cards.setIsVisible(2);
            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    if (!cardsDAO.saveOrUpdate()) {
                        throw new Exception(cardsDAO.errorMsg);
                    }
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage());
                }
            } else {
                showErrors(cardsDAO);
            }
        }
    }
}