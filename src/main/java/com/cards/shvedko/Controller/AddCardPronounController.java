package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardPronounController extends A_Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Создать новое местоимение:");
        speechPart.setValue(ModelsDAO.PRONOUN);
        speechPart.setDisable(true);
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("addCard.fxml", A_Controller.CHOOSE_TYPE_OF_CARD_PAGE_TITLE, "");
    }

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Do really want to save this card? You haven't changed anything in native and foreign words!");
        }

        CardsDAO cardsDAO = null;
        if (answer) {
            cardsDAO = super.handleAddButton(actionEvent);

            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    cardsDAO.save();
                    showSuccess(actionEvent);
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage());
                }
            } else {
                showErrors(cardsDAO);
            }
        }

        return cardsDAO;
    }
}