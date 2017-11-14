package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardController extends A_Controller {

    @FXML
    public Button previous;
    @FXML
    public Button translation;
    @FXML
    public Button sound;
    @FXML
    public Button next;
    @FXML
    public Label word;
    @FXML
    public Label translatedWord;
    @FXML
    public Label example;
    @FXML
    public Label translatedExample;
    @FXML
    public ImageView wordSound;
    @FXML
    public ImageView translatedWordSound;
    @FXML
    public ImageView exampleSound;
    @FXML
    public ImageView translatedExampleSound;

    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Cards> cardsTable = FXCollections.observableArrayList();
        List deckValues = ((Decks)A_Controller.globalUserData).getDecksValues();

        for(Object deckValue: deckValues){
            cardsTable.add(((DecksValues)deckValue).getCards());
        }

        for(Object card: cardsTable){
            word.setText(((Cards)card).getName());
        }
    }
}
