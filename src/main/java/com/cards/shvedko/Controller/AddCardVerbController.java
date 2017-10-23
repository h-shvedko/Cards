package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.Model.CardsPrepositionAkkusativ;
import com.cards.shvedko.Model.CardsPrepositionDativ;
import com.cards.shvedko.ModelDAO.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCardVerbController extends A_Controller {

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
        titleOfAddCard.setText("Add new verb:");
        speechPart.setValue("Verb");
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
            dataAkk = CardsPrepositionAkkusativDAO.setAllPrepositions(dataAkk);
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
            dataDat = CardsPrepositionDativDAO.setAllPrepositions(dataDat);
            pronomenDat.setItems(dataDat);

            pronomenDat.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorTopic.setText("");
                    errorTopic.setVisible(false);
                }
            });
        }
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("addCard.fxml", A_Controller.CHOOSE_TYPE_OF_CARD_PAGE_TITLE, "");
    }

    @Override
    public void handlePreviewButton(ActionEvent actionEvent) {

    }

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Do really want to save this card? You haven't changed anything in native and foreign words!");
        }

        CardsDAO cardsDAO = null;
        if (answer) {
            cardsDAO = super.handleAddButton(actionEvent);

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
                e.printStackTrace();
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
                e.printStackTrace();
            }
            if (akkObject != null) {
                cardsDAO.cards.setPrepositionAkk((CardsPrepositionAkkusativ) akkObject);
            }

            //setting genetive preposition
            String genPronomenValue = pronomenGen.getText();
            cardsDAO.cards.setPrepositionGen(genPronomenValue);

            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    cardsDAO.save();
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
