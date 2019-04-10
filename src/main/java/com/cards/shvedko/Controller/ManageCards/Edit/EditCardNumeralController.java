package com.cards.shvedko.Controller.ManageCards.Edit;

import com.cards.shvedko.ModelDAO.ModelsDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCardNumeralController extends EditCardController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        speechPart.setValue(ModelsDAO.NUMERAL);
    }
}