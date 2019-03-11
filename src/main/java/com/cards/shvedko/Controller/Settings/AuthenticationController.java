package com.cards.shvedko.Controller.Settings;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.ModelDAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthenticationController extends A_Controller {
    @FXML
    public Label errorAuth;
    @FXML
    private Button closeApp;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink createAccount;

    public void handleSubmitButtonAction() {
        login.setStyle("-fx-border-color: inherit");
        password.setStyle("-fx-border-color: inherit");
        String loginValue = login.getText();
        String passwordValue = password.getText();
        String validUser;
        try {
            validUser = UsersDAO.authenticator(loginValue, passwordValue);
        } catch (Exception e) {
            validUser = e.getMessage();
        }
        switch (validUser){
            case UsersDAO.AUTHENTICATION_FAILED:
                errorAuth.setText("Authentication error!");
                break;
            case UsersDAO.USERNAME_EMPTY:
                errorAuth.setText("Authentication error! Username is empty.");
                login.setStyle("-fx-border-color: red");
                break;
            case UsersDAO.AUTHENTICATION_OK:
                this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, A_Controller.globalUserModel);
                break;
            case UsersDAO.PASSWORD_ERROR:
                errorAuth.setText("Authentication error! Incorrect password.");
                password.setStyle("-fx-border-color: red");
                break;
            case UsersDAO.USERNAME_ERROR:
                errorAuth.setText("Authentication error! There is no such user with given username");
                login.setStyle("-fx-border-color: red");
                break;
        }
    }

    public void handleCancelButtonAction() {
        Stage stage = (Stage) closeApp.getScene().getWindow();
        stage.close();
    }

    public void createAccount(ActionEvent actionEvent) throws Exception {
        this.goToPage("Settings/registration.fxml", A_Controller.REGISTRATION_PAGE_TITLE, "");
    }

    public void initialize(URL location, ResourceBundle resources) {
        login.setStyle("-fx-border-color: inherit");
        password.setStyle("-fx-border-color: inherit");
        errorAuth.setText("");
    }
}
