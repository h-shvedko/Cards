package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.ModelDAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController extends A_Controller {

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
        String name = username.getText();
        String pass = password.getText();
        String firstName = first_name.getText();
        String lastName = last_name.getText();
        boolean isError = false;

        if (!isError && (name == null || name.isEmpty())) {
            username.setStyle("-fx-border-color: red");
            userNameError.setText("You have put empty username! Please, fill username field.");
            isError = true;
        }

        if (!isError) {
            UsersDAO usersDAO = new UsersDAO();
            usersDAO.user.setName(name);
            usersDAO.user.setPassword(pass);
            usersDAO.user.setFirstName(firstName);
            usersDAO.user.setLastName(lastName);
            usersDAO.user.setIsVisible(1);
            if(usersDAO.validate(usersDAO.user)){
                try{
                    if(!usersDAO.save()){
                        throw new Exception(usersDAO.errorMsg);
                    }
                    showSuccessRegistration(actionEvent, usersDAO.user);
                } catch (Exception e){
                    crashAppeared(e.getMessage());
                }
            } else{
                showErrors(usersDAO);
            }
        }
    }

    public void handleCancelButtonAction(ActionEvent actionEvent) {
        this.goToPage("authentication.fxml", A_Controller.LOGIN_PAGE_TITLE, "");
    }

    public void initialize(URL location, ResourceBundle resources) {
        userNameError.setText("");
        passwordError.setText("");
        firstNameError.setText("");
        lastNameError.setText("");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
