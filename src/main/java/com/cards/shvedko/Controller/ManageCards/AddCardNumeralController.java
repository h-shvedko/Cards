package com.cards.shvedko.Controller.ManageCards;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardNumeralController extends AddCardController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Создать новове числительное:");
        speechPart.setValue(ModelsDAO.NUMERAL);
    }
}