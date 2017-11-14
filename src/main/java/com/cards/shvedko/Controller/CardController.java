package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    private int numberOfElement = 0;
    public ObservableList<Cards> cardsTable = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        List deckValues = ((Decks)A_Controller.globalUserData).getDecksValues();

        for(Object deckValue: deckValues){
            cardsTable.add(((DecksValues)deckValue).getCards());
        }
        checkNumberOfWords();
        setValuesOfCard();
    }

    private void setValuesOfCard() {
        word.setText(cardsTable.get(getNumberOfElement()).getName());
        translatedWord.setText(cardsTable.get(getNumberOfElement()).getForeignName());
        example.setText(cardsTable.get(getNumberOfElement()).getExample());
        translatedExample.setText(cardsTable.get(getNumberOfElement()).getForeignExample());
    }

    public int getNumberOfElement() {
        return numberOfElement;
    }

    public void setNumberOfElement(int numberOfElement) {
        this.numberOfElement = numberOfElement;
    }

    public void handlePreviousButton(ActionEvent actionEvent) {
        int numberOfWord = getNumberOfElement();

        if(numberOfWord > 0){
            setNumberOfElement(--numberOfWord);
            setValuesOfCard();
        }

        checkNumberOfWords();
    }

    public void handleNextButton(ActionEvent actionEvent) {
        int numberOfWord = getNumberOfElement();

        if(numberOfWord < cardsTable.size() - 1){
            setNumberOfElement(++numberOfWord);
            setValuesOfCard();
        }

        checkNumberOfWords();
    }

    private void checkNumberOfWords() {
        int numberOfWord = getNumberOfElement();

        if(numberOfWord == 0){
            previous.setDisable(true);
        } else {
            previous.setDisable(false);
        }

        if(numberOfWord == cardsTable.size() - 1){
            next.setDisable(true);
        } else {
            next.setDisable(false);
        }
    }

    public void handleSettingsButton(ActionEvent actionEvent) {

    }

    public void handleEditButton(ActionEvent actionEvent) {

    }
}
