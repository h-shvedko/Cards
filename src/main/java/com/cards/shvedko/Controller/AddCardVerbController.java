package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardVerbController extends A_Controller {

    @FXML
    public CheckBox reflexiveVerb;
    @FXML
    public RadioButton habenPerfect;
    @FXML
    public RadioButton seinPerfect;
    @FXML
    public ComboBox pronomenAkk;
    @FXML
    public ComboBox pronomenDat;
    @FXML
    public TextField pronomenGen;
    @FXML
    public TextField foreignValuePresence;
    @FXML
    public Hyperlink foreignConjunctionsPresence;
    @FXML
    public Label errorForeignValuePresence;
    @FXML
    public ImageView foreignValueVoicePresence;
    @FXML
    public TextField foreignValuePreteriturm;
    @FXML
    public Hyperlink foreignConjunctionsPreteriturm;
    @FXML
    public Label errorForeignValuePreteriturm;
    @FXML
    public ImageView foreignValueVoicePreteriturm;
    @FXML
    public TextField foreignValuePerfect;
    @FXML
    public Hyperlink foreignConjunctionsPerfect;
    @FXML
    public Label errorForeignValuePerfect;
    @FXML
    public ImageView foreignValueVoicePerfect;
    @FXML
    public RadioButton regelmassigVerb;
    @FXML
    public ToggleGroup verbType;
    @FXML
    public RadioButton unregelmassigVerb;
    @FXML
    public RadioButton hasNotTermbarePrefix;
    @FXML
    public RadioButton hasTermbarePrefix;
    @FXML
    public ToggleGroup trembareGroup;
    @FXML
    public ToggleGroup perfectGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Add new verb:");
        speechPart.setValue("Verb");
        speechPart.setDisable(true);

        regelmassigVerb.setToggleGroup(verbType);
        unregelmassigVerb.setToggleGroup(verbType);

        hasNotTermbarePrefix.setToggleGroup(trembareGroup);
        hasTermbarePrefix.setToggleGroup(trembareGroup);

        seinPerfect.setToggleGroup(perfectGroup);
        habenPerfect.setToggleGroup(perfectGroup);


    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("addCard.fxml");}

    @Override
    public void handlePreviewButton(ActionEvent actionEvent) {

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
