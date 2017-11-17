package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditCardPronounController extends A_Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Редактировать местоимение:");
        speechPart.setValue(ModelsDAO.PRONOUN);
        speechPart.setDisable(true);
        setData();
    }

    private void setData() {
        if (A_Controller.globalUserData != null) {

            Cards cards = (Cards) A_Controller.globalUserData;
            topic.setValue(cards.getCategory().getName());
            nativeValue.setText(cards.getName());
            nativeExample.setText(cards.getExample());
            foreignExample.setText(cards.getForeignExample());
            foreignValue.setText(cards.getForeignName());

            String speechPartValue = ((Cards) A_Controller.globalUserData).getType().getName();
            if (!Objects.equals(speechPartValue, "")) {
                speechPart.setValue(speechPartValue);
            }

            String topicValue = ((Cards) A_Controller.globalUserData).getCategory().getName();
            if (!Objects.equals(topicValue, "")) {
                topic.setValue(topicValue);
            }
        }
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, "");
    }

    @Override
    public CardsDAO handleEditButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Вы дествительно хотите сохранить картоку! Вы не внесли никаках изменений.");
        }

        CardsDAO cardsDAO = null;
        if (answer) {
            cardsDAO = super.handleEditButton(actionEvent);

            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    if (!cardsDAO.save()) {
                        throw new Exception(cardsDAO.errorMsg);
                    }
                    showSuccessEditCard(actionEvent);
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage());
                }
            } else {
                showErrors(cardsDAO);
            }
        }

        return cardsDAO;
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}