package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardCategoriesDAO;
import com.cards.shvedko.ModelDAO.CardTypesDAO;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddVerb extends A_Controller {

    @FXML
    private ComboBox speechPart;
    @FXML
    private ComboBox topic;
    @FXML
    private TextField nativeValue;
    @FXML
    private Hyperlink nativeConjunctions;
    @FXML
    private ImageView nativeVoice;
    @FXML
    private TextArea nativeExample;
    @FXML
    private ImageView nativeExampleVoice;
    @FXML
    private TextArea foreignExample;
    @FXML
    private ImageView foreignExampleVoice;
    @FXML
    private TextField foreignValue;
    @FXML
    private Hyperlink foreignConjunctions;
    @FXML
    private ImageView foreignValueVoice;
    @FXML
    private Button previewButton;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        ObservableList<Object> dataSpeech = FXCollections.observableArrayList();
        dataSpeech = CardTypesDAO.setAllTypes(dataSpeech);
        speechPart.setItems(dataSpeech);

        ObservableList<Object> dataTopic = FXCollections.observableArrayList();
        dataTopic = CardCategoriesDAO.setAllTypes(dataTopic);
        topic.setItems(dataTopic);
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml");
    }

    public void handlePreviewButton(ActionEvent actionEvent) {

    }

    public void handleAddButton(ActionEvent actionEvent) {

        String name = nativeValue.getText();
        String value = foreignValue.getText();
        String nExample = nativeExample.getText();
        CardsDAO cardsDAO = new CardsDAO();
        cardsDAO.cards.setName(name);
        cardsDAO.cards.setValue(value);
        cardsDAO.cards.setExample(nExample);
        cardsDAO.cards.setCategoryId(1);
        cardsDAO.cards.setTypeId(1);
        cardsDAO.cards.setIsVisible(1);
        cardsDAO.save();

    }

}
