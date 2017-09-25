package com.cards.shvedko.Controller;

import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
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
    public ComboBox pronomenAkk;
    @FXML
    public ComboBox pronomenDat;
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


    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("addCard.fxml");}

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

    private int getPerfectType(){
        String typeOfPerfect = perfectGroup.getSelectedToggle().getUserData().toString();
        int typeOfPerfectIntoDB;
        switch(typeOfPerfect){
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

    private int getVerbType(){
        String typeOfVerb = verbType.getSelectedToggle().getUserData().toString();
        int typeOfVerbIntoDB;
        switch(typeOfVerb){
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

    private int getPrefixType(){
        String typeOfPrefix = trembareGroup.getSelectedToggle().getUserData().toString();
        int typeOfPrefixIntoDB;
        switch(typeOfPrefix){
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
