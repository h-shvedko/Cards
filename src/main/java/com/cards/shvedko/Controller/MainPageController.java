package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends A_Controller {

    @FXML
    private Button addWord;
    @FXML
    private Button seeAllWords;
    @FXML
    private Button close;

    public void handleAddWordButton(ActionEvent actionEvent) {
        goToPage("addCardVerb.fxml");
    }

    public void handleSeeAllWordsButton(ActionEvent actionEvent) {
        goToPage("listOfCards.fxml");
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        this.closeWindow(close);
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
