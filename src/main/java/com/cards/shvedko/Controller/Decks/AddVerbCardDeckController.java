package com.cards.shvedko.Controller.Decks;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import com.cards.shvedko.Services.DBService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddVerbCardDeckController extends A_Controller {

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
    private ToggleButton allTopic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        if(globalUserData instanceof TextField){
            nameDeck.setText(((TextField) globalUserData).getText());
        }
        trembareLabel.setWrapText(true);

        if (speechPart != null) {
            speechPart.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (!Objects.equals(newValue, ModelsDAO.VERB)) {
                        globalUserData = nameDeck;
                        goToPage("Decks/addDeck.fxml", "Создать колоду", globalUserData);
                    }
                }
            });
        }

        trembarePrefixYes.setUserData(ModelsDAO.TREMBARE_YES);
        trembarePrefixNot.setUserData(ModelsDAO.TREMBARE_NO);
        trembarePrefixAll.setUserData(ModelsDAO.TREMBARE_ALL);
        trembarePrefixYes.setToggleGroup(trembareGroup);
        trembarePrefixNot.setToggleGroup(trembareGroup);
        trembarePrefixAll.setToggleGroup(trembareGroup);

        regelmassigAll.setUserData(ModelsDAO.REGELMESSIG_ALL);
        regelmassigNo.setUserData(ModelsDAO.REGELMESSIG_NO);
        regelmassigYes.setUserData(ModelsDAO.REGELMESSIG_YES);
        regelmassigAll.setToggleGroup(regelmessigGroup);
        regelmassigNo.setToggleGroup(regelmessigGroup);
        regelmassigYes.setToggleGroup(regelmessigGroup);

        reflexiveAll.setUserData(ModelsDAO.REFLEXIVE_ALL);
        reflexiveNo.setUserData(ModelsDAO.REFLEXIVE_NO);
        reflexiveYes.setUserData(ModelsDAO.REFLEXIVE_YES);
        reflexiveAll.setToggleGroup(reflexiveGroup);
        reflexiveNo.setToggleGroup(reflexiveGroup);
        reflexiveYes.setToggleGroup(reflexiveGroup);

        perfectAll.setUserData(ModelsDAO.PERFECT_ALL);
        perfectHaben.setUserData(ModelsDAO.PERFECT_HABEN);
        perfectSein.setUserData(ModelsDAO.PERFECT_SEIN);
        perfectAll.setToggleGroup(perfectGroup);
        perfectHaben.setToggleGroup(perfectGroup);
        perfectSein.setToggleGroup(perfectGroup);

        prefixAkkusative.setUserData(1);
        prefixAkkusative.setToggleGroup(akkusativeGroup);

        prefixGenetive.setUserData(1);
        prefixGenetive.setToggleGroup(genetiveGroup);

        prefixDative.setUserData(1);
        prefixDative.setToggleGroup(dativeGroup);

        allSpeechPart.setSelected(false);
        allSpeechPart.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    goToPage("Decks/addDeck.fxml", "Создать колоду", globalUserData);
                }
            }
        });
        speechPart.setDisable(false);
        speechPart.setValue(ModelsDAO.VERB);
    }

    public void handleSaveButton(ActionEvent actionEvent) {
        String name = nameDeck.getText();
        boolean ifAllTopic = allTopic.isSelected();
        A_Models userObject = globalUserModel;

        int speechPartValue = 0;
        A_Models typeObject = null;

        speechPartValue = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;
        CardTypesDAO cardTypesDAO = new CardTypesDAO();

        try {
            typeObject = cardTypesDAO.select("where id=" + speechPartValue);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }

        int topicValue = 0;
        A_Models categoryObject = null;
        if (!ifAllTopic) {
            topicValue = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;
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
        levelValue = Integer.parseInt(String.valueOf(level.getSelectionModel().getSelectedIndex())) + 1;

        if(levelValue == 0){
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
        if (categoryObject != null) {
            decksDAO.decks.setCategory((CardCategories) categoryObject);
        }
        if (typeObject != null) {
            decksDAO.decks.setType((CardTypes) typeObject);
        }

        decksDAO.decks.setName(name);
        decksDAO.decks.setIsVisible(1);

        if (userObject != null) {
            decksDAO.decks.setUser((Users) userObject);
        }

        String isRegelmessig = regelmessigGroup.getSelectedToggle().getUserData().toString();
        decksDAO.decks.setRegelmessig(Integer.parseInt(isRegelmessig));

        String isTrembare = trembareGroup.getSelectedToggle().getUserData().toString();
        decksDAO.decks.setTrembarePrefix(Integer.parseInt(isTrembare));

        String isReflexive = reflexiveGroup.getSelectedToggle().getUserData().toString();
        decksDAO.decks.setReflexive(Integer.parseInt(isReflexive));

        if (akkusativeGroup.getSelectedToggle() != null) {
            String isAkkusative = akkusativeGroup.getSelectedToggle().getUserData().toString();
            decksDAO.decks.setPrepositionAkkusative(Integer.parseInt(isAkkusative));
        }

        if (dativeGroup.getSelectedToggle() != null) {
            String isDative = dativeGroup.getSelectedToggle().getUserData().toString();
            decksDAO.decks.setPrepositionDative(Integer.parseInt(isDative));
        }

        if (genetiveGroup.getSelectedToggle() != null) {
            String isGenetive = genetiveGroup.getSelectedToggle().getUserData().toString();
            decksDAO.decks.setPrepositionGenetive(Integer.parseInt(isGenetive));

        }

        if (decksDAO.validate(decksDAO.decks)) {
            try {
                if (!decksDAO.save()) {
                    throw new Exception(decksDAO.errorMsg);
                }
            } catch (Exception ex) {
                crashAppeared(ex.getMessage(), actionEvent);
            }

            try {
                saveDecksValues(decksDAO);
                showSuccessStayOnPage(actionEvent);
            } catch (Exception e) {
                crashAppeared(e.getMessage(), actionEvent);
            }
        } else {
            showErrors(decksDAO);
        }
    }

    private void saveDecksValues(DecksDAO decksDAO) throws Exception {
        int categoryId = decksDAO.decks.getCategory().getId();
        int typeId = decksDAO.decks.getType().getId();
        int userId = decksDAO.decks.getUser().getId();
        int deckId = decksDAO.decks.getId();
        int levelId = decksDAO.decks.getLevels().getId();

        CardsDAO cardsDAO = new CardsDAO();
        List cards;
        String queryString = "where user_id=" + userId + " and is_visible=1";
        try {
            if (decksDAO.decks.getReflexive() == ModelsDAO.REFLEXIVE_NO) {
                queryString += " and is_reflexiv_verb=" + ModelsDAO.REFLEXIVE_NO;
            } else if (decksDAO.decks.getReflexive() == ModelsDAO.REFLEXIVE_YES) {
                queryString += " and is_reflexiv_verb=" + ModelsDAO.REFLEXIVE_YES;
            }

            if (decksDAO.decks.getRegelmessig() == ModelsDAO.REGELMESSIG_NO) {
                queryString += " and is_regular_verb=" + ModelsDAO.REGELMESSIG_NO;
            } else if (decksDAO.decks.getReflexive() == ModelsDAO.REGELMESSIG_YES) {
                queryString += " and is_regular_verb=" + ModelsDAO.REGELMESSIG_YES;
            }

            if (decksDAO.decks.getTrembarePrefix() == ModelsDAO.TREMBARE_NO) {
                queryString += " and is_trembare_prefix_verb=" + ModelsDAO.TREMBARE_NO;
            } else if (decksDAO.decks.getReflexive() == ModelsDAO.TREMBARE_YES) {
                queryString += " and is_trembare_prefix_verb=" + ModelsDAO.TREMBARE_YES;
            }

            if (decksDAO.decks.getPerfect() == ModelsDAO.PERFECT_HABEN) {
                queryString += " and is_perfect_with_haben=" + ModelsDAO.PERFECT_HABEN;
            } else if (decksDAO.decks.getReflexive() == ModelsDAO.PERFECT_SEIN) {
                queryString += " and is_perfect_with_haben=" + ModelsDAO.PERFECT_SEIN;
            }

            queryString = getPrepositionsQuery(decksDAO, queryString);

            if (!decksDAO.decks.getCategory().getName().equals(ModelsDAO.ALL_PART_OF_SPEECH)) {
                queryString += " and category_id=" + categoryId;
            }
            if (!decksDAO.decks.getType().getName().equals(ModelsDAO.ALL_PART_OF_SPEECH)) {
                queryString += " and type_id=" + typeId;
            }
            if (!decksDAO.decks.getLevels().getName().equals(ModelsDAO.ALL_LEVELS)) {
                queryString += " and level_id=" + levelId;
            } else {
                queryString += " and level_id>0";
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

                for (Object card : cards) {
                    insertString = new StringBuilder("INSERT INTO DECKS_VALUES (cards_id, deck_id, is_favorite, is_anchor, is_ready, date_ready, order_in_card, count_of_appearence) VALUES ");
                    insertString.append("(");
                    insertString.append(((Cards) card).getId());
                    insertString.append(",");
                    insertString.append(deckId);
                    insertString.append(",0,0,0,null,0,0)");
                    insertString.append(";");
                    decksValuesDAO.insert(insertString, session);
                }

                decksValuesDAO.commit();
            } catch (Exception ex) {
                if (session.getTransaction() != null) session.getTransaction().rollback();
                throw new Exception(ex.getMessage());
            }
        }

    }

    private String getPrepositionsQuery(DecksDAO decksDAO, String queryString) {
        if (decksDAO.decks.getPrepositionAkkusative() == 1 && decksDAO.decks.getPrepositionDative() == 1 && decksDAO.decks.getPrepositionGenetive() == 1) {
            queryString += " and (preposition_akk>1 or preposition_dat>1 or preposition_gen!=NULL)";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 1 && decksDAO.decks.getPrepositionDative() == 1 && decksDAO.decks.getPrepositionGenetive() == 0) {
            queryString += " and (preposition_akk>1 or preposition_dat>1)";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 1 && decksDAO.decks.getPrepositionDative() == 0 && decksDAO.decks.getPrepositionGenetive() == 0) {
            queryString += " and preposition_akk>1";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 1 && decksDAO.decks.getPrepositionDative() == 0 && decksDAO.decks.getPrepositionGenetive() == 1) {
            queryString += " and (preposition_akk>1 or preposition_gen!=NULL)";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 0 && decksDAO.decks.getPrepositionDative() == 1 && decksDAO.decks.getPrepositionGenetive() == 1) {
            queryString += " and (preposition_dat>1 or preposition_gen!=NULL)";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 0 && decksDAO.decks.getPrepositionDative() == 1 && decksDAO.decks.getPrepositionGenetive() == 0) {
            queryString += " and preposition_dat>1";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 0 && decksDAO.decks.getPrepositionDative() == 0 && decksDAO.decks.getPrepositionGenetive() == 1) {
            queryString += " and preposition_gen!=NULL";
        } else if (decksDAO.decks.getPrepositionAkkusative() == 0 && decksDAO.decks.getPrepositionDative() == 0 && decksDAO.decks.getPrepositionGenetive() == 0) {
            return queryString;
        }

        return queryString;
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
        globalUserData = nameDeck;
        goToPage("Decks/addDeck.fxml", "Создать колоду", globalUserData);
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