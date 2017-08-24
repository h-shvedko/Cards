package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;

public class AddVerbController extends A_Controller {

    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml");
    }

    public void handlePreviewButton(ActionEvent actionEvent) {

    }

    public void handleAddButton(ActionEvent actionEvent) {

        String name = nativeValue.getText();
        String value = foreignValue.getText();
        String nExample = nativeExample.getText();
        int type = speechPart.getSelectionModel().getSelectedIndex();
        int category = topic.getSelectionModel().getSelectedIndex();

        CardsDAO cardsDAO = new CardsDAO();
        cardsDAO.cards.setName(name);
        cardsDAO.cards.setValue(value);
        cardsDAO.cards.setExample(nExample);
        cardsDAO.cards.setCategoryId(1);
        cardsDAO.cards.setTypeId(type + 1);
        cardsDAO.cards.setIsVisible(category + 1);

        if (cardsDAO.validate(cardsDAO.cards)) {
            try {
                cardsDAO.save();
                showSuccess();
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }
        } else {
            showErrors(cardsDAO);
        }
    }
}
