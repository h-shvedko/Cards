package com.cards.shvedko.Controller.ManageCards;

import com.cards.shvedko.ModelDAO.ModelsDAO;

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