package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardNounController extends A_Controller {

    @FXML
    public RadioButton maskulinum;
    @FXML
    public RadioButton femininum;
    @FXML
    public RadioButton neutrum;
    @FXML
    public TextField foreignValuePlural;
    @FXML
    public ImageView foreignValueVoicePlural;

    private final ToggleGroup group = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Add new noun:");
        speechPart.setValue("Noun");
        speechPart.setDisable(true);

        maskulinum.setUserData(ModelsDAO.MUSKULINUM);
        maskulinum.setToggleGroup(group);

        femininum.setUserData(ModelsDAO.FEMININUM);
        femininum.setToggleGroup(group);

        neutrum.setUserData(ModelsDAO.NEUTRUM);
        neutrum.setToggleGroup(group);
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