package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends A_Controller {

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
        String pass = password.getText();
        String firstName = first_name.getText();
        String lastName = last_name.getText();

        UsersDAO usersDAO = new UsersDAO(globalUserModel.getId());
        usersDAO.user.setPassword(pass);
        usersDAO.user.setFirstName(firstName);
        usersDAO.user.setLastName(lastName);
        if (usersDAO.validate(usersDAO.user)) {
            try {
                if (!usersDAO.save()) {
                    throw new Exception(usersDAO.errorMsg);
                }
                A_Controller.globalUserModel = usersDAO.user;
                showSuccessProfile(actionEvent);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        } else {
            showErrors(usersDAO);
        }
    }

    public void handleCancelButtonAction(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, "");
    }

    public void initialize(URL location, ResourceBundle resources) {
        username.setText(globalUserModel.getName());
        password.setText(globalUserModel.getPassword());
        first_name.setText(globalUserModel.getFirstName());
        last_name.setText(globalUserModel.getLastName());
        firstNameError.setText("");
        lastNameError.setText("");
        passwordError.setText("");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
