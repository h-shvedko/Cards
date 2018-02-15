package com.cards.shvedko.Controller;

import com.cards.shvedko.MainApp;
import com.cards.shvedko.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends A_Controller {

    public Button settings;

    public void handleAddWordButton(ActionEvent actionEvent) {
        this.goToPage("addCard.fxml", A_Controller.CHOOSE_TYPE_OF_CARD_PAGE_TITLE, "");
    }

    public void handleSeeAllWordsButton(ActionEvent actionEvent) {
        this.goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, "");
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
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

    public void handleLearnCardsButton(ActionEvent actionEvent) {
        this.goToPage("chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
    }

    public void handleProfileButton(ActionEvent actionEvent) {
        this.goToPage("profile.fxml", A_Controller.PROFILE_PAGE_TITLE, "");
    }

    public void handleSettingsButton(ActionEvent actionEvent) {
        this.goToPage("settings.fxml", A_Controller.SETTINGS_PAGE_TITLE, "");
    }
}
