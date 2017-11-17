package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModalSuccessEditCardController extends A_Controller {
    @FXML
    private Button close;
    @FXML
    private Button createNew;


    public void handleCreateButton(ActionEvent actionEvent) {
        closeWindow(createNew);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        closeWindow(createNew);
        this.goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, "");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
