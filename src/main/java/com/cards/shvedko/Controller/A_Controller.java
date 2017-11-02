package com.cards.shvedko.Controller;

import com.cards.shvedko.MainApp;
import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
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

    /**
     * Titles for pages
     */
    public static final String MAIN_PAGE_TITLE = "Учим немецкие слова! Главная страница";
    public static final String LOGIN_PAGE_TITLE = "Учим немецкие слова! Авторизация.";
    public static final String REGISTRATION_PAGE_TITLE = "Учим немецкие слова! Регистрация.";
    public static final String PROFILE_PAGE_TITLE = "Учим немецкие слова! Профиль.";
    public static final String SETTINGS_PAGE_TITLE = "Учим немецкие слова! Настройки.";
    public static final String CHOOSE_TYPE_OF_CARD_PAGE_TITLE = "Учим немецкие слова! Выберите часть речи.";
    public static final String ADD_NOUN_PAGE = "Учим немецкие слова! Создание существительного.";
    public static final String ADD_VERB_PAGE = "Учим немецкие слова! Создание глагола.";
    public static final String ADD_ADJECTIVE_PAGE = "Учим немецкие слова! Создание прилогательного.";
    public static final String ADD_ADVERB_PAGE = "Учим немецкие слова! Создание наречия.";
    public static final String ADD_NUMERAL_PAGE = "Учим немецкие слова! Создание числительного.";
    public static final String ADD_PARTICIPLE_PAGE = "Учим немецкие слова! Создание причастия.";
    public static final String ADD_PRONOUN_PAGE = "Учим немецкие слова! Создание местоимения.";
    public static final String ADD_OTHER_PAGE = "Учим немецкие слова! Создание других частей речи.";
    public static final String ADD_DECK_PAGE_TITLE = "Учим немецкие слова! Создание новой колоды.";
    public static final String EDIT_DECK_PAGE_TITLE = "Учим немецкие слова! Редактирование колоды.";
    public static final String CHOOSE_DECKS_TITLE = "Учим немецкие слова! Выберите колоду";
    public static final String CHOOSE_CARDS_TITLE = "Учим немецкие слова!";
    public static final String LIST_OF_CARDS_TITLE = "Учим немецкие слова! Список карточек.";

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
    protected Label errorDecks;
    @FXML
    protected Label errorForeignValue;
    @FXML
    protected ComboBox<String> speechPart;
    @FXML
    protected ComboBox<String> decksCombo;
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
    @FXML
    public Label greeting;

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

    /**
     * Global data
     */
    protected static Object globalUserData;
    public static A_Models globalUserModel;
    public static A_Models globalDeckData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (speechPart != null) {
            ObservableList<String> dataSpeech = FXCollections.observableArrayList();
            try {
                dataSpeech = CardTypesDAO.setAllTypes(dataSpeech);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
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
            try {
                dataTopic = CardCategoriesDAO.setAllTypes(dataTopic);
            } catch (Exception e) {
                crashAppeared(e.getMessage());
            }
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

    public boolean compareNativeValue() {
        if (nativeValueNew != null && nativeValueNew.equals(nativeValueOld)) {
            return true;
        }

        return false;
    }

    public boolean compareForeignValue() {
        if (foreignValueNew != null && foreignValueNew.equals(foreignValueOld)) {
            return true;
        }

        return false;
    }

    public void goToPage(String fxml, String title, Object userData) {
        try {
            globalUserData = userData;
            Parent page = FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = MainApp.stage.getScene();
            if (scene == null) {
                scene = new Scene(page);
                MainApp.stage.setScene(scene);
            } else {
                MainApp.stage.getScene().setRoot(page);
            }
            MainApp.stage.setTitle(title);
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
        goToPage("mainPage.fxml", "Main page", "");
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

    protected void showSuccessProfile(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modalSuccessProfile.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showSuccessRegistration(ActionEvent event, Users user) {
        try {
            globalUserData = user;
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modalSuccessRegistration.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Registration is success!");
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

    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, "");
    }

    public void handlePreviewButton(ActionEvent actionEvent) {
    }

    public CardsDAO handleAddButton(ActionEvent actionEvent) {

        String name = nativeValue.getText();
        String value = foreignValue.getText();
        String nExample = nativeExample.getText();
        String fExample = foreignExample.getText();
        int type = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex())) + 1;
        int category = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex())) + 1;

        CardCategoriesDAO cardCategoriesDAO = new CardCategoriesDAO();
        A_Models categoryObject = null;
        try {
            categoryObject = cardCategoriesDAO.select("where id=" + category);
        } catch (Exception e) {
            crashAppeared(e.getMessage());
        }

        A_Models userObject = globalUserModel;

        CardTypesDAO cardTypesDAO = new CardTypesDAO();
        A_Models typeObject = null;
        try {
            typeObject = cardTypesDAO.select("where id=" + type);
        } catch (Exception e) {
            crashAppeared(e.getMessage());
        }

        CardsDAO cardsDAO = new CardsDAO();
        cardsDAO.cards.setName(name);
        cardsDAO.cards.setForeignName(value);
        cardsDAO.cards.setExample(nExample);
        cardsDAO.cards.setForeignExample(fExample);

        if (categoryObject != null) {
            cardsDAO.cards.setCategory((CardCategories) categoryObject);
        }

        if (typeObject != null) {
            cardsDAO.cards.setType((CardTypes) typeObject);
        }

        if (userObject != null) {
            cardsDAO.cards.setUser((Users) userObject);
        }

        cardsDAO.cards.setIsVisible(1);

        return cardsDAO;

    }
}
