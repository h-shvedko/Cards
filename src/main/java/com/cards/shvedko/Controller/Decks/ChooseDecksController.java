package com.cards.shvedko.Controller.Decks;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Helpers.Language.LanguageLabelsRu;
import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import com.cards.shvedko.ModelDAO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseDecksController extends A_Controller {

    @FXML
    public ComboBox decks;
    @FXML
    public Button createDeck;
    @FXML
    public Button start;
    public Label chooseDeckMainLabel;
    @FXML
    public Button settingsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        settingsButton.setDisable(true);
        chooseDeckMainLabel.setText(LanguageLabelsRu.CHOOSE_DECK_HEADER);

        ObservableList<String> decks = FXCollections.observableArrayList();
        try {
            decks = DecksDAO.setAllDecks(decks);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), new ActionEvent());
        }
        decksCombo.setItems(decks);

        decksCombo.setPromptText(LanguageLabelsRu.PLACEHOLDER_SELECT_DECK);
        decksCombo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue value, String oldValue, String newValue) {
                if (errorDecks != null) {
                    errorDecks.setText("");
                    errorDecks.setVisible(false);
                }

                    settingsButton.setDisable(false);

            }
        });
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCreateButton(ActionEvent actionEvent) {
        this.goToPage("Decks/addDeck.fxml", A_Controller.ADD_DECK_PAGE_TITLE, "");
    }

    public void handleStartButton(ActionEvent actionEvent) {
        this.setDeck(actionEvent);
        if(A_Controller.globalDeckData != null && A_Controller.globalDeckData.getDecksValues() != null && A_Controller.globalDeckData.getDecksValues().size() > 0){
            this.goToPage("card.fxml", A_Controller.CHOOSE_CARDS_TITLE, A_Controller.globalDeckData);
        } else if(A_Controller.globalDeckData == null) {
            showAlertNoDeckSelected();
        } else if(A_Controller.globalDeckData != null && A_Controller.globalDeckData.getDecksValues() != null && A_Controller.globalDeckData.getDecksValues().size() == 0) {
            showAlertEmptyDeckSelected();
        }

    }

    private void setDeck(ActionEvent actionEvent) {
        String deckValue = String.valueOf(decksCombo.getSelectionModel().getSelectedItem());
        DecksDAO decksDAO = new DecksDAO();
        A_Models deck = null;
        try {
            deck = decksDAO.select("where name='" + deckValue + "' and is_visible=1");
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }
        if (deck != null) {
            A_Controller.globalDeckData = (Decks) deck;
            DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
            List decksValues = new ArrayList<DecksValues>();
            try {
                decksValues = decksValuesDAO.selectAllBy("where deck_id=" + deck.getId());
            } catch (Exception e) {
                crashAppeared(e.getMessage(), actionEvent);
            }

            if(!decksValues.isEmpty()){
                A_Controller.globalDeckData.setDecksValues(decksValues);
            }
        }
    }

    public void handleSettingsButton(ActionEvent actionEvent) {

        this.setDeck(actionEvent);

        if(A_Controller.globalDeckData == null) {
            showAlertNoDeckSelected();
        } else {
            if(A_Controller.globalDeckData.getType() != null && !A_Controller.globalDeckData.getType().getName().equals(ModelsDAO.VERB)){
                this.goToPage("Decks/editDeck.fxml", A_Controller.EDIT_DECK_PAGE_TITLE, A_Controller.globalDeckData);
            } else {
                this.goToPage("Decks/editVerbDeck.fxml", A_Controller.EDIT_DECK_PAGE_TITLE, A_Controller.globalDeckData);
            }
        }
    }
}