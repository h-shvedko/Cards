package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditVerbCardDeckController extends A_Controller {

    @FXML
    public ToggleButton trembarePrefixYes;
    @FXML
    public ToggleButton trembarePrefixNot;
    @FXML
    public ToggleButton trembarePrefixAll;
    protected final ToggleGroup trembareGroup = new ToggleGroup();


    @FXML
    public ToggleButton regelmassigAll;
    @FXML
    public ToggleButton regelmassigNo;
    @FXML
    public ToggleButton regelmassigYes;
    protected final ToggleGroup regelmessigGroup = new ToggleGroup();

    @FXML
    public ToggleButton reflexiveAll;
    @FXML
    public ToggleButton reflexiveNo;
    @FXML
    public ToggleButton reflexiveYes;
    protected final ToggleGroup reflexiveGroup = new ToggleGroup();

    @FXML
    public ToggleButton perfectAll;
    @FXML
    public ToggleButton perfectSein;
    @FXML
    public ToggleButton perfectHaben;
    protected final ToggleGroup perfectGroup = new ToggleGroup();

    @FXML
    public ToggleButton prefixGenetive;
    protected final ToggleGroup genetiveGroup = new ToggleGroup();
    @FXML
    public ToggleButton prefixDative;
    protected final ToggleGroup dativeGroup = new ToggleGroup();
    @FXML
    public ToggleButton prefixAkkusative;
    protected final ToggleGroup akkusativeGroup = new ToggleGroup();

    @FXML
    public Label trembareLabel;

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

        trembareLabel.setWrapText(true);

        if (A_Controller.globalDeckData != null) {
            int isTrembarePrefix = A_Controller.globalDeckData.getTrembarePrefix();

            if (isTrembarePrefix == ModelsDAO.TREMBARE_YES) {
                trembarePrefixYes.setUserData(ModelsDAO.TREMBARE_YES);
                trembarePrefixYes.setSelected(true);
                trembarePrefixYes.setToggleGroup(trembareGroup);
            }
            if (isTrembarePrefix == ModelsDAO.TREMBARE_NO) {
                trembarePrefixNot.setUserData(ModelsDAO.TREMBARE_NO);
                trembarePrefixNot.setSelected(true);
                trembarePrefixNot.setToggleGroup(trembareGroup);
            }
            if (isTrembarePrefix == ModelsDAO.TREMBARE_ALL) {
                trembarePrefixAll.setUserData(ModelsDAO.TREMBARE_ALL);
                trembarePrefixAll.setSelected(true);
                trembarePrefixAll.setToggleGroup(trembareGroup);
            }

            int isRegelmassig = A_Controller.globalDeckData.getRegelmessig();

            if (isRegelmassig == ModelsDAO.REGELMESSIG_YES) {
                regelmassigYes.setUserData(ModelsDAO.REGELMESSIG_YES);
                regelmassigYes.setSelected(true);
            }
            if (isRegelmassig == ModelsDAO.REGELMESSIG_NO) {
                regelmassigNo.setUserData(ModelsDAO.REGELMESSIG_NO);
                regelmassigNo.setSelected(true);
            }
            if (isRegelmassig == ModelsDAO.REGELMESSIG_ALL) {
                regelmassigAll.setUserData(ModelsDAO.REGELMESSIG_ALL);
                regelmassigAll.setSelected(true);
            }

            regelmassigYes.setToggleGroup(regelmessigGroup);
            regelmassigNo.setToggleGroup(regelmessigGroup);
            regelmassigAll.setToggleGroup(regelmessigGroup);

            int isReflexive = A_Controller.globalDeckData.getReflexive();

            if (isReflexive == ModelsDAO.REFLEXIVE_ALL) {
                reflexiveAll.setUserData(ModelsDAO.REFLEXIVE_ALL);
                reflexiveAll.setSelected(true);
            }
            if (isReflexive == ModelsDAO.REFLEXIVE_NO) {
                reflexiveNo.setUserData(ModelsDAO.REFLEXIVE_NO);
                reflexiveNo.setSelected(true);
            }
            if (isReflexive == ModelsDAO.REFLEXIVE_YES) {
                reflexiveYes.setUserData(ModelsDAO.REFLEXIVE_YES);
                reflexiveYes.setSelected(true);
            }

            reflexiveYes.setToggleGroup(reflexiveGroup);
            reflexiveNo.setToggleGroup(reflexiveGroup);
            reflexiveAll.setToggleGroup(reflexiveGroup);

            int isHaben = A_Controller.globalDeckData.getPerfect();

            if (isHaben == ModelsDAO.PERFECT_HABEN) {
                perfectHaben.setUserData(ModelsDAO.PERFECT_HABEN);
                perfectHaben.setSelected(true);
            }
            if (isHaben == ModelsDAO.PERFECT_SEIN) {
                perfectSein.setUserData(ModelsDAO.PERFECT_SEIN);
                perfectSein.setSelected(true);
            }
            if (isHaben == ModelsDAO.PERFECT_ALL) {
                perfectAll.setUserData(ModelsDAO.PERFECT_ALL);
                perfectAll.setSelected(true);
            }

            perfectSein.setToggleGroup(perfectGroup);
            perfectHaben.setToggleGroup(perfectGroup);
            perfectAll.setToggleGroup(perfectGroup);

            int isAkkusative = A_Controller.globalDeckData.getPrepositionAkkusative();

            if (isAkkusative == ModelsDAO.ANCHOR_ON) {
                prefixAkkusative.setUserData(ModelsDAO.ANCHOR_ON);
                prefixAkkusative.setSelected(true);
            }
            prefixAkkusative.setToggleGroup(akkusativeGroup);

            int isDative = A_Controller.globalDeckData.getPrepositionDative();

            if (isDative == ModelsDAO.ANCHOR_ON) {
                prefixDative.setUserData(ModelsDAO.ANCHOR_ON);
                prefixDative.setSelected(true);
            }
            prefixDative.setToggleGroup(dativeGroup);

            int isGenetive = A_Controller.globalDeckData.getPrepositionGenetive();

            if (isGenetive == ModelsDAO.ANCHOR_ON) {
                prefixGenetive.setUserData(ModelsDAO.ANCHOR_ON);
                prefixGenetive.setSelected(true);
            }
            prefixGenetive.setToggleGroup(genetiveGroup);

            int isAnchorValue = A_Controller.globalDeckData.getIsAnchore();

            if (isAnchorValue == ModelsDAO.ANCHOR_OFF) {
                anchorOff.setUserData(ModelsDAO.ANCHOR_OFF);
                anchorOff.setSelected(true);
            }
            if (isAnchorValue == ModelsDAO.ANCHOR_ON) {
                anchorOn.setUserData(ModelsDAO.ANCHOR_ON);
                anchorOn.setSelected(true);
            }
            anchorOff.setToggleGroup(groupAnchor);
            anchorOn.setToggleGroup(groupAnchor);

            int isFavoriteValue = A_Controller.globalDeckData.getIsFavorite();
            if (isFavoriteValue == ModelsDAO.FAVORITE_ON) {
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

            if (A_Controller.stage != null) {
                nameDeck.setDisable(true);
            }

            String allSpeechPartValue = A_Controller.globalDeckData.getType().getName();

            allSpeechPart.setDisable(true);
            speechPart.setValue(allSpeechPartValue);


            String allTopicValue = A_Controller.globalDeckData.getCategory().getName();
            if (allTopicValue.equals(ModelsDAO.ALL_PART_OF_SPEECH)) {
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
                if (!decksDAO.save()) {
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

        try {
            cleanAllValues(decksDAO.decks.getDecksValues());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        if (cards.size() > 0) {
            try {
                for (Object card : cards) {
                    DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
                    decksValuesDAO.decksValues.setCards((Cards) card);
                    decksValuesDAO.decksValues.setDecks(decksDAO.decks);
                    if (!decksValuesDAO.saveOrUpdate()) {
                        throw new Exception(decksValuesDAO.errorMsg);
                    }
                }
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }

    }

    private void cleanAllValues(List<DecksValues> decksValues) throws Exception {
        for (Object deckValue : decksValues) {
            int id = ((DecksValues) deckValue).getId();
            DecksValuesDAO decksValuesDAO;
            try {
                decksValuesDAO = new DecksValuesDAO(id);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            if (decksValuesDAO.decksValues != null) {
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
                if (!decksDAO.saveOrUpdate()) {
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