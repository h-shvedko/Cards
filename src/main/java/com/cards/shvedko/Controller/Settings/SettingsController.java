package com.cards.shvedko.Controller.Settings;

import com.cards.shvedko.Controller.A_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController extends A_Controller {

    @FXML
    public Button importFromCSV;
    @FXML
    public Button cleanDB;
    @FXML
    public Button copyDB;

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
        this.goToPage("tmpListOfCards.fxml", A_Controller.IMPORT_FROM_CSV, "");
    }

    public void handleCleanDB(ActionEvent actionEvent) {
//        this.goToPage("cleanDB.fxml", A_Controller.CLEAN_DB, "");
    }

    public void handleCopyDB(ActionEvent actionEvent) {
//        this.goToPage("copyDB.fxml", A_Controller.COPY_DB, "");
    }
}
