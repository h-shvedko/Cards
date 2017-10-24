package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.Decks;
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
import java.util.ResourceBundle;

public class ChooseDecksController extends A_Controller {

    @FXML
    public ComboBox decks;
    @FXML
    public Button createDeck;
    @FXML
    public Button start;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        if (decksCombo != null) {
            ObservableList<String> decks = FXCollections.observableArrayList();
            decks = DecksDAO.setAllDecks(decks);
            decksCombo.setItems(decks);

            decksCombo.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorDecks.setText("");
                    errorDecks.setVisible(false);
                }
            });
        }
    }

    public void handleCreateButton(ActionEvent actionEvent) {
        this.goToPage("addDeck.fxml", A_Controller.ADD_DECK_PAGE_TITLE, "");
    }

    public void handleStartButton(ActionEvent actionEvent) {
        this.setDeck();
        this.goToPage("cards.fxml", A_Controller.CHOOSE_CARDS_TITLE, A_Controller.globalDeckData);
    }

    private void setDeck() {
        int deckValue = Integer.parseInt(String.valueOf(decksCombo.getSelectionModel().getSelectedIndex())) + 1;
        DecksDAO decksDAO = new DecksDAO();
        A_Models deck = null;
        try {
            deck = decksDAO.select("where id=" + deckValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deck != null) {
            A_Controller.globalDeckData = deck;
        } else {
            errorDecks.setText("Выберите колоду!");
            errorDecks.setVisible(true);
        }
    }

    public void handleSettingsButton(ActionEvent actionEvent) {
        this.setDeck();
        this.goToPage("editDeck.fxml", A_Controller.EDIT_DECK_PAGE_TITLE, A_Controller.globalDeckData);
    }
}