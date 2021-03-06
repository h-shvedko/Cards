package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.Language.LanguageLabelsRu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController extends A_CardController {

    public Button editCard;
    public Button editDeck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        editCard.setText(LanguageLabelsRu.EDIT_CARD);
        Tooltip editCardTooltip = new Tooltip();
        editCardTooltip.setText(LanguageLabelsRu.EDIT_CARD);
        editCard.setTooltip(editCardTooltip);

        editDeck.setText(LanguageLabelsRu.EDIT_DECK);
        Tooltip editDeckTooltip = new Tooltip();
        editDeckTooltip.setText(LanguageLabelsRu.EDIT_DECK);
        editDeck.setTooltip(editDeckTooltip);

        translation.setText(LanguageLabelsRu.SHOW_TRANSLATION);
        Tooltip translationTooltip = new Tooltip();
        translationTooltip.setText(LanguageLabelsRu.SHOW_TRANSLATION_TOOLTIP);
        translation.setTooltip(translationTooltip);

        sound.setText(LanguageLabelsRu.PLAY_SOUND);
        Tooltip soundTooltip = new Tooltip();
        soundTooltip.setText(LanguageLabelsRu.PLAY_SOUND_TOOLTIP);
        sound.setTooltip(soundTooltip);
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleDisplayList(ActionEvent actionEvent) {
        goToPage("listOfCardsInDeck.fxml", "Список карточек в колоде", "");
    }
}
