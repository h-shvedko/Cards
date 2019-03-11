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
import javafx.scene.image.ImageView;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        chooseDeckMainLabel.setText(LanguageLabelsRu.CHOOSE_DECK_HEADER);

        ObservableList<String> decks = FXCollections.observableArrayList();
        try {
            decks = DecksDAO.setAllDecks(decks);
        } catch (Exception e) {
            crashAppeared(e.getMessage());
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
        this.goToPage("addDeck.fxml", A_Controller.ADD_DECK_PAGE_TITLE, "");
    }

    public void handleStartButton(ActionEvent actionEvent) {
        this.setDeck();
        if(A_Controller.globalDeckData.getDecksValues() != null && A_Controller.globalDeckData.getDecksValues().size() > 0){
            this.goToPage("card.fxml", A_Controller.CHOOSE_CARDS_TITLE, A_Controller.globalDeckData);
        } else {
            this.openOneMoreWindow("emptyDeck.fxml", A_Controller.EMPTY_DECK_TITLE, A_Controller.globalDeckData, actionEvent);
        }

    }

    private void setDeck() {
        String deckValue = String.valueOf(decksCombo.getSelectionModel().getSelectedItem());
        DecksDAO decksDAO = new DecksDAO();
        A_Models deck = null;
        try {
            deck = decksDAO.select("where name='" + deckValue + "' and is_visible=1");
        } catch (Exception e) {
            crashAppeared(e.getMessage());
        }
        if (deck != null) {
            A_Controller.globalDeckData = (Decks) deck;
            DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
            List decksValues = new ArrayList<DecksValues>();
            try {
                decksValues = decksValuesDAO.selectAllBy("where deck_id=" + deck.getId());
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }

            if(!decksValues.isEmpty()){
                A_Controller.globalDeckData.setDecksValues(decksValues);
            }
        } else {
            errorDecks.setText("Выберите колоду!");
            errorDecks.setVisible(true);
        }
    }

    public void handleSettingsButton(ActionEvent actionEvent) {

        this.setDeck();

        if(A_Controller.globalDeckData.getType() != null && !A_Controller.globalDeckData.getType().getName().equals(ModelsDAO.VERB)){
            this.goToPage("editDeck.fxml", A_Controller.EDIT_DECK_PAGE_TITLE, A_Controller.globalDeckData);
        } else {
            this.goToPage("editVerbDeck.fxml", A_Controller.EDIT_DECK_PAGE_TITLE, A_Controller.globalDeckData);
        }
    }
}