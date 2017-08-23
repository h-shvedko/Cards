package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPage extends A_Controller {

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
}
