package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        String name = nameDeck.getText();
        String isAnchor = groupAnchor.getSelectedToggle().getUserData().toString();
        String isFavorite = groupFavorite.getSelectedToggle().getUserData().toString();
        boolean ifAllSpeechPart = allSpeechPart.isSelected();
        boolean ifAllTopic = allTopic.isSelected();
        A_Models userObject = globalUserModel;

        int speechPartValue = 0;
        A_Models categoryObject = null;
        if (!ifAllSpeechPart) {
            speechPartValue = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;
            CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();

            try {
                categoryObject = cardCategoriesDAO.select("where id=" + speechPartValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                categoryObject = getCategoryAll();
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        }

        int topicValue = 0;
        A_Models typeObject = null;
        if (!ifAllTopic) {
            topicValue = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;
            CardTypesDAO cardTypesDAO = new CardTypesDAO();

            try {
                typeObject = cardTypesDAO.select("where id=" + topicValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                typeObject = getTopicAll();
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        }

        DecksDAO decksDAO = new DecksDAO();
        if (categoryObject != null) {
            decksDAO.decks.setCategory((CardCategories) categoryObject);
        }
        if (typeObject != null) {
            decksDAO.decks.setType((CardTypes) typeObject);
        }

        decksDAO.decks.setName(name);
        decksDAO.decks.setIsAnchore(Integer.parseInt(isAnchor));
        decksDAO.decks.setIsFavorite(Integer.parseInt(isFavorite));
        decksDAO.decks.setIsVisible(1);

        if (userObject != null) {
            decksDAO.decks.setUser((Users) userObject);
        }

        if (decksDAO.validate(decksDAO.decks)) {
            try {
                decksDAO.save();
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
                DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
                for (Object card : cards) {
                    decksValuesDAO.decksValues.setCards((Cards) card);
                    decksValuesDAO.decksValues.setDecks(decksDAO.decks);
                    decksValuesDAO.saveOrUpdate();
                }
                decksValuesDAO.save();
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }

    }

    private A_Models getTopicAll() throws Exception {
        CardTypesDAO cardTypesDAO = new CardTypesDAO();
        A_Models typeObject = null;
        try {
            typeObject = cardTypesDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        assert typeObject != null;
        return typeObject;
    }

    private A_Models getCategoryAll() throws Exception {
        CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
        A_Models categoryObject = null;
        try {
            categoryObject = cardCategoriesDAO.select("where name='" + ModelsDAO.ALL_PART_OF_SPEECH + "'");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        assert categoryObject != null;
        return categoryObject;
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