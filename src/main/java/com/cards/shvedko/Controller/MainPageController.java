package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends A_Controller {

    @FXML
    private Button addWord;
    @FXML
    private Button seeAllWords;
    @FXML
    private Button close;
    @FXML
    private Label errorMessage;

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
        if (errorMsg != null) {
            errorMessage.setText(errorMsg);
            errorMessage.setVisible(true);
        }
    }
}
