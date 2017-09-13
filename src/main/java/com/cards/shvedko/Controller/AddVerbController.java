package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.CardCategories;
import com.cards.shvedko.Model.CardTypes;
import com.cards.shvedko.ModelDAO.CardCategoriesDAO;
import com.cards.shvedko.ModelDAO.CardTypesDAO;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;
import org.hibernate.Query;

import java.util.List;

public class AddVerbController extends A_Controller {

    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml");
    }

    public void handlePreviewButton(ActionEvent actionEvent) {

    }

    public void handleAddButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Do really want to save this card? You haven't changed anything in native and foreign words!");
        }

        if (answer) {
            String name = nativeValue.getText();
            String value = foreignValue.getText();
            String nExample = nativeExample.getText();
            int type = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;
            int category = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;


            CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
            Object categoryObject = cardCategoriesDAO.select("where id=" + category);

            CardTypesDAO cardTypesDAO = new CardTypesDAO();
            Object typeObject = cardTypesDAO.select("where id=" + type);

            CardsDAO cardsDAO = new CardsDAO();
            cardsDAO.cards.setName(name);
            cardsDAO.cards.setValue(value);
            cardsDAO.cards.setExample(nExample);
            cardsDAO.cards.setCategory((CardCategories) categoryObject);
            cardsDAO.cards.setType((CardTypes) typeObject);
            cardsDAO.cards.setIsVisible(1);

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
    }
}
