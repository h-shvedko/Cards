package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.Language.Language;
import com.cards.shvedko.Helpers.Language.LanguageLabelsRu;
import com.cards.shvedko.MainApp;
import com.cards.shvedko.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController extends A_Controller {

    public Button settings;
    public Button addWord;
    public Button seeAllWords;
    public Button learnCards;
    public Button profile;
    public Label seeAllWordsTitle;
    public Label addWordTitle;
    public Label profileTitle;
    public Label closeTitle;
    public Label settingsTitle;
    public Label learnCardsTitle;

    public void handleAddWordButton(ActionEvent actionEvent) {
        this.goToPage("addCard.fxml", A_Controller.CHOOSE_TYPE_OF_CARD_PAGE_TITLE, "");
    }

    public void handleSeeAllWordsButton(ActionEvent actionEvent) {
        this.goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, "");
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {

        greeting.setText(globalUserModel.getName());

        Tooltip addWordTooltip = new Tooltip();
        addWordTooltip.setText(LanguageLabelsRu.MAIN_PAGE_ADD_WORD_TOOLTIP);
        addWord.setTooltip(addWordTooltip);

        Tooltip seeAllWordsTooltip = new Tooltip();
        seeAllWordsTooltip.setText(LanguageLabelsRu.MAIN_PAGE_ALL_WORDS_TOOLTIP);
        seeAllWords.setTooltip(seeAllWordsTooltip);

        Tooltip closeTooltip = new Tooltip();
        closeTooltip.setText(LanguageLabelsRu.MAIN_PAGE_CLOSE_TOOLTIP);
        close.setTooltip(closeTooltip);

        Tooltip profileTooltip = new Tooltip();
        profileTooltip.setText(LanguageLabelsRu.MAIN_PAGE_PROFILE_TOOLTIP);
        profile.setTooltip(profileTooltip);

        Tooltip settingsTooltip = new Tooltip();
        settingsTooltip.setText(LanguageLabelsRu.MAIN_PAGE_SETTINGS_TOOLTIP);
        settings.setTooltip(settingsTooltip);

        Tooltip learnCardsTooltip = new Tooltip();
        learnCardsTooltip.setText(LanguageLabelsRu.MAIN_PAGE_LEARN_CARDS_TOOLTIP);
        learnCards.setTooltip(learnCardsTooltip);

        seeAllWordsTitle.setText(LanguageLabelsRu.MAIN_ALL_WORDS_TEXT);
        addWordTitle.setText(LanguageLabelsRu.MAIN_PAGE_ADD_WORD_TEXT);
        profileTitle.setText(LanguageLabelsRu.MAIN_PAGE_PROFILE_TEXT);
        closeTitle.setText(LanguageLabelsRu.MAIN_PAGE_CLOSE_TEXT);
        settingsTitle.setText(LanguageLabelsRu.MAIN_PAGE_SETTINGS_TEXT);
        learnCardsTitle.setText(LanguageLabelsRu.MAIN_PAGE_LEARN_CARDS_TEXT);
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleLearnCardsButton(ActionEvent actionEvent) {
        this.goToPage("chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
    }

    public void handleProfileButton(ActionEvent actionEvent) {
        this.goToPage("profile.fxml", A_Controller.PROFILE_PAGE_TITLE, "");
    }

    public void handleSettingsButton(ActionEvent actionEvent) {
        this.goToPage("settings.fxml", A_Controller.SETTINGS_PAGE_TITLE, "");
    }
}
