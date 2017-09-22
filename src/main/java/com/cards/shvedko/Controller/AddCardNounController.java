package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardNounController extends A_Controller {

    @FXML
    public RadioButton maskulinum;
    @FXML
    public RadioButton femininum;
    @FXML
    public RadioButton neutrum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Add new noun:");
        speechPart.setValue("Noun");
        speechPart.setDisable(true);
        final ToggleGroup group = new ToggleGroup();

        maskulinum.setToggleGroup(group);
        femininum.setToggleGroup(group);
        neutrum.setToggleGroup(group);
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("addCard.fxml");}

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {

        return null;
    }
}