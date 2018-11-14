package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddCardDeckController extends A_Controller {

    @FXML
    private Button cancel;
    @FXML
    private ToggleButton allSpeechPart;
    @FXML
    private Button save;
    @FXML
    private TextField nameDeck;
    @FXML
    private ToggleButton allTopic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        super.initialize(location, resources);

        if (speechPart != null) {
            speechPart.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if(Objects.equals(newValue, ModelsDAO.VERB)){
                        goToPage("addVerbDeck.fxml", "Создать колоду с глаголами", globalUserData);
                    }
                }
            });
        }
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        String name = nameDeck.getText();
        boolean ifAllSpeechPart = allSpeechPart.isSelected();
        boolean ifAllTopic = allTopic.isSelected();
        A_Models userObject = globalUserModel;

        int speechPartValue = 0;
        A_Models typeObject = null;
        if (!ifAllSpeechPart) {
            speechPartValue = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;
            CardTypesDAO cardTypesDAO = new CardTypesDAO();

            try {
                typeObject = cardTypesDAO.select("where id=" + speechPartValue);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        } else {
            try {
                typeObject = getCategoryAll();
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        }

        int topicValue = 0;
        A_Models categoryObject = null;
        if (!ifAllTopic) {
            topicValue = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;
            CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();

            try {
                categoryObject = cardCategoriesDAO.select("where id=" + topicValue);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        } else {
            try {
                categoryObject = getTopicAll();
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        }

        int levelValue = 0;
        A_Models levelObject = null;
        levelValue = Integer.parseInt(String.valueOf(level.getSelectionModel().getSelectedIndex())) + 1;
        CardLevelsDAO cardLevelsDAO = new CardLevelsDAO();

        try {
            levelObject = cardLevelsDAO.select("where id=" + levelValue);
        } catch (Exception e) {
            crashAppeared(e.getMessage());
        }

        DecksDAO decksDAO = new DecksDAO();
        if (levelObject != null) {
            decksDAO.decks.setCategory((CardCategories) levelObject);
        }
        if (typeObject != null) {
            decksDAO.decks.setType((CardTypes) typeObject);
        }

        decksDAO.decks.setName(name);
        decksDAO.decks.setIsVisible(1);

        if (userObject != null) {
            decksDAO.decks.setUser((Users) userObject);
        }

        if (decksDAO.validate(decksDAO.decks)) {
            try {
                if(!decksDAO.save()){
                    throw new Exception(decksDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }

            try {
                saveDecksValues(decksDAO);
                showSuccess(actionEvent);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        } else {
            showErrors(decksDAO);
        }
    }

    private void saveDecksValues(DecksDAO decksDAO) throws Exception {
        int categoryId = decksDAO.decks.getCategory().getId();
        int typeId = decksDAO.decks.getType().getId();
        int userId = decksDAO.decks.getUser().getId();

        CardsDAO cardsDAO = new CardsDAO();
        List cards;
        String queryString = "where user_id=" + userId + " and is_visible=1";
        try {
            if (!decksDAO.decks.getCategory().getName().equals(ModelsDAO.ALL_PART_OF_SPEECH)) {
                queryString += " and category_id=" + categoryId;
            }
            if (!decksDAO.decks.getType().getName().equals(ModelsDAO.ALL_PART_OF_SPEECH)) {
                queryString += " and type_id=" + typeId;
            }
            cards = cardsDAO.selectAllBy(queryString);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        if (cards.size() > 0) {
            try {
                for (Object card : cards) {
                    DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
                    decksValuesDAO.decksValues.setCards((Cards) card);
                    decksValuesDAO.decksValues.setDecks(decksDAO.decks);
                    if(!decksValuesDAO.saveOrUpdateDeckValues()){
                        throw new Exception(decksValuesDAO.errorMsg);
                    }
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }

    }

    private A_Models getTopicAll() throws Exception {
        CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
        A_Models categoryObject;
        try {
            categoryObject = cardCategoriesDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        assert categoryObject != null;
        return categoryObject;
    }

    private A_Models getCategoryAll() throws Exception {
        CardTypesDAO cardTypesDAO = new CardTypesDAO();
        A_Models typeObject;
        try {
            typeObject = cardTypesDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        assert typeObject != null;
        return typeObject;
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

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