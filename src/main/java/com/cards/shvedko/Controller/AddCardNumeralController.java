package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardNumeralController extends A_Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Add new numerical:");
        speechPart.setValue("Numerical");
        speechPart.setDisable(true);
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("addCard.fxml");}

    @Override
    public void handleAddButton(ActionEvent actionEvent) {

    }
}