package com.cards.shvedko.Controller.Modals;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModalSuccessController extends A_Controller {
    @FXML
    private Button close;
    @FXML
    private Button createNew;


    public void handleCreateButton(ActionEvent actionEvent) {
        MainApp.stage.setOpacity(1);
        closeWindow(createNew);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        closeWindow(createNew);
        MainApp.stage.setOpacity(1);
        this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, "");
    }

    @Override
    protected void handleCancelButtonAction() {
    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCancelButtonEmptyDeck(ActionEvent actionEvent) {
        MainApp.stage.setOpacity(1);
        closeWindow(close);
    }
}
