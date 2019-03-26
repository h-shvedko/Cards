package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.ProgressForm;
import com.cards.shvedko.MainApp;
import com.cards.shvedko.Model.*;
import com.cards.shvedko.ModelDAO.*;
import com.cathive.fonts.fontawesome.FontAwesomeIcon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import com.cathive.fonts.fontawesome.FontAwesomeIconView;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.net.URL;
import java.util.*;
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
    public static final String EDIT_NOUN_PAGE = "Учим немецкие слова! Редактирование существительного.";
    public static final String EDIT_VERB_PAGE = "Учим немецкие слова! Редактирование глагола.";
    public static final String EDIT_ADJECTIVE_PAGE = "Учим немецкие слова! Редактирование прилогательного.";
    public static final String EDIT_ADVERB_PAGE = "Учим немецкие слова! Редактирование наречия.";
    public static final String EDIT_NUMERAL_PAGE = "Учим немецкие слова! Редактирование числительного.";
    public static final String EDIT_PARTICIPLE_PAGE = "Учим немецкие слова! Редактирование причастия.";
    public static final String EDIT_PRONOUN_PAGE = "Учим немецкие слова! Редактирование местоимения.";
    public static final String EDIT_OTHER_PAGE = "Учим немецкие слова! Редактирование других частей речи.";
    public static final String ADD_DECK_PAGE_TITLE = "Учим немецкие слова! Создание новой колоды.";
    public static final String EDIT_DECK_PAGE_TITLE = "Учим немецкие слова! Редактирование колоды.";
    public static final String CHOOSE_DECKS_TITLE = "Учим немецкие слова! Выберите колоду";
    public static final String CHOOSE_CARDS_TITLE = "Учим немецкие слова!";
    public static final String LIST_OF_CARDS_TITLE = "Учим немецкие слова! Список карточек.";
    public static final String AUDIO_CAPTURING_TITLE = "Учим немецкие слова! Запись аудио.";
    public static final String EMPTY_DECK_TITLE = "Учим немецкие слова! Пустая колода.";
    public static final String IMPORT_FROM_CSV = "Учим немецкие слова! Импорт слов из CSV файла.";
    public static final String COPY_DB = "Учим немецкие слова! Создание резервной копии БД.";
    public static final String CLEAN_DB = "Учим немецкие слова! Очистка БД.";

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
    protected Label errorLevel;
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
    protected ComboBox<String> level;
    @FXML
    protected TextField nativeValue;
    @FXML
    protected Hyperlink nativeConjunctions;
    @FXML
    protected Button nativeVoice;
    @FXML
    protected TextArea nativeExample;
    @FXML
    protected Button nativeExampleVoice;
    @FXML
    protected TextArea foreignExample;
    @FXML
    protected Button foreignExampleVoice;
    @FXML
    protected TextField foreignValue;
    @FXML
    protected Hyperlink foreignConjunctions;
    @FXML
    protected Button foreignValueVoice;
    @FXML
    protected Button previewButton;
    @FXML
    protected Button addButton;
    @FXML
    protected Button cancelButton;
    @FXML
    public Label greeting;
    @FXML
    public Button delete;
    @FXML
    public Label startCards;
    @FXML
    protected Button cancel;
    @FXML
    protected ToggleButton allSpeechPart;
    @FXML
    protected ToggleButton favoriteOff;
    @FXML
    protected ToggleButton anchorOn;
    @FXML
    protected ToggleButton anchorOff;
    @FXML
    protected Button save;
    @FXML
    protected TextField nameDeck;
    @FXML
    protected ToggleButton allTopic;
    @FXML
    protected ToggleButton favoriteOn;

    protected final ToggleGroup groupAnchor = new ToggleGroup();
    protected final ToggleGroup groupFavorite = new ToggleGroup();

    //*********************TABLE VIEW CARDS DATA *****************************
    @FXML
    protected TableView<Cards> cardsTable;
    @FXML
    protected TableView<TmpCards> tmpCardsTable;
    @FXML
    protected TableView<DecksValues> deckValuesTable;
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
    protected TableColumn<Cards, Integer> active;
    @FXML
    protected TableColumn<Cards, String> tableForeignValue;

    @FXML
    public GridPane selectedInformation;
    @FXML
    public Label speechPartSelected;
    @FXML
    public Label topicSelected;
    @FXML
    public Label levelsSelected;

    public ObservableList<Integer> selectedSpeechPart;
    public ObservableList<Integer> selectedTopics;
    public ObservableList<Integer> selectedLevels;
    //************************************************************************

    //*********************PROGRESS BAR **************************************
    @FXML
    protected ProgressBar splash;
    private Stage splashStage;
    protected static ProgressForm pForm;

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
    public static Stage stage;
    protected static Object globalUserData;
    protected static Object globalCardSavedData;
    public static A_Models globalUserModel;
    public static Decks globalDeckData;
    public static Object globalAudioFileData;
    public static Map<String, String> globalCardData = new HashMap<String, String>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (speechPart != null) {
            ObservableList<String> dataSpeech = FXCollections.observableArrayList();
            try {
                dataSpeech = CardTypesDAO.setAllTypes(dataSpeech);
            } catch (Exception e) {
                crashAppeared(e.getMessage(), new ActionEvent());
            }
            speechPart.setItems(dataSpeech);

            speechPart.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (errorPartOfSpeech != null) {
                        errorPartOfSpeech.setText("");
                        errorPartOfSpeech.setVisible(false);
                    }
                    setTextToSelectedSpeechPart(newValue);
                    setIndexSelectedSpeechPart();
                }
            });
        }

        if (selectedInformation != null) {
            selectedInformation.setVisible(false);
        }


        if (topic != null) {
            ObservableList<String> dataTopic = FXCollections.observableArrayList();
            try {
                dataTopic = CardCategoriesDAO.setAllTypes(dataTopic);
            } catch (Exception e) {
                crashAppeared(e.getMessage(), new ActionEvent());
            }
            topic.setItems(dataTopic);

            topic.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (errorTopic != null) {
                        errorTopic.setText("");
                        errorTopic.setVisible(false);
                    }
                    setTextToSelectedTopics(newValue);
                    setIndexSelectedTopics();
                }
            });
        }

        if (level != null) {
            ObservableList<String> dataLevel = FXCollections.observableArrayList();
            try {
                dataLevel = CardLevelsDAO.setAllLevels(dataLevel);
            } catch (Exception e) {
                crashAppeared(e.getMessage(), new ActionEvent());
            }
            level.setItems(dataLevel);

            level.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (errorLevel != null) {
                        errorLevel.setText("");
                        errorLevel.setVisible(false);
                    }
                    setTextToSelectedLevels(newValue);
                    setIndexSelectedLevels();
                }
            });
        }

        if (nativeValue != null) {
            if (nativeVoice != null) {
                nativeVoice.setDisable(true);
            }
            nativeValue.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorNativeValue.setText("");
                    errorNativeValue.setVisible(false);

                    nativeValueOld = oldValue;
                    nativeValueNew = newValue;
                    if (!Objects.equals(newValue, "")) {
                        if (nativeVoice != null) {
                            nativeVoice.setDisable(false);
                        }
                    }
                }
            });
        }

        if (foreignValue != null) {
            if (foreignValueVoice != null) {
                foreignValueVoice.setDisable(true);
            }

            foreignValue.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    errorForeignValue.setText("");
                    errorForeignValue.setVisible(false);

                    foreignValueOld = oldValue;
                    foreignValueNew = newValue;
                    if (!Objects.equals(newValue, "")) {
                        if (foreignValueVoice != null) {
                            foreignValueVoice.setDisable(false);
                        }
                    }
                }
            });
        }

        if (foreignExample != null) {
            if (foreignExampleVoice != null) {
                foreignExampleVoice.setDisable(true);
            }

            foreignExample.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (!Objects.equals(newValue, "")) {
                        if (foreignExampleVoice != null) {
                            foreignExampleVoice.setDisable(false);
                        }
                    }
                }
            });
        }

        if (nativeExample != null) {
            if (nativeExampleVoice != null) {
                nativeExampleVoice.setDisable(true);
            }

            nativeExample.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue value, String oldValue, String newValue) {
                    if (!Objects.equals(newValue, "")) {
                        if (nativeExampleVoice != null) {
                            nativeExampleVoice.setDisable(false);
                        }
                    }
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

        if (A_Controller.globalUserData != null) {
            A_Controller.globalCardSavedData = A_Controller.globalUserData;
        }

        if (titleOfAddCard != null) {
            titleOfAddCard.setText("Редактировать карточку:");
        }
    }

    /**
     * @param newValue
     */
    protected void setTextToSelectedSpeechPart(String newValue) {
        if (speechPartSelected != null) {
            if (selectedInformation != null && !selectedInformation.isVisible()) {
                selectedInformation.setVisible(true);
            }

            String oldText = speechPartSelected.getText();
            String newText;

            newText = getTextForSelectedInfo(newValue, oldText);

            speechPartSelected.setText(newText);
            attachRemoveIcon(speechPartSelected, selectedSpeechPart);
            speechPartSelected.setContentDisplay(ContentDisplay.RIGHT);
        }
    }

    /**
     * @param label
     */
    private void attachRemoveIcon(Label label, ObservableList<?> list) {
        FontAwesomeIconView fontAwesome = new FontAwesomeIconView();
        fontAwesome.setIcon(FontAwesomeIcon.ICON_TRASH);
        label.setGraphic(fontAwesome);

        fontAwesome.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(list != null){
                    list.clear();
                }
                label.setText("");
                label.setGraphic(null);

            }
        });
    }

    /**
     *
     */
    protected void setIndexSelectedSpeechPart() {

        if (selectedSpeechPart == null) {
            selectedSpeechPart = FXCollections.observableArrayList();
        }

        Integer selectedIndex = Integer.parseInt(String.valueOf(speechPart.getSelectionModel().getSelectedIndex()));

        if (!selectedSpeechPart.isEmpty() && !selectedSpeechPart.contains(selectedIndex)) {
            selectedSpeechPart.add(selectedIndex);
        }
    }

    /**
     * @param newValue
     */
    protected void setTextToSelectedTopics(String newValue) {
        if (topicSelected != null) {
            if (selectedInformation != null && !selectedInformation.isVisible()) {
                selectedInformation.setVisible(true);
            }

            String oldText = topicSelected.getText();
            String newText;

            newText = getTextForSelectedInfo(newValue, oldText);

            topicSelected.setText(newText);
            attachRemoveIcon(topicSelected, selectedTopics);
            topicSelected.setContentDisplay(ContentDisplay.RIGHT);
        }
    }

    /**
     *
     */
    protected void setIndexSelectedTopics() {
        if (selectedTopics == null) {
            selectedTopics = FXCollections.observableArrayList();
        }

        Integer selectedIndex = Integer.parseInt(String.valueOf(topic.getSelectionModel().getSelectedIndex()));

        if (!selectedTopics.contains(selectedIndex)) {
            selectedTopics.add(selectedIndex);
        }
    }

    /**
     * @param newValue
     */
    protected void setTextToSelectedLevels(String newValue) {
        if (levelsSelected != null) {
            if (selectedInformation != null && !selectedInformation.isVisible()) {
                selectedInformation.setVisible(true);
            }

            String oldText = levelsSelected.getText();
            String newText;
            if (!newValue.equals(ModelsDAO.ALL_LEVELS)) {
                newText = getTextForSelectedInfo(newValue, oldText);
            } else {
                newText = newValue;
            }
            levelsSelected.setText(newText);

            attachRemoveIcon(levelsSelected, selectedLevels);
            levelsSelected.setContentDisplay(ContentDisplay.RIGHT);

        }
    }

    protected String getTextForSelectedInfo(String newValue, String oldText) {
        String newText;

        if (newValue.equals(ModelsDAO.ALL_PART_OF_SPEECH) || oldText.equals(ModelsDAO.ALL_PART_OF_SPEECH)) {
            return newValue;
        }

        if (!oldText.contains(newValue)) {
            if (!oldText.equals("")) {
                newText = oldText + ", " + newValue;
            } else {
                newText = newValue;
            }
        } else {
            newText = oldText;
        }
        return newText;
    }

    /**
     *
     */
    private void setIndexSelectedLevels() {
        if (selectedLevels == null) {
            selectedLevels = FXCollections.observableArrayList();
        }

        Integer selectedIndex = Integer.parseInt(String.valueOf(level.getSelectionModel().getSelectedIndex()));

        if (selectedIndex == 0) {
            selectedLevels.clear();
            selectedLevels.add(selectedIndex);
        } else {
            if (!selectedLevels.contains(selectedIndex)) {
                selectedLevels.add(selectedIndex);
            }
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
            if (closeAdditionalStage()) {
                MainApp.stage.setOpacity(1);
                return;
            }
            globalUserData = userData;
            Parent page = FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = MainApp.stage.getScene();
            if (scene == null) {
                scene = new Scene(page);
                MainApp.stage.setScene(scene);
            } else {
                MainApp.stage.getScene().setRoot(page);
            }
            MainApp.stage.setOpacity(1);
            MainApp.stage.setTitle(title);
            MainApp.stage.sizeToScene();
            MainApp.stage.setResizable(false);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            MainApp.stage.setX((primScreenBounds.getWidth() - MainApp.stage.getWidth()) / 2);
            MainApp.stage.setY((primScreenBounds.getHeight() - MainApp.stage.getHeight()) / 2);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected boolean closeAdditionalStage() {

        if (splashStage != null) {
            splashStage.close();
        }

        if (A_Controller.stage != null) {
            A_Controller.stage.close();
            A_Controller.stage = null;
            MainApp.stage.setOpacity(1);
            return true;
        }

        return false;
    }

    public void openOneMoreWindow(String fxml, String title, Object userData, ActionEvent event) {
        try {
            if (closeAdditionalStage()) {
                return;
            }
            globalUserData = userData;
            A_Controller.stage = new Stage();
            A_Controller.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    if (A_Controller.stage != null) {
                        A_Controller.stage.close();
                        A_Controller.stage = null;
                        MainApp.stage.setOpacity(1);
                    }
                }
            });
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
            A_Controller.stage.setScene(new Scene(root));
            A_Controller.stage.setTitle(title);
            A_Controller.stage.sizeToScene();
            A_Controller.stage.setResizable(false);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            A_Controller.stage.initModality(Modality.WINDOW_MODAL);
            A_Controller.stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            A_Controller.stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeWindow(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        if(MainApp.stage != null){
            MainApp.stage.setOpacity(1);
        }
        stage.close();
        closeAdditionalStage();
    }

    /**
     * @param message
     */
    public void crashAppeared(String message, ActionEvent event) {
        globalUserData = message;
        System.out.println(message);

        try {
            closeAdditionalStage();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalError.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Error!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param modelsDAO
     */
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

    protected void showSuccessStayOnPage(ActionEvent event) {
        try {
            closeAdditionalStage();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalSuccessStayOnThePage.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showSuccessImport(ActionEvent event) {
        try {
            closeAdditionalStage();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalQuestionImport.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param actionEvent
     * @param task
     */
    protected void showSplashProgress(ActionEvent actionEvent, final Task<?> task) {
        try {
//            closeAdditionalStage();

            pForm = new ProgressForm();

            ((Node) actionEvent.getSource()).getScene().getWindow().setOpacity(0.7);
            pForm.activateProgressBar(task);

            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    pForm.getDialogStage().close();
                    showSuccessStayOnPage(actionEvent);
                }
            });

            pForm.getDialogStage().show();

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param actionEvent
     * @param task
     */
    protected void showSplashProgressFoImport(ActionEvent actionEvent, final Task<?> task) {
        try {
            pForm = new ProgressForm();

            ((Node) actionEvent.getSource()).getScene().getWindow().setOpacity(0.7);
            pForm.activateProgressBar(task);

            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    pForm.getDialogStage().close();
                    showSuccessImport(actionEvent);
                }
            });

            pForm.getDialogStage().show();

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param task
     */
    public static void showLoadingSplashProgress(final Task<?> task) {
        try {
            pForm = new ProgressForm();

            pForm.activateProgressBar(task);

            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    pForm.getDialogStage().close();
                }
            });

            pForm.getDialogStage().show();

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    protected void showSplashProgress(ActionEvent event) {
//        try {
//            closeAdditionalStage();
//            splashStage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("splashProgress.fxml"), null, new JavaFXBuilderFactory());
//            splashStage.setScene(new Scene(root));
//            splashStage.setTitle("In progress...");
//            splashStage.initStyle(StageStyle.UNDECORATED);
//            splashStage.initModality(Modality.WINDOW_MODAL);
//            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.3);
//            splashStage.initOwner(((Node) event.getSource()).getScene().getWindow());
//            splashStage.show();
//        } catch (Exception ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    protected void closeSplashProgress(ActionEvent event) {
        try {
            closeAdditionalStage();
            splashStage.close();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param event
     */
    protected void showSuccessAfterDeleteStayOnPage(ActionEvent event) {
        try {
            closeAdditionalStage();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalSuccessAfterDeletionStayOnThePage.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showSuccess(ActionEvent event) {
        try {
            closeAdditionalStage();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalSuccess.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showSuccessEditCard(ActionEvent event) {
        try {
            closeAdditionalStage();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalSuccessEditCard.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showSuccessProfile(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalSuccessProfile.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
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
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalSuccessRegistration.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Registration is success!");
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showQuiestion(ActionEvent event, String text) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalQuestion.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Warning!");
            stage.setUserData(text);
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void showRemoveDeckQuestion(ActionEvent event, String text) {
        try {
            answer = false;
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Modals/modalRemoveDeckQuestion.fxml"), null, new JavaFXBuilderFactory());
            stage.setScene(new Scene(root));
            stage.setTitle("Warning!");
            stage.setUserData(text);
            stage.initModality(Modality.WINDOW_MODAL);
            ((Node) event.getSource()).getScene().getWindow().setOpacity(0.7);
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
            crashAppeared(e.getMessage(), actionEvent);
        }

        A_Models userObject = globalUserModel;

        CardTypesDAO cardTypesDAO = new CardTypesDAO();
        A_Models typeObject = null;
        try {
            typeObject = cardTypesDAO.select("where id=" + type);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }

        CardLevelsDAO cardLevels = new CardLevelsDAO();
        A_Models levelObject = null;
        try {
            levelObject = cardLevels.select("where id=" + type);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
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

        if (levelObject != null) {
            cardsDAO.cards.setLevels((CardLevels) levelObject);
        }

        if (A_Controller.globalCardData != null) {
            Iterator it = A_Controller.globalCardData.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (pair.getKey().equals("nativeVoice")) {
                    cardsDAO.cards.setNameVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("nativeExampleVoice")) {
                    cardsDAO.cards.setExampleVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("foreignExampleVoice")) {
                    cardsDAO.cards.setForeignExampleVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("foreignValueVoice")) {
                    cardsDAO.cards.setForeignNameVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("foreignValueVoicePlural")) {
                    cardsDAO.cards.setForeignValuePluralVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("foreignValueVoicePreteriturm")) {
                    cardsDAO.cards.setForeignValuePreteriturmVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("foreignValueVoicePresence")) {
                    cardsDAO.cards.setForeignValuePresenceVoice((String) pair.getValue());
                }
                if (pair.getKey().equals("foreignValueVoicePerfect")) {
                    cardsDAO.cards.setForeignValuePerfectVoice((String) pair.getValue());
                }
                it.remove();
            }
        }

        cardsDAO.cards.setIsVisible(1);

        return cardsDAO;

    }

    public CardsDAO handleEditButton(ActionEvent actionEvent) {

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
            crashAppeared(e.getMessage(), actionEvent);
        }

        A_Models userObject = globalUserModel;

        CardTypesDAO cardTypesDAO = new CardTypesDAO();
        A_Models typeObject = null;
        try {
            typeObject = cardTypesDAO.select("where id=" + type);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }

        CardLevelsDAO cardLevelsDAO = new CardLevelsDAO();
        A_Models levelObject = null;
        try {
            levelObject = cardLevelsDAO.select("where id=" + type);
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }

        int cardId = ((Cards) A_Controller.globalCardSavedData).getId();
        CardsDAO cardsDAO = new CardsDAO(cardId);
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

        if (levelObject != null) {
            cardsDAO.cards.setLevels((CardLevels) levelObject);
        }

        if (A_Controller.globalCardData != null) {
            Iterator it = A_Controller.globalCardData.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (!pair.getValue().equals("")) {
                    if (pair.getKey().equals("nativeVoice")) {
                        cardsDAO.cards.setNameVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("nativeExampleVoice")) {
                        cardsDAO.cards.setExampleVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("foreignExampleVoice")) {
                        cardsDAO.cards.setForeignExampleVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("foreignValueVoice")) {
                        cardsDAO.cards.setForeignNameVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("foreignValueVoicePlural")) {
                        cardsDAO.cards.setForeignValuePluralVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("foreignValueVoicePreteriturm")) {
                        cardsDAO.cards.setForeignValuePreteriturmVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("foreignValueVoicePresence")) {
                        cardsDAO.cards.setForeignValuePresenceVoice((String) pair.getValue());
                    }
                    if (pair.getKey().equals("foreignValueVoicePerfect")) {
                        cardsDAO.cards.setForeignValuePerfectVoice((String) pair.getValue());
                    }
                }
                it.remove();
            }
        }

        cardsDAO.cards.setIsVisible(1);

        return cardsDAO;

    }


    public void handleEnterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            handleSubmitButtonAction();
        }

        if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
            handleCancelButtonAction();
        }
    }

    protected abstract void handleCancelButtonAction();

    protected abstract void handleSubmitButtonAction();

    public void handleAudioCaption(ActionEvent actionEvent) {
        openOneMoreWindow("audioCapturing.fxml", AUDIO_CAPTURING_TITLE, ((Button) actionEvent.getSource()).getId(), actionEvent);
    }

    public static void updateGlobalDeckData() throws Exception {

        Decks deck = globalDeckData;

        DecksValuesDAO decksValuesDAO = new DecksValuesDAO();
        List decksValues;
        try {
            decksValues = decksValuesDAO.selectAllBy("where deck_id=" + deck.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        if (decksValues != null) {
            globalDeckData.setDecksValues(decksValues);
        }
    }
}
