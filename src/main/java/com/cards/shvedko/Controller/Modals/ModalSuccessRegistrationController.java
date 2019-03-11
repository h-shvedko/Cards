package com.cards.shvedko.Controller.Modals;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalSuccessRegistrationController extends A_Controller {
    @FXML
    public Label congratulation;
    @FXML
    private Button close;

    public void initialize(URL location, ResourceBundle resources) {
        Users user = (Users) globalUserData;
        congratulation.setText("Congratulation " + user.getName() + "!");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCloseButton(ActionEvent actionEvent) {
        closeWindow(close);
        this.goToPage("Settings/authentication.fxml", A_Controller.LOGIN_PAGE_TITLE, "");
    }
}
