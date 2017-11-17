package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.CardsPrepositionAkkusativ;
import com.cards.shvedko.Model.CardsPrepositionDativ;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.CardsPrepositionAkkusativDAO;
import com.cards.shvedko.ModelDAO.CardsPrepositionDativDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditCardVerbController extends A_Controller {

    @FXML
    public CheckBox reflexiveVerb;
    @FXML
    public RadioButton habenPerfect;
    @FXML
    public RadioButton seinPerfect;
    @FXML
    public ComboBox<String> pronomenAkk;
    @FXML
    public ComboBox<String> pronomenDat;
    @FXML
    public TextField pronomenGen;
    @FXML
    public TextField foreignValuePresence;
    @FXML
    public Hyperlink foreignConjunctionsPresence;
    @FXML
    public Label errorForeignValuePresence;
    @FXML
    public ImageView foreignValueVoicePresence;
    @FXML
    public TextField foreignValuePreteriturm;
    @FXML
    public Hyperlink foreignConjunctionsPreteriturm;
    @FXML
    public Label errorForeignValuePreteriturm;
    @FXML
    public ImageView foreignValueVoicePreteriturm;
    @FXML
    public TextField foreignValuePerfect;
    @FXML
    public Hyperlink foreignConjunctionsPerfect;
    @FXML
    public Label errorForeignValuePerfect;
    @FXML
    public ImageView foreignValueVoicePerfect;
    @FXML
    public RadioButton regelmassigVerb;
    @FXML
    public ToggleGroup verbType;
    @FXML
    public RadioButton unregelmassigVerb;
    @FXML
    public RadioButton hasNotTermbarePrefix;
    @FXML
    public RadioButton hasTermbarePrefix;
    @FXML
    public ToggleGroup trembareGroup;
    @FXML
    public ToggleGroup perfectGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        titleOfAddCard.setText("Редактировать глагол:");
        speechPart.setValue(ModelsDAO.VERB);
        speechPart.setDisable(true);

        regelmassigVerb.setUserData(ModelsDAO.REGELMESSIG_VERB);
        regelmassigVerb.setToggleGroup(verbType);
        unregelmassigVerb.setUserData(ModelsDAO.UNREGELMESSIG_VERB);
        unregelmassigVerb.setToggleGroup(verbType);

        hasNotTermbarePrefix.setUserData(ModelsDAO.TREMBARE_PREFIX_VERB);
        hasNotTermbarePrefix.setToggleGroup(trembareGroup);
        hasTermbarePrefix.setUserData(ModelsDAO.UMTREMBARE_PREFIX_VERB_VERB);
        hasTermbarePrefix.setToggleGroup(trembareGroup);

        seinPerfect.setUserData(ModelsDAO.SEIN_PERFECT);
        seinPerfect.setToggleGroup(perfectGroup);
        habenPerfect.setUserData(ModelsDAO.HABEN_PERFECT);
        habenPerfect.setToggleGroup(perfectGroup);

        if (pronomenAkk != null) {
            ObservableList<String> dataAkk = FXCollections.observableArrayList();
            try {
                dataAkk = CardsPrepositionAkkusativDAO.setAllPrepositions(dataAkk);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
            pronomenAkk.setItems(dataAkk);

            pronomenAkk.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorTopic.setText("");
                    errorTopic.setVisible(false);
                }
            });
        }

        if (pronomenDat != null) {
            ObservableList<String> dataDat = FXCollections.observableArrayList();
            try {
                dataDat = CardsPrepositionDativDAO.setAllPrepositions(dataDat);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
            pronomenDat.setItems(dataDat);

            pronomenDat.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorTopic.setText("");
                    errorTopic.setVisible(false);
                }
            });
        }
        setData();
    }

    private void setData() {
        if(A_Controller.globalUserData != null){

            Cards cards = (Cards)A_Controller.globalUserData;
            topic.setValue(cards.getCategory().getName());
            nativeValue.setText(cards.getName());
            nativeExample.setText(cards.getExample());
            foreignExample.setText(cards.getForeignExample());
            foreignValue.setText(cards.getForeignName());

            if (cards.getIsRegularVerb() == 1) {
                regelmassigVerb.setSelected(true);
                unregelmassigVerb.setSelected(false);
            } else {
                regelmassigVerb.setSelected(true);
                unregelmassigVerb.setSelected(false);
            }

            if (cards.getIsTrembarePrefixVerb() == 1) {
                hasNotTermbarePrefix.setSelected(false);
                hasTermbarePrefix.setSelected(true);
            } else {
                hasNotTermbarePrefix.setSelected(true);
                hasTermbarePrefix.setSelected(false);
            }

            if (cards.getIsPerfectWithHaben() == 1) {
                seinPerfect.setSelected(false);
                habenPerfect.setSelected(true);
            } else {
                seinPerfect.setSelected(true);
                habenPerfect.setSelected(false);
            }

            pronomenAkk.setValue(cards.getPrepositionAkk().getName());
            pronomenDat.setValue(cards.getPrepositionDativ().getName());
            pronomenGen.setText(cards.getPrepositionGen());

            String speechPartValue = ((Cards)A_Controller.globalUserData).getType().getName();
            if(!Objects.equals(speechPartValue, "")){
                speechPart.setValue(speechPartValue);
            }

            String topicValue = ((Cards)A_Controller.globalUserData).getCategory().getName();
            if(!Objects.equals(topicValue, "")){
                topic.setValue(topicValue);
            }
        }
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("listOfCards.fxml", A_Controller.LIST_OF_CARDS_TITLE, "");
    }

    @Override
    public CardsDAO handleEditButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Вы дествительно хотите сохранить картоку! Вы не внесли никаках изменений.");
        }

        CardsDAO cardsDAO = null;
        if (answer) {
            cardsDAO = super.handleEditButton(actionEvent);

            cardsDAO.cards.setIsPerfectWithHaben(getPerfectType());
            cardsDAO.cards.setIsRegularVerb(getVerbType());
            cardsDAO.cards.setIsTrembarePrefixVerb(getPrefixType());
            cardsDAO.cards.setIsReflexiveVerb(getIsReflexive());

            //setting dative preposition
            int prepositionDat = Integer.parseInt(String.valueOf(pronomenDat.getSelectionModel().getSelectedIndex())) + 1;
            CardsPrepositionDativDAO cardsPrepositionDativDAO = new CardsPrepositionDativDAO();
            A_Models dativObject = null;
            try {
                dativObject = cardsPrepositionDativDAO.select("where id=" + prepositionDat);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
            if (dativObject != null) {
                cardsDAO.cards.setPrepositionDativ((CardsPrepositionDativ) dativObject);
            }

            //setting akkusative preposition
            int prepositionAkk = Integer.parseInt(String.valueOf(pronomenAkk.getSelectionModel().getSelectedIndex())) + 1;
            CardsPrepositionAkkusativDAO cardsPrepositionAkkusativDAO = new CardsPrepositionAkkusativDAO();
            A_Models akkObject = null;
            try {
                akkObject = cardsPrepositionAkkusativDAO.select("where id=" + prepositionAkk);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
            if (akkObject != null) {
                cardsDAO.cards.setPrepositionAkk((CardsPrepositionAkkusativ) akkObject);
            }

            //setting genetive preposition
            String genPronomenValue = pronomenGen.getText();
            cardsDAO.cards.setPrepositionGen(genPronomenValue);

            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    if (!cardsDAO.save()) {
                        throw new Exception(cardsDAO.errorMsg);
                    }
                    showSuccess(actionEvent);
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage());
                }
            } else {
                showErrors(cardsDAO);
            }
        }

        return cardsDAO;
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    private int getIsReflexive() {

        int ret = 0;

        if (reflexiveVerb.isSelected()) {
            ret = 1;
        }
        return ret;
    }

    private int getPerfectType() {
        String typeOfPerfect = perfectGroup.getSelectedToggle().getUserData().toString();
        int typeOfPerfectIntoDB;
        switch (typeOfPerfect) {
            case ModelsDAO.HABEN_PERFECT:
                typeOfPerfectIntoDB = ModelsDAO.HABEN_PERFECT_TO_DB;
                break;
            case ModelsDAO.SEIN_PERFECT:
                typeOfPerfectIntoDB = ModelsDAO.SEIN_PERFECT_TO_DB;
                break;
            default:
                typeOfPerfectIntoDB = ModelsDAO.HABEN_PERFECT_TO_DB;
                break;
        }

        return typeOfPerfectIntoDB;
    }

    private int getVerbType() {
        String typeOfVerb = verbType.getSelectedToggle().getUserData().toString();
        int typeOfVerbIntoDB;
        switch (typeOfVerb) {
            case ModelsDAO.REGELMESSIG_VERB:
                typeOfVerbIntoDB = ModelsDAO.REGELMESSIG_VERB_TO_DB;
                break;
            case ModelsDAO.UNREGELMESSIG_VERB:
                typeOfVerbIntoDB = ModelsDAO.UNREGELMESSIG_VERB_TO_DB;
                break;
            default:
                typeOfVerbIntoDB = ModelsDAO.REGELMESSIG_VERB_TO_DB;
                break;
        }

        return typeOfVerbIntoDB;
    }

    private int getPrefixType() {
        String typeOfPrefix = trembareGroup.getSelectedToggle().getUserData().toString();
        int typeOfPrefixIntoDB;
        switch (typeOfPrefix) {
            case ModelsDAO.TREMBARE_PREFIX_VERB:
                typeOfPrefixIntoDB = ModelsDAO.TREMBARE_PREFIX_VERB_TO_DB;
                break;
            case ModelsDAO.UMTREMBARE_PREFIX_VERB_VERB:
                typeOfPrefixIntoDB = ModelsDAO.UMTREMBARE_PREFIX_VERB_VERB_TO_DB;
                break;
            default:
                typeOfPrefixIntoDB = ModelsDAO.TREMBARE_PREFIX_VERB_TO_DB;
                break;
        }

        return typeOfPrefixIntoDB;
    }

}
