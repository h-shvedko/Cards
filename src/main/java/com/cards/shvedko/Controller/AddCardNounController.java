package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardNounController extends A_Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Add new noun:");
        speechPart.setValue("Noun");
        speechPart.setDisable(true);
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("addCard.fxml");}

    @Override
    public void handleAddButton(ActionEvent actionEvent) {

    }
}
//Add new verb: