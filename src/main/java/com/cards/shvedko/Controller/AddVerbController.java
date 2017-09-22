package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.CardCategories;
import com.cards.shvedko.Model.CardTypes;
import com.cards.shvedko.ModelDAO.CardCategoriesDAO;
import com.cards.shvedko.ModelDAO.CardTypesDAO;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddVerbController extends A_Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Add new verb:");
        speechPart.setValue("Verb");
        speechPart.setDisable(true);
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("addCard.fxml");}

    @Override
    public void handlePreviewButton(ActionEvent actionEvent) {

    }

    @Override
    public void handleAddButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Do really want to save this card? You haven't changed anything in native and foreign words!");
        }

        if (answer) {
            String name = nativeValue.getText();
            String value = foreignValue.getText();
            String nExample = nativeExample.getText();
            String fExample = foreignExample.getText();
            int type = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;
            int category = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;


            CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
            A_Models categoryObject = null;
            try {
                categoryObject = cardCategoriesDAO.select("where id=" + category);
            } catch (Exception e) {
                e.printStackTrace();
            }

            CardTypesDAO cardTypesDAO = new CardTypesDAO();
            A_Models typeObject = null;
            try {
                typeObject = cardTypesDAO.select("where id=" + type);
            } catch (Exception e) {
                e.printStackTrace();
            }

            CardsDAO cardsDAO = new CardsDAO();
            cardsDAO.cards.setName(name);
            cardsDAO.cards.setValue(value);
            cardsDAO.cards.setExample(nExample);
            cardsDAO.cards.setForeignExample(fExample);

            if(categoryObject != null){
                cardsDAO.cards.setCategory((CardCategories) categoryObject);
            }

            if(typeObject != null){
                cardsDAO.cards.setType((CardTypes) typeObject);
            }

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
