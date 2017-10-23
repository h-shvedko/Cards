package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController extends A_Controller{

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
    public Label userNameError;
    @FXML
    public Label passwordError;
    @FXML
    public Label firstNameError;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {

    }

    public void handleCancelButtonAction(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
