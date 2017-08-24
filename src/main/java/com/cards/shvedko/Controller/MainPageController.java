package com.cards.shvedko.Controller;

import com.cards.shvedko.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends A_Controller {

    @FXML
    private GridPane grid;
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
        RowConstraints rowConstraint = grid.getRowConstraints().get(0);
        rowConstraint.setPercentHeight(0);
        if (errorMsg != null) {
            rowConstraint.setPercentHeight(25);
            errorMessage.setText(errorMsg);
            errorMessage.setVisible(true);
        }
    }
}
