package com.cards.shvedko.Controller;

import com.cards.shvedko.MainApp;
import com.cards.shvedko.ModelDAO.CardCategoriesDAO;
import com.cards.shvedko.ModelDAO.CardTypesDAO;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class A_Controller implements Initializable {

    protected static String errorStringMsg;
    @FXML
    protected Label errorNativeValue;
    @FXML
    protected Label errorTopic;
    @FXML
    protected Label errorPartOfSpeech;
    @FXML
    protected Label errorForeignValue;
    @FXML
    protected ComboBox<String> speechPart;
    @FXML
    protected ComboBox<String> topic;
    @FXML
    protected TextField nativeValue;
    @FXML
    protected Hyperlink nativeConjunctions;
    @FXML
    protected ImageView nativeVoice;
    @FXML
    protected TextArea nativeExample;
    @FXML
    protected ImageView nativeExampleVoice;
    @FXML
    protected TextArea foreignExample;
    @FXML
    protected ImageView foreignExampleVoice;
    @FXML
    protected TextField foreignValue;
    @FXML
    protected Hyperlink foreignConjunctions;
    @FXML
    protected ImageView foreignValueVoice;
    @FXML
    protected Button previewButton;
    @FXML
    protected Button addButton;
    @FXML
    protected Button cancelButton;

    public static String errorMsg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> dataSpeech = FXCollections.observableArrayList();
        dataSpeech = CardTypesDAO.setAllTypes(dataSpeech);
        speechPart.setItems(dataSpeech);

        ObservableList<String> dataTopic = FXCollections.observableArrayList();
        dataTopic = CardCategoriesDAO.setAllTypes(dataTopic);
        topic.setItems(dataTopic);

        nativeConjunctions.setVisible(false);
        foreignConjunctions.setVisible(false);
        errorNativeValue.setVisible(false);
        errorForeignValue.setVisible(false);
        errorPartOfSpeech.setVisible(false);
        errorTopic.setVisible(false);

        speechPart.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue value, String oldValue, String newValue) {
                errorPartOfSpeech.setText("");
                errorPartOfSpeech.setVisible(false);
            }
        });

        topic.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue value, String oldValue, String newValue) {
                errorTopic.setText("");
                errorTopic.setVisible(false);
            }
        });

        nativeValue.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue value, String oldValue, String newValue) {
                errorNativeValue.setText("");
                errorNativeValue.setVisible(false);
            }
        });

        foreignValue.textProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue value, String oldValue, String newValue) {
                errorForeignValue.setText("");
                errorForeignValue.setVisible(false);
            }
        });
    }

    public void goToPage(String fxml) {
        try {
            Parent page = FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = MainApp.stage.getScene();
            if (scene == null) {
                scene = new Scene(page);
                MainApp.stage.setScene(scene);
            } else {
                MainApp.stage.getScene().setRoot(page);
            }
            MainApp.stage.sizeToScene();
            MainApp.stage.setResizable(false);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeWindow(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void crashAppeared(String message){
        MainPageController.errorStringMsg = message;
        goToPage("mainPage.fxml");
    }

    protected void showErrors(ModelsDAO modelsDAO) {
        if(modelsDAO.errorSet != null){
            for (ConstraintViolation violation : modelsDAO.errorSet) {
                Path wrongAttribute = violation.getPropertyPath();
                String message = violation.getMessage();
                if(wrongAttribute.iterator().hasNext()){
                    for (Iterator<Path.Node> it = wrongAttribute.iterator(); it.hasNext(); ) {
                        Path.Node attribute = it.next();
                        String nameOfAttrib = attribute.getName();
                        setErrorMessages(nameOfAttrib, message);
                    }
                }
            }
        }
    }

    protected void setErrorMessages(String nameOfAttrib, String message) {
        if(nameOfAttrib != null){
            switch (nameOfAttrib){
                case "name":
                    errorNativeValue.setText(message);
                    errorNativeValue.setVisible(true);
                    break;
                case "value":
                    errorForeignValue.setText(message);
                    errorForeignValue.setVisible(true);
                    break;
                case "typeId":
                    errorPartOfSpeech.setText(message);
                    errorPartOfSpeech.setVisible(true);
                    break;
                case "categoryId":
                    errorTopic.setText(message);
                    errorTopic.setVisible(true);
                    break;
            }
        }
    }

    protected void showSuccess() {

    }
}
