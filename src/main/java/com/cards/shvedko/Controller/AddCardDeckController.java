package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardDeckController extends A_Controller {

    @FXML
    private Button cancel;
    @FXML
    private ToggleButton allSpeechPart;
    @FXML
    private ToggleButton favoriteOff;
    @FXML
    private ToggleButton anchorOn;
    @FXML
    private ToggleButton anchorOff;
    @FXML
    private Button save;
    @FXML
    private TextField nameDeck;
    @FXML
    private ToggleButton allTopic;
    @FXML
    private ToggleButton favoriteOn;

    private final ToggleGroup groupAnchor = new ToggleGroup();
    private final ToggleGroup groupFavorite = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        anchorOff.setUserData(ModelsDAO.ANCHOR_OFF);
        anchorOff.setToggleGroup(groupAnchor);

        anchorOn.setUserData(ModelsDAO.ANCHOR_ON);
        anchorOn.setToggleGroup(groupAnchor);

        favoriteOn.setUserData(ModelsDAO.FAVORITE_ON);
        favoriteOn.setToggleGroup(groupFavorite);

        favoriteOff.setUserData(ModelsDAO.FAVORITE_OFF);
        favoriteOff.setToggleGroup(groupFavorite);

    }

    public void handleSaveButton(ActionEvent actionEvent) {

    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
    }

    public void handleDisableSpeechPartCombo(ActionEvent actionEvent) {
        boolean isDisabled = speechPart.isDisabled();
        speechPart.setDisable(!isDisabled);
    }

    public void handleDisableTopicCombo(ActionEvent actionEvent) {
        boolean isDisabled = topic.isDisabled();
        topic.setDisable(!isDisabled);
    }
}