package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditCardDeckController extends A_Controller {

    @FXML
    public Button delete;
    @FXML
    public Label startCards;
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

        startCards.setWrapText(true);
        if(A_Controller.globalDeckData != null){
            int isAnchorValue = A_Controller.globalDeckData.getIsAnchore();

            if(isAnchorValue == ModelsDAO.ANCHOR_OFF){
                anchorOff.setUserData(ModelsDAO.ANCHOR_OFF);
                anchorOff.setSelected(true);
            }
            if(isAnchorValue == ModelsDAO.ANCHOR_ON){
                anchorOn.setUserData(ModelsDAO.ANCHOR_ON);
                anchorOn.setSelected(true);
            }
            anchorOff.setToggleGroup(groupAnchor);
            anchorOn.setToggleGroup(groupAnchor);

            int isFavoriteValue = A_Controller.globalDeckData.getIsFavorite();
            if(isFavoriteValue == ModelsDAO.FAVORITE_ON){
                favoriteOn.setUserData(ModelsDAO.FAVORITE_ON);
                favoriteOn.setSelected(true);
            }
            if (isFavoriteValue == ModelsDAO.FAVORITE_OFF) {
                favoriteOff.setUserData(ModelsDAO.FAVORITE_OFF);
                favoriteOff.setSelected(true);
            }
            favoriteOn.setToggleGroup(groupFavorite);
            favoriteOff.setToggleGroup(groupFavorite);

            nameDeck.setText(A_Controller.globalDeckData.getName());

            if(A_Controller.stage != null){
                nameDeck.setDisable(true);
            }

            String allSpeechPartValue = A_Controller.globalDeckData.getType().getName();
            if(allSpeechPartValue.equals(ModelsDAO.ALL_PART_OF_SPEECH)){
                allSpeechPart.setSelected(true);
                speechPart.setDisable(true);
            } else {
                speechPart.setValue(allSpeechPartValue);
            }

            String allTopicValue = A_Controller.globalDeckData.getCategory().getName();
            if(allTopicValue.equals(ModelsDAO.ALL_PART_OF_SPEECH)){
                allTopic.setSelected(true);
                topic.setDisable(true);
            } else {
                topic.setValue(allTopicValue);
            }
        } else {
            crashAppeared("Deck is not chosen!");
        }
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        String name = nameDeck.getText();
        String isAnchor = groupAnchor.getSelectedToggle().getUserData().toString();
        String isFavorite = groupFavorite.getSelectedToggle().getUserData().toString();
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

        DecksDAO decksDAO = new DecksDAO(A_Controller.globalDeckData.getId());
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
                if(!decksDAO.save()){
                    throw new Exception(decksDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }

            try {
                updateDecksValues(decksDAO);
                showSuccess(actionEvent);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
        } else {
            showErrors(decksDAO);
        }
    }

    private void updateDecksValues(DecksDAO decksDAO) throws Exception {
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

        try{
            cleanAllValues(decksDAO.decks.getDecksValues());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

        if (cards.size() > 0) {
            try {
                for (Object card : cards) {
                    DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
                    decksValuesDAO.decksValues.setCards((Cards) card);
                    decksValuesDAO.decksValues.setDecks(decksDAO.decks);
                    if(!decksValuesDAO.saveOrUpdate()){
                        throw new Exception(decksValuesDAO.errorMsg);
                    }
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }

    }

    private void cleanAllValues(List<DecksValues> decksValues) throws Exception {
        for(Object deckValue: decksValues){
            int id = ((DecksValues)deckValue).getId();
            DecksValuesDAO decksValuesDAO;
            try{
                decksValuesDAO = new DecksValuesDAO(id);
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
            if(decksValuesDAO.decksValues != null){
                decksValuesDAO.delete(decksValuesDAO.decksValues.getId());
            } else {
                decksValuesDAO.closeSession();
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

    public void handleDeleteButton(ActionEvent actionEvent) {
        DecksDAO decksDAO = new DecksDAO(A_Controller.globalDeckData.getId());
        decksDAO.decks.setIsVisible(Integer.parseInt(String.valueOf(0)));
        if (decksDAO.validate(decksDAO.decks)) {
            try {
                if(!decksDAO.saveOrUpdate()){
                    throw new Exception(decksDAO.errorMsg);
                }
                showSuccess(actionEvent);
            } catch (Exception ex) {
                crashAppeared(ex.getMessage());
            }
        } else {
            showErrors(decksDAO);
        }
    }
}