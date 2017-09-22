package com.cards.shvedko.Controller;

import com.cards.shvedko.MainApp;
import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardCategoriesDAO;
import com.cards.shvedko.ModelDAO.CardTypesDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
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
    protected GridPane grid;
    @FXML
    protected Button close;
    @FXML
    protected Label errorMessage;
    @FXML
    protected Label titleOfAddCard;
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

    //*********************TABLE VIEW CARDS DATA *****************************
    @FXML
    protected TableView<Cards> cardsTable;
    @FXML
    protected TableColumn<Cards, String> tableTopic;
    @FXML
    protected TableColumn<Cards, String> tableSpeechPart;
    @FXML
    protected TableColumn<Cards, String> tableNativeValue;
    @FXML
    protected TableColumn<Cards, String> tableNativeExample;
    @FXML
    protected TableColumn<Cards, String> tableForeignExample;
    @FXML
    protected TableColumn<Cards, String> tableForeignValue;
    //************************************************************************

    public static String errorMsg;
    protected String nativeValueOld;
    protected String nativeValueNew;
    protected String foreignValueOld;
    protected String foreignValueNew;
    protected boolean answer = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (speechPart != null) {
            ObservableList<String> dataSpeech = FXCollections.observableArrayList();
            dataSpeech = CardTypesDAO.setAllTypes(dataSpeech);
            speechPart.setItems(dataSpeech);

            speechPart.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorPartOfSpeech.setText("");
                    errorPartOfSpeech.setVisible(false);
                }
            });
        }


        if (topic != null) {
            ObservableList<String> dataTopic = FXCollections.observableArrayList();
            dataTopic = CardCategoriesDAO.setAllTypes(dataTopic);
            topic.setItems(dataTopic);

            topic.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorTopic.setText("");
                    errorTopic.setVisible(false);
                }
            });
        }

        if (nativeValue != null) {
            nativeValue.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorNativeValue.setText("");
                    errorNativeValue.setVisible(false);

                    nativeValueOld = oldValue;
                    nativeValueNew = newValue;
                }
            });
        }
        if (foreignValue != null) {
            foreignValue.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorForeignValue.setText("");
                    errorForeignValue.setVisible(false);

                    foreignValueOld = oldValue;
                    foreignValueNew = newValue;
                }
            });
        }
        if (nativeConjunctions != null) {
            nativeConjunctions.setVisible(false);

        }
        if (foreignConjunctions != null) {
            foreignConjunctions.setVisible(false);

        }
        if (errorNativeValue != null) {
            errorNativeValue.setVisible(false);

        }
        if (errorForeignValue != null) {
            errorForeignValue.setVisible(false);

        }
        if (errorPartOfSpeech != null) {
            errorPartOfSpeech.setVisible(false);

        }
        if (errorTopic != null) {
            errorTopic.setVisible(false);

        }
    }

    public boolean compareNativeValue(){
        if(nativeValueNew != null && nativeValueNew.equals(nativeValueOld)){
            return true;
        }

        return false;
    }

    public boolean compareForeignValue(){
        if(foreignValueNew != null && foreignValueNew.equals(foreignValueOld)){
            return true;
        }

        return false;
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

    public void crashAppeared(String message) {
        MainPageController.errorStringMsg = message;
        System.out.println(message);
        goToPage("mainPage.fxml");
    }

    protected void showErrors(ModelsDAO modelsDAO) {
        if (modelsDAO.errorSet != null) {
            for (ConstraintViolation violation : modelsDAO.errorSet) {
                Path wrongAttribute = violation.getPropertyPath();
                String message = violation.getMessage();
                if (wrongAttribute.iterator().hasNext()) {
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
        if (nameOfAttrib != null) {
            switch (nameOfAttrib) {
                case "name":
                    errorNativeValue.setText(message);
                    errorNativeValue.setVisible(true);
                    break;
                case "value":
                    errorForeignValue.setText(message);
                    errorForeignValue.setVisible(true);
                    break;
                case "type":
                    errorPartOfSpeech.setText(message);
                    errorPartOfSpeech.setVisible(true);
                    break;
                case "category":
                    errorTopic.setText(message);
                    errorTopic.setVisible(true);
                    break;
            }
        }
    }

    protected void showSuccess(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modalSuccess.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showQuiestion(ActionEvent event, String text) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modalQuestion.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Warning!");
            stage.setUserData(text);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void clear() {
        nativeValue.setText("");
        foreignValue.setText("");
        foreignExample.setText("");
        nativeExample.setText("");
        nativeConjunctions.setVisible(false);
        nativeConjunctions.setText("");
        foreignConjunctions.setText("");
        foreignConjunctions.setVisible(false);
    }

    public void handleCancelButton(ActionEvent actionEvent){this.goToPage("mainPage.fxml");}

    public void handlePreviewButton(ActionEvent actionEvent){}

    public void handleAddButton(ActionEvent actionEvent){}
}
