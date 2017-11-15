package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.DecksValuesDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Date;
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
    @FXML
    public ToggleButton favoriteButton;
    @FXML
    public ToggleButton trophyButton;
    @FXML
    public ToggleButton anchorButton;
    @FXML
    public Label numberOfCards;
    @FXML
    public Label topicLabel;
    @FXML
    public Label categoryLabel;

    private int numberOfElement = 1;
    private int cardId = 0;
    private int deckId = 0;
    private int numberOfActiveCards = 0;
    private A_Models decksValues;

    private final ToggleGroup groupAnchor = new ToggleGroup();
    private final ToggleGroup groupFavorite = new ToggleGroup();
    private final ToggleGroup groupTrophy = new ToggleGroup();

    public ObservableList<Cards> cardsTable = FXCollections.observableArrayList();
    public ObservableList<DecksValues> decksValuesTable = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        anchorButton.setToggleGroup(groupAnchor);
        favoriteButton.setToggleGroup(groupFavorite);
        trophyButton.setToggleGroup(groupTrophy);

        translatedWord.setVisible(false);
        translatedExample.setVisible(false);

        deckId = ((Decks) A_Controller.globalUserData).getId();
        List deckValues = ((Decks) A_Controller.globalUserData).getDecksValues();

        topicLabel.setText(((Decks) A_Controller.globalUserData).getType().getName());
        categoryLabel.setText(((Decks) A_Controller.globalUserData).getCategory().getName());

        if (((Decks) A_Controller.globalUserData).getIsFavorite() == 0) {
            for (Object deckValue : deckValues) {
                if (((DecksValues) deckValue).getIsReady() == 0 && ((DecksValues) deckValue).getIsFavorite() == 0 && ((DecksValues) deckValue).getDecks().getIsVisible() == 1) {
                    cardsTable.add(((DecksValues) deckValue).getCards());
                    decksValuesTable.add((DecksValues) deckValue);
                    ++numberOfActiveCards;
                    if(((DecksValues) deckValue).getIsAnchor() == 1){
                        setNumberOfElement(numberOfActiveCards);
                        cardId = ((DecksValues) deckValue).getCards().getId();
                    }
                }
            }
        } else {
            for (Object deckValue : deckValues) {
                if (((DecksValues) deckValue).getIsReady() == 0 && ((DecksValues) deckValue).getIsFavorite() == 1 && ((DecksValues) deckValue).getDecks().getIsVisible() == 1) {
                    cardsTable.add(((DecksValues) deckValue).getCards());
                    decksValuesTable.add((DecksValues) deckValue);
                    ++numberOfActiveCards;
                    if(((DecksValues) deckValue).getIsAnchor() == 1){
                        setNumberOfElement(numberOfActiveCards);
                        cardId = ((DecksValues) deckValue).getCards().getId();
                    }
                }
            }

        }

        numberOfCards.setText("Карточка " + getNumberOfElement() + " / " + String.valueOf(numberOfActiveCards));

        try {
            setValuesOfCard();
        } catch (Exception e) {
            crashAppeared(e.getMessage());
        }
    }

    private void setValuesOfCard() throws Exception {
        decksValues = decksValuesTable.get(getNumberOfElement() - 1);
        word.setText(cardsTable.get(getNumberOfElement()).getName());
        word.setWrapText(true);
        translatedWord.setText(cardsTable.get(getNumberOfElement()).getForeignName());
        translatedWord.setWrapText(true);
        example.setText(cardsTable.get(getNumberOfElement()).getExample());
        example.setWrapText(true);
        translatedExample.setText(cardsTable.get(getNumberOfElement()).getForeignExample());
        translatedExample.setWrapText(true);
        numberOfCards.setWrapText(true);

//        getDecksValues();

        if (((DecksValues) decksValues).getIsFavorite() == 1) {
            favoriteButton.setSelected(true);
        } else {
            favoriteButton.setSelected(false);
        }

        if (((DecksValues) decksValues).getIsAnchor() == 1) {
            anchorButton.setSelected(true);
        } else {
            anchorButton.setSelected(false);
        }

        if (((DecksValues) decksValues).getIsReady() == 1) {
            trophyButton.setSelected(true);
        } else {
            trophyButton.setSelected(false);
        }
    }

    public int getNumberOfElement() {
        return numberOfElement;
    }

    public void setNumberOfElement(int numberOfElement) {
        this.numberOfElement = numberOfElement;
    }

    public void handlePreviousButton(ActionEvent actionEvent) throws Exception {
        int numberOfWord = getNumberOfElement();
        translatedWord.setVisible(false);
        translatedExample.setVisible(false);

        if (numberOfWord > 1) {
            --numberOfWord;
        } else {
            numberOfWord = cardsTable.size();
        }
        setNumberOfElement(numberOfWord);
        setValuesOfCard();

        numberOfCards.setText("Карточка " + numberOfWord + " / " + String.valueOf(numberOfActiveCards));
    }

    public void handleNextButton(ActionEvent actionEvent) throws Exception {
        int numberOfWord = getNumberOfElement();
        translatedWord.setVisible(false);
        translatedExample.setVisible(false);

        if (numberOfWord < cardsTable.size()) {
            ++numberOfWord;
        } else {
            numberOfWord = 1;
        }
        setNumberOfElement(numberOfWord);
        setValuesOfCard();

        numberOfCards.setText("Карточка " + numberOfWord + " / " + String.valueOf(numberOfActiveCards));
    }

    public void handleSettingsButton(ActionEvent actionEvent) {

    }

    public void handleEditButton(ActionEvent actionEvent) {

    }

    public void handleAnchorButton(ActionEvent actionEvent) throws Exception {
        int isAnchor = ((DecksValues) decksValues).getIsAnchor();
        DecksValuesDAO decksValuesDAO = new DecksValuesDAO(decksValues.getId());
        if (isAnchor == 1) {
            decksValuesDAO.decksValues.setIsAnchor(0);
        } else {
            decksValuesDAO.decksValues.setIsAnchor(1);
        }

        if (decksValuesDAO.validate(decksValuesDAO.decksValues)) {
            try {
                if (!decksValuesDAO.save()) {
                    throw new Exception(decksValuesDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }
        } else {
            showErrors(decksValuesDAO);
        }
    }

    public void handleTrophyButton(ActionEvent actionEvent) throws Exception {
        int isReady = ((DecksValues) decksValues).getIsReady();
        DecksValuesDAO decksValuesDAO = new DecksValuesDAO(decksValues.getId());
        String currentDate = new Date().toString();
        if (isReady == 1) {
            decksValuesDAO.decksValues.setIsReady(0);
            decksValuesDAO.decksValues.setDateReady("");
        } else {
            decksValuesDAO.decksValues.setIsReady(1);
            decksValuesDAO.decksValues.setDateReady(currentDate);
        }

        if (decksValuesDAO.validate(decksValuesDAO.decksValues)) {
            try {
                if (!decksValuesDAO.saveOrUpdate()) {
                    throw new Exception(decksValuesDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }
        } else {
            showErrors(decksValuesDAO);
        }
    }

    public void handleFavoriteButton(ActionEvent actionEvent) throws Exception {
        int isReady = ((DecksValues) decksValues).getIsFavorite();

        DecksValuesDAO decksValuesDAO = new DecksValuesDAO(decksValues.getId());
        if (isReady == 1) {
            decksValuesDAO.decksValues.setIsFavorite(0);
        } else {
            decksValuesDAO.decksValues.setIsFavorite(1);
        }

        if (decksValuesDAO.validate(decksValuesDAO.decksValues)) {
            try {
                if (!decksValuesDAO.saveOrUpdate()) {
                    throw new Exception(decksValuesDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }
        } else {
            showErrors(decksValuesDAO);
        }
    }

    private void getDecksValues() throws Exception {
        DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
        String queryString = "where deck_id=" + deckId + " and cards_id=" + cardId;
        try {
            decksValues = decksValuesDAO.select(queryString);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void handleTranslationButton(ActionEvent actionEvent) {
        translatedExample.setVisible(!translatedExample.isVisible());
        translatedWord.setVisible(!translatedWord.isVisible());
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
    }

}
