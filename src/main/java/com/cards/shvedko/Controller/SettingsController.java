package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController extends A_Controller {

    public Button importFromCSV;
    public Button cleanDB;

    public void handleCancelButtonAction(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, "");
    }

    public void initialize(URL location, ResourceBundle resources) {
        greeting.setText(globalUserModel.getName());
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleImportFromCSV(ActionEvent actionEvent) {


    }

    public void handleCleanDB(ActionEvent actionEvent) {

    }
}
