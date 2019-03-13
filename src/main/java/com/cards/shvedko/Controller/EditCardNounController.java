package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditCardNounController extends A_Controller {

    @FXML
    public RadioButton maskulinum;
    @FXML
    public RadioButton femininum;
    @FXML
    public RadioButton neutrum;
    @FXML
    public TextField foreignValuePlural;
    @FXML
    public Button foreignValueVoicePlural;

    private final ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        speechPart.setValue(ModelsDAO.NOUN);
        speechPart.setDisable(true);

        maskulinum.setUserData(ModelsDAO.MUSKULINUM);
        maskulinum.setToggleGroup(group);

        femininum.setUserData(ModelsDAO.FEMININUM);
        femininum.setToggleGroup(group);

        neutrum.setUserData(ModelsDAO.NEUTRUM);
        neutrum.setToggleGroup(group);

        setData();
    }

    private void setData() {
        if (A_Controller.globalCardSavedData != null) {

            Cards cards = (Cards) A_Controller.globalCardSavedData;
            topic.setValue(cards.getCategory().getName());
            nativeValue.setText(cards.getName());
            nativeExample.setText(cards.getExample());
            foreignExample.setText(cards.getForeignExample());
            foreignValue.setText(cards.getForeignName());
            foreignValuePlural.setText(cards.getPluralEndung());

            switch (cards.getKindOfNoun()) {
                case ModelsDAO.FEMININUM_INTO_DB:
                    femininum.setSelected(true);
                    break;
                case ModelsDAO.MUSKULINUM_INTO_DB:
                    maskulinum.setSelected(true);
                    break;
                case ModelsDAO.NEUTRUM_INTO_DB:
                    neutrum.setSelected(true);
                    break;
            }

            String speechPartValue = ((Cards) A_Controller.globalCardSavedData).getType().getName();
            if (!Objects.equals(speechPartValue, "")) {
                speechPart.setValue(speechPartValue);
            }

            String topicValue = ((Cards) A_Controller.globalCardSavedData).getCategory().getName();
            if (!Objects.equals(topicValue, "")) {
                topic.setValue(topicValue);
            }
        }
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, "");
    }

    @Override
    public CardsDAO handleEditButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Вы дествительно хотите сохранить картоку! Вы не внесли никаках изменений.");
        }

        CardsDAO cardsDAO = null;
        if (answer) {
            cardsDAO = super.handleEditButton(actionEvent);

            String typeOfNoun = group.getSelectedToggle().getUserData().toString();
            int typeOfNounIntoDB = 1;
            switch (typeOfNoun) {
                case ModelsDAO.FEMININUM:
                    typeOfNounIntoDB = ModelsDAO.FEMININUM_INTO_DB;
                    break;
                case ModelsDAO.MUSKULINUM:
                    typeOfNounIntoDB = ModelsDAO.MUSKULINUM_INTO_DB;
                    break;
                case ModelsDAO.NEUTRUM:
                    typeOfNounIntoDB = ModelsDAO.NEUTRUM_INTO_DB;
                    break;
                default:
                    typeOfNounIntoDB = ModelsDAO.MUSKULINUM_INTO_DB;
                    break;
            }

            cardsDAO.cards.setKindOfNoun(typeOfNounIntoDB);

            String foreignPlural = foreignValuePlural.getText();
            cardsDAO.cards.setPluralEndung(foreignPlural);

            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    if (!cardsDAO.save()) {
                        throw new Exception(cardsDAO.errorMsg);
                    }
                    showSuccessEditCard(actionEvent);
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage(), actionEvent);
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