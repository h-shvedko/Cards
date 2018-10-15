package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.AudioPlaying;
import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
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

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class A_CardController extends A_Controller {
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
    public Button wordSound;
    @FXML
    public Button translatedWordSound;
    @FXML
    public Button exampleSound;
    @FXML
    public Button translatedExampleSound;
    @FXML
    public ToggleButton favoriteButton;
    @FXML
    public ToggleButton trophyButton;
    @FXML
    public ToggleButton anchorButton;
    @FXML
    public Label numberOfCards;
    @FXML
    public Label typeLabel;
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

        //Disable buttons for foreign language sounds
        translatedWordSound.setVisible(false);
        translatedExampleSound.setVisible(false);

        deckId = ((Decks) A_Controller.globalUserData).getId();
        List deckValues = ((Decks) A_Controller.globalUserData).getDecksValues();

        typeLabel.setText(((Decks) A_Controller.globalUserData).getType().getName());
        categoryLabel.setText(((Decks) A_Controller.globalUserData).getCategory().getName());

        if (((Decks) A_Controller.globalUserData).getIsFavorite() == 0) {
            for (Object deckValue : deckValues) {
                if (((DecksValues) deckValue).getIsReady() == 0 && ((DecksValues) deckValue).getIsFavorite() == 0
                        && ((DecksValues) deckValue).getDecks().getIsVisible() == 1) {
                    cardsTable.add(((DecksValues) deckValue).getCards());
                    decksValuesTable.add((DecksValues) deckValue);
                    ++numberOfActiveCards;
                    if (((DecksValues) deckValue).getIsAnchor() == 1) {
                        setNumberOfElement(numberOfActiveCards);
                        cardId = ((DecksValues) deckValue).getCards().getId();
                    }
                }
            }
        } else {
            for (Object deckValue : deckValues) {
                if (((DecksValues) deckValue).getIsReady() == 0 && ((DecksValues) deckValue).getIsFavorite() == 1
                        && ((DecksValues) deckValue).getDecks().getIsVisible() == 1) {
                    cardsTable.add(((DecksValues) deckValue).getCards());
                    decksValuesTable.add((DecksValues) deckValue);
                    ++numberOfActiveCards;
                    if (((DecksValues) deckValue).getIsAnchor() == 1) {
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

    protected void setValuesOfCard() throws Exception {
        int numberOfCurrentElement = getNumberOfElement() - 1;
        decksValues = decksValuesTable.get(numberOfCurrentElement);

        word.setText(cardsTable.get(numberOfCurrentElement).getName());
        word.setWrapText(true);

        if (translatedWord != null) {
            setTranslatedWordValue(cardsTable.get(numberOfCurrentElement));
            translatedWord.setWrapText(true);
        }


        example.setText(cardsTable.get(numberOfCurrentElement).getExample());
        example.setWrapText(true);

        translatedExample.setText(cardsTable.get(numberOfCurrentElement).getForeignExample());
        translatedExample.setWrapText(true);

        numberOfCards.setWrapText(true);

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

    private void setTranslatedWordValue(Cards cards) {
//        translatedWord.setText(.getForeignName());
        String typeOfWord = cards.getType().getName();

        switch (typeOfWord) {
            case ModelsDAO.VERB:
                generateVerbTranslation(cards);
                break;
            case ModelsDAO.NOUN:
                generateNounTranslation(cards);
                break;
            default:
                translatedWord.setText(cards.getForeignName());
                break;
        }
    }

    private void generateNounTranslation(Cards cards) {
        String value;

        value = cards.getForeignName();

        if (cards.getPluralEndung() != null && !cards.getPluralEndung().equals("")) {
            value += " (-" + cards.getPluralEndung() + ")";
        }

        translatedWord.setText(value);
    }

    private void generateVerbTranslation(Cards cards) {
        String value = "";
        String haben = "";
        String sich = "";
        String preteritum = "";
        String perfect = "";
        String akkusative = "";
        String dative = "";
        String genetive = "";
        String infinitive = "";

        if (cards.getPrepositionAkk() != null) {
            akkusative = cards.getPrepositionAkk().getName();
        }

        if (cards.getPrepositionDativ() != null) {
            dative = cards.getPrepositionDativ().getName();
        }

        if (cards.getPrepositionGen() != null) {
            genetive = cards.getPrepositionGen();
        }

        if (cards.getIsPerfectWithHaben() != 0) {
            haben = " /haben ";
        } else {
            haben = " /sein ";
        }

        if (cards.getForeignNamePreteritum() != null && !cards.getForeignNamePreteritum().equals("")) {
            preteritum = " /" + cards.getForeignNamePreteritum();
        }

        if (cards.getForeignNamePerfect() != null && !cards.getForeignNamePerfect().equals("")) {
            perfect = " " + cards.getForeignNamePerfect();
        }

        if (cards.getIsReflexiveVerb() != 0) {
            sich += " sich ";
        }

        infinitive = cards.getForeignName();

        if (!akkusative.equals("")) {
            infinitive += " (" + akkusative + " etw. Akk.)";
        }

        if (!dative.equals("")) {
            infinitive += " (" + dative + " etw. Dat.)";
        }

        if (!genetive.equals("")) {
            infinitive += " (" + genetive + " etw. Gen.)";
        }

        value = sich + infinitive + preteritum + haben + perfect;

        translatedWord.setText(value);
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

        markCardAsShown();
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

        markCardAsShown();
        numberOfCards.setText("Карточка " + numberOfWord + " / " + String.valueOf(numberOfActiveCards));
    }

    private void markCardAsShown() throws Exception {
        DecksValuesDAO decksValuesDAO = new DecksValuesDAO(decksValues.getId());
        int count = decksValuesDAO.decksValues.getCountOfAppearance();
        decksValuesDAO.decksValues.setCountOfAppearance(++count);

        if (decksValuesDAO.validate(decksValuesDAO.decksValues)) {
            try {
                if (!decksValuesDAO.saveOrUpdateDeckValues()) {
                    throw new Exception(decksValuesDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }
        } else {
            showErrors(decksValuesDAO);
        }
    }

    public void handleSettingsButton(ActionEvent actionEvent) {
        A_Controller.globalDeckData = ((DecksValues) decksValues).getDecks();
        this.openOneMoreWindow("editDeck.fxml", A_Controller.EDIT_DECK_PAGE_TITLE, "", actionEvent);
    }

    public void handleEditCardButton(ActionEvent actionEvent) {
        Cards cards = cardsTable.get(getNumberOfElement() - 1);
        switch (cards.getType().getName()) {
            case ModelsDAO.NOUN:
                openOneMoreWindow("editCardNoun.fxml", A_Controller.EDIT_NOUN_PAGE, cards, actionEvent);
                break;
            case ModelsDAO.VERB:
                openOneMoreWindow("editCardVerb.fxml", A_Controller.EDIT_VERB_PAGE, cards, actionEvent);
                break;
            case ModelsDAO.ADJECTIVE:
                openOneMoreWindow("editCardAdjective.fxml", A_Controller.EDIT_ADJECTIVE_PAGE, cards, actionEvent);
                break;
            case ModelsDAO.ADVERB:
                openOneMoreWindow("editCardAdverb.fxml", A_Controller.EDIT_ADVERB_PAGE, cards, actionEvent);
                break;
            case ModelsDAO.NUMERAL:
                openOneMoreWindow("editCardNumeral.fxml", A_Controller.EDIT_NUMERAL_PAGE, cards, actionEvent);
                break;
            case ModelsDAO.PARTICIPLE:
                openOneMoreWindow("editCardParticiple.fxml", A_Controller.EDIT_PARTICIPLE_PAGE, cards, actionEvent);
                break;
            case ModelsDAO.PRONOUN:
                openOneMoreWindow("editCardPronoun.fxml", A_Controller.EDIT_PRONOUN_PAGE, cards, actionEvent);
                break;
            default:
                openOneMoreWindow("editCardOther.fxml", A_Controller.EDIT_OTHER_PAGE, cards, actionEvent);
                break;
        }
    }

    public void handleAnchorButton(ActionEvent actionEvent) throws Exception {
        int isAnchor = ((DecksValues) decksValues).getIsAnchor();
        DecksValuesDAO decksValuesDAO = new DecksValuesDAO(decksValues.getId(), true);
        if (isAnchor == 1) {
            decksValuesDAO.decksValues.setIsAnchor(0);
        } else {
            decksValuesDAO.decksValues.setIsAnchor(1);
            cancelOtherAnchorsInArrayList((DecksValues) decksValues);
            cancelOtherAnchorsInDataBase((DecksValues) decksValues);
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

    private void cancelOtherAnchorsInDataBase(DecksValues decksCurrentValue) {
        Decks decks = ((DecksValues) decksValues).getDecks();
        List<DecksValues> decksValuesOfCurrentDeck = decks.getDecksValues();

        for (Object deckValuesOfCurrentDeck : decksValuesOfCurrentDeck) {
            if (((DecksValues) deckValuesOfCurrentDeck).getIsAnchor() == 1
                    && decksCurrentValue.getId() != ((DecksValues) deckValuesOfCurrentDeck).getId()) {
                DecksValuesDAO decksValuesDAO = new DecksValuesDAO(((DecksValues) deckValuesOfCurrentDeck).getId());
                decksValuesDAO.decksValues.setIsAnchor(0);
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
        }
    }

    private void cancelOtherAnchorsInArrayList(DecksValues decksValue) {
        for (Object deckValueTable : decksValuesTable) {
            if (((DecksValues) deckValueTable).getIsAnchor() == 1 && decksValue.getId() != ((DecksValues) deckValueTable).getId()) {
                ((DecksValues) deckValueTable).setIsAnchor(0);
            }
            if (decksValue.getId() == ((DecksValues) deckValueTable).getId()) {
                ((DecksValues) deckValueTable).setIsAnchor(1);
            }
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

    public void handleTranslationButton(ActionEvent actionEvent) {
        translatedExample.setVisible(!translatedExample.isVisible());
        translatedWord.setVisible(!translatedWord.isVisible());

        //Enable buttons for foreign language sounds
        translatedWordSound.setVisible(!translatedWordSound.isVisible());
        translatedExampleSound.setVisible(!translatedExampleSound.isVisible());
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
    }

    public void handlePlayAction(ActionEvent actionEvent) {
        String nameVoice = cardsTable.get(getNumberOfElement() - 1).getNameVoice();
        AudioPlaying.playSound(nameVoice + ".wav");
    }

    public void handlePlayAudioAction(ActionEvent actionEvent) {
        String id = ((Button) actionEvent.getSource()).getId();

        String nameVoice = "";

        switch (id) {
            case "wordSound":
                nameVoice = cardsTable.get(getNumberOfElement() - 1).getNameVoice();
                break;
            case "translatedWordSound":
                nameVoice = cardsTable.get(getNumberOfElement() - 1).getForeignNameVoice();
                break;
            case "exampleSound":
                nameVoice = cardsTable.get(getNumberOfElement() - 1).getExampleVoice();
                break;
            case "translatedExampleSound":
                nameVoice = cardsTable.get(getNumberOfElement() - 1).getForeignExampleVoice();
                break;

        }

        AudioPlaying.playSound(nameVoice + ".wav");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
