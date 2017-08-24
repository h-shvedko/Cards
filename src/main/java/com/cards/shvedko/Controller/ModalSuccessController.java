package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModalSuccessController extends A_Controller {
    @FXML
    private Button close;
    @FXML
    private Button createNew;


    public void handleCreateButton(ActionEvent actionEvent) {
        closeWindow(createNew);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        closeWindow(createNew);
        goToPage("mainPage.fxml");
    }
}
