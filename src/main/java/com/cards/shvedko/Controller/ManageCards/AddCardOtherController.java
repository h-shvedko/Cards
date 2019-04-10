package com.cards.shvedko.Controller.ManageCards;

import com.cards.shvedko.ModelDAO.ModelsDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardOtherController extends AddCardController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Создать другую часть речи:");
        speechPart.setValue(ModelsDAO.OTHER_PART_OF_SPEECH);
    }
}
