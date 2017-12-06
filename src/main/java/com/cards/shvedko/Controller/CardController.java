package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;

public class CardController extends A_CardController {

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleDisplayList(ActionEvent actionEvent) {
        goToPage("listOfCardsInDeck.fxml", "List of cards in deck", "");
    }
}
