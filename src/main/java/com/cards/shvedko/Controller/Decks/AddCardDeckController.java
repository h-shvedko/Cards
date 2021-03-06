package com.cards.shvedko.Controller.Decks;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import com.cards.shvedko.Services.DBService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;

import java.net.URL;
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
    private ToggleButton allTopic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        super.initialize(location, resources);

        if(globalUserData instanceof TextField){
            nameDeck.setText(((TextField) globalUserData).getText());
        }

        if (speechPart != null) {
            speechPart.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {

                    if (Objects.equals(newValue, ModelsDAO.VERB)) {
                        globalUserData = nameDeck;
                        goToPage("Decks/addVerbDeck.fxml", "Создать колоду с глаголами", globalUserData);
                    }
                }
            });
        }
    }

    public void handleSaveButton(ActionEvent actionEvent) {

        String name = nameDeck.getText();
        String errorMessageValue = "";

        boolean ifAllSpeechPart = allSpeechPart.isSelected();
        boolean ifAllTopic = allTopic.isSelected();
        A_Models userObject = globalUserModel;

        int speechPartValue = 0;
        A_Models typeObject = null;
        if (!ifAllSpeechPart) {
            speechPartValue = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;

            if(speechPartValue == 0){
                errorMessageValue += "Выберите часть речи!\n";
            }

            CardTypesDAO cardTypesDAO = new CardTypesDAO();

            try {
                typeObject = cardTypesDAO.select("where id=" + speechPartValue);
            } catch (Exception e) {
                crashAppeared(e.getMessage(), actionEvent);
            }
        } else {
            try {
                typeObject = getCategoryAll();
            } catch (Exception e) {
                crashAppeared(e.getMessage(), actionEvent);
            }
        }

        int topicValue = 0;
        A_Models categoryObject = null;
        if (!ifAllTopic) {
            topicValue = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;

            if(topicValue == 0){
                errorMessageValue += "Выберите тематику!\n";
            }

            CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();

            try {
                categoryObject = cardCategoriesDAO.select("where id=" + topicValue);
            } catch (Exception e) {
                crashAppeared(e.getMessage(), actionEvent);
            }
        } else {
            try {
                categoryObject = getTopicAll();
            } catch (Exception e) {
                crashAppeared(e.getMessage(), actionEvent);
            }
        }

        int levelValue = 1;
        A_Models levelObject = null;
        levelValue = Integer.parseInt(String.valueOf(level.getSelectionModel().getSelectedIndex()));

        if(levelValue == -1 || levelValue == 0){
            levelValue = 1;
        }

        CardLevelsDAO cardLevelsDAO = new CardLevelsDAO();

        try {
            levelObject = cardLevelsDAO.select("where id=" + levelValue);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }

         DecksDAO decksDAO = new DecksDAO();
        if (levelObject != null) {
            decksDAO.decks.setLevels((CardLevels) levelObject);
        }
        if (typeObject != null) {
            decksDAO.decks.setType((CardTypes) typeObject);
        }
        if (categoryObject != null) {
            decksDAO.decks.setCategory((CardCategories) categoryObject);
        }

        decksDAO.decks.setName(name);
        decksDAO.decks.setIsVisible(1);

        if (userObject != null) {
            decksDAO.decks.setUser((Users) userObject);
        }

        if (decksDAO.validate(decksDAO.decks) && errorMessageValue.length() == 0) {
            try {
                if (!decksDAO.save()) {
                    throw new Exception(decksDAO.errorMsg);
                }
                try {
                    saveDecksValues(decksDAO, actionEvent);
                } catch (Exception e) {
                    crashAppeared(e.getMessage(), actionEvent);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage(), actionEvent);
            }
        } else {
            showErrors(decksDAO);
            crashAppeared(errorMessageValue, actionEvent);
        }
    }

    private void saveDecksValues(DecksDAO decksDAO, ActionEvent actionEvent) throws Exception {
        int categoryId = decksDAO.decks.getCategory().getId();
        int typeId = decksDAO.decks.getType().getId();
        int userId = decksDAO.decks.getUser().getId();
        int deckId = decksDAO.decks.getId();
        int levelId = decksDAO.decks.getLevels().getId();

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
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
                    if (!decksDAO.decks.getLevels().getName().equals(ModelsDAO.ALL_LEVELS)) {
                        queryString += " and level_id=" + levelId;
                    }
                    cards = cardsDAO.selectAllBy(queryString);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }

                if (cards.size() > 0) {
                    final Session session = DBService.sessionFactory.getCurrentSession();

                    try {
                        StringBuilder insertString = null;
                        DecksValuesDAO decksValuesDAO = new DecksValuesDAO();

                        int i = 0;
                        for (Object card : cards) {
                            insertString = new StringBuilder("INSERT INTO DECKS_VALUES (cards_id, deck_id, is_favorite, is_anchor, is_ready, date_ready, order_in_card, count_of_appearence) VALUES ");
                            insertString.append("(");
                            insertString.append(((Cards) card).getId());
                            insertString.append(",");
                            insertString.append(deckId);
                            insertString.append(",0,0,0,null,0,0)");
                            insertString.append(";");
                            decksValuesDAO.insert(insertString, session);

                            updateProgress(i, cards.size());
//                            Thread.sleep(200);

                            i++;
                        }

                        decksValuesDAO.commit();
                    } catch (Exception ex) {
                        if (session.getTransaction() != null) session.getTransaction().rollback();
                        throw new Exception(ex.getMessage());
                    }
                }
                updateProgress(10, 10);
                setDeckValues(decksDAO.decks.getId(), actionEvent);
                return null ;
            }
        };

        String deckName = decksDAO.decks.getName();
        showSplashProgress(actionEvent, task, deckName);

        Thread thread = new Thread(task);
        thread.start();
    }

    private void setDeckValues(int deckId, ActionEvent actionEvent) {
        DecksDAO decksDAO = new DecksDAO();
        A_Models deck = null;
        try {
            deck = decksDAO.select("where id='" + deckId + "' and is_visible=1");
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }
        if (deck != null) {
            A_Controller.globalDeckData = (Decks) deck;
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
        this.goToPage("Decks/chooseDecks.fxml", A_Controller.CHOOSE_DECKS_TITLE, "");
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
        setTextToSelectedSpeechPart(ModelsDAO.ALL_PART_OF_SPEECH);
        if(selectedSpeechPart != null && selectedSpeechPart.size() > 0){
            selectedSpeechPart.clear();
        }
    }

    public void handleDisableTopicCombo(ActionEvent actionEvent) {
        boolean isDisabled = topic.isDisabled();
        topic.setDisable(!isDisabled);
        setTextToSelectedTopics(ModelsDAO.ALL_PART_OF_SPEECH);
        if(selectedTopics != null && selectedTopics.size() > 0){
            selectedTopics.clear();
        }
    }
}