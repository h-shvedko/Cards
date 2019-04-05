package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.Language.LanguageLabelsRu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

public class CardPreviewController extends A_CardPreviewController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

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
    }
}
