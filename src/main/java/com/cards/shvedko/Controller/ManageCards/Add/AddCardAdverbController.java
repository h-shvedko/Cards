package com.cards.shvedko.Controller.ManageCards.Add;

import com.cards.shvedko.ModelDAO.ModelsDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardAdverbController extends AddCardController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Cоздать новое наречие:");
        speechPart.setValue(ModelsDAO.ADVERB);
    }
}