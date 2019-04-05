package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.AudioPlaying;
import com.cards.shvedko.Model.*;
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

public class A_CardPreviewController extends A_Controller {
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

    private A_Models card;

    private final ToggleGroup groupAnchor = new ToggleGroup();
    private final ToggleGroup groupFavorite = new ToggleGroup();
    private final ToggleGroup groupTrophy = new ToggleGroup();

    public ObservableList<Cards> cardsTable = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        anchorButton.setToggleGroup(groupAnchor);
        favoriteButton.setToggleGroup(groupFavorite);
        trophyButton.setToggleGroup(groupTrophy);

        card = (Cards) globalUserData;

        typeLabel.setText(((Cards) card).getType().getName());
        categoryLabel.setText(((Cards) card).getCategory().getName());

        int numberOfActiveCards = 1;
        numberOfCards.setText("Карточка " + getNumberOfElement() + " / " + String.valueOf(numberOfActiveCards));

        try {
            setValuesOfCard();
        } catch (Exception e) {
            crashAppeared(e.getMessage(), new ActionEvent());
        }
    }

    protected void setValuesOfCard() throws Exception {
        categoryLabel.setText(((Cards) card).getCategory().getName());
        categoryLabel.setWrapText(true);

        word.setText(card.getName());
        word.setWrapText(true);
        wordSound.setVisible(true);

        if (translatedWord != null) {
            setTranslatedWordValue((Cards) card);
            translatedWord.setWrapText(true);
        }

        example.setText(((Cards) card).getExample());
        example.setWrapText(true);

        if(!((Cards) card).getExample().equals("")){
            exampleSound.setVisible(true);
        } else {
            exampleSound.setVisible(false);
        }

        translatedExample.setText(((Cards) card).getForeignExample());
        translatedExample.setWrapText(true);

        numberOfCards.setWrapText(true);

        favoriteButton.setSelected(false);
        anchorButton.setSelected(false);
        trophyButton.setSelected(false);
    }

    private void setTranslatedWordValue(Cards cards) {
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

        if (cards.getKindOfNoun() == ModelsDAO.MUSKULINUM_INTO_DB) {
            value = "der " + value;
        }

        if (cards.getKindOfNoun() == ModelsDAO.FEMININUM_INTO_DB) {
            value = "die " + value;
        }

        if (cards.getKindOfNoun() == ModelsDAO.NEUTRUM_INTO_DB) {
            value = "das " + value;
        }

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
        String infinitive3 = "";

        if (!cards.getPrepositionAkk().getName().equals(CardsPrepositionAkkusativ.PREPOSITION_AKKUSATIVE_NO)) {
            akkusative = cards.getPrepositionAkk().getName();
        }

        if (!cards.getPrepositionDativ().getName().equals(CardsPrepositionDativ.PREPOSITION_DATIVE_NO)) {
            dative = cards.getPrepositionDativ().getName();
        }

        if (cards.getPrepositionGen() != null) {
            genetive = cards.getPrepositionGen();
        }

        if (cards.getIsReflexiveVerb() != 0) {
            sich = " sich ";
        }

        if (cards.getIsPerfectWithHaben() != 0) {
            haben = " / hat " + sich;
        } else {
            haben = " / ist " + sich;
        }

        if (cards.getForeignNamePreteritum() != null && !cards.getForeignNamePreteritum().equals("")) {
            preteritum = " / " + sich + cards.getForeignNamePreteritum();
        }

        if (cards.getForeignNameInfinitive() != null && !cards.getForeignNameInfinitive().equals("")) {
            infinitive3 = " / " + sich + cards.getForeignNameInfinitive();
        }

        if (cards.getForeignNamePerfect() != null && !cards.getForeignNamePerfect().equals("")) {
            perfect = " " + cards.getForeignNamePerfect();
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

        value = sich + infinitive + infinitive3 + preteritum + haben + perfect;

        translatedWord.setText(value);
    }

    public static int getNumberOfElement() {
        return 1;
    }

    public void handlePreviousButton(ActionEvent actionEvent) throws Exception {

    }

    public void handleNextButton(ActionEvent actionEvent) throws Exception {

    }

    private void markCardAsShown() throws Exception {

    }

    public void handleSettingsButton(ActionEvent actionEvent) {

    }

    public void handleEditCardButton(ActionEvent actionEvent) {
    }

    public void handleAnchorButton(ActionEvent actionEvent) throws Exception {

    }

    public void handleTrophyButton(ActionEvent actionEvent) throws Exception {

    }

    public void handleFavoriteButton(ActionEvent actionEvent) throws Exception {

    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        A_Controller.globalDeckData = null;
        this.goToPage("Decks/chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
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
