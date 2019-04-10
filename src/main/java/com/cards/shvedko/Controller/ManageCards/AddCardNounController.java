package com.cards.shvedko.Controller.ManageCards;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
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
    public Label errorTypeOfNone;
    @FXML
    public Button foreignValueVoicePlural;

    private final ToggleGroup group = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Создать новое существительное:");
        speechPart.setValue(ModelsDAO.NOUN);

        maskulinum.setUserData(ModelsDAO.MUSKULINUM);
        maskulinum.setToggleGroup(group);

        femininum.setUserData(ModelsDAO.FEMININUM);
        femininum.setToggleGroup(group);

        neutrum.setUserData(ModelsDAO.NEUTRUM);
        neutrum.setToggleGroup(group);

        errorTypeOfNone.setVisible(false);

        if (foreignValuePlural != null) {
            if(foreignValueVoicePlural != null){
                foreignValueVoicePlural.setDisable(true);
            }

            foreignValuePlural.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (!Objects.equals(newValue, "")) {
                        if(foreignValueVoicePlural != null){
                            foreignValueVoicePlural.setDisable(false);
                        }
                    }
                }
            });
        }
    }

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Вы дествительно хотите сохранить картоку! Вы не внесли никаках изменений.");
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
                    if(!cardsDAO.save()){
                        throw new Exception(cardsDAO.errorMsg);
                    }
                    showSuccess(actionEvent);
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