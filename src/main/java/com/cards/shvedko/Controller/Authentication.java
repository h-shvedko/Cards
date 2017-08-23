package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Authentication extends A_Controller{
    @FXML
    private Button closeApp;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink createAccount;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        String loginValue = login.getText();
        String passwordValue = password.getText();
    }

    public void handleCancelButtonAction(ActionEvent actionEvent) {
        Stage stage = (Stage) closeApp.getScene().getWindow();
        stage.close();
    }

    public void createAccount(ActionEvent actionEvent) throws Exception {
        goToPage("addCardVerb.fxml");
    }
}
