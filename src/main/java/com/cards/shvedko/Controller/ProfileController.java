package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends A_Controller{

    @FXML
    public Button close;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public TextField first_name;
    @FXML
    public TextField last_name;
    @FXML
    public Button save;
    @FXML
    public Label lastNameError;
    @FXML
    public Label passwordError;
    @FXML
    public Label firstNameError;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {

    }

    public void handleCancelButtonAction(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, "");
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
