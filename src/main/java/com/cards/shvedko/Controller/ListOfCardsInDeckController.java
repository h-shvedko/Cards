package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.Model.Decks;
import com.cards.shvedko.Model.DecksValues;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListOfCardsInDeckController extends A_Controller {

    @FXML
    public TableColumn<Cards, String> tableSounds;
    @FXML
    public TableColumn<Cards, String> tableIsAnchor;
    @FXML
    public TableColumn<Cards, String> tableIsLearnt;

    private ObservableList<Cards> decksValues = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        linkToColumns();
        makeTitleOfColumns();

        Decks deck = globalDeckData;

        List<DecksValues> decksValuesFromDeck = deck.getDecksValues();

        if (decksValuesFromDeck.size() > 0) {
            for (Object deckValue : decksValuesFromDeck) {
                this.decksValues.add(((DecksValues) deckValue).getCards());
            }
        }

        cardsTable.setItems(getDecksValues());

        cardsTable.setRowFactory(new Callback<TableView<Cards>, TableRow<Cards>>() {
            @Override
            public TableRow<Cards> call(TableView<Cards> tv) {
                final TableRow<Cards> row = new TableRow<>();
                row.itemProperty().addListener(new ChangeListener<Cards>() {
                    @Override
                    public void changed(ObservableValue<? extends Cards> obs, Cards oldItem, Cards newItem) {
                        if (newItem != null) {
                            Boolean isReady = checkIfCardIsReadyInDeck(newItem);
                            if (isReady) {
                                row.setStyle("-fx-background-color: mediumseagreen");
                                Tooltip tooltip = new Tooltip("Эта карточка выучена");
                                tooltip.setStyle("-fx-font-size: 16px;");
                                row.setTooltip(tooltip);
                            } else {
                                row.setStyle("");
                                Tooltip tooltip = new Tooltip("Эта карточка еще не выучена");
                                tooltip.setStyle("-fx-font-size: 16px;");
                                row.setTooltip(tooltip);
                            }
                        }
                    }
                });

                row.hoverProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        Cards cardsValue = row.getItem();
                        if (row.isHover()) {
                            if (cardsValue != null) {
                                row.setStyle("-fx-background-color: cornflowerblue");
                            }
                        } else {
                            if (cardsValue != null) {
                                Boolean isReady = checkIfCardIsReadyInDeck(cardsValue);
                                if (isReady) {
                                    row.setStyle("-fx-background-color: mediumseagreen");
                                    Tooltip tooltip = new Tooltip("Эта карточка выучена");
                                    tooltip.setStyle("-fx-font-size: 16px;");
                                    row.setTooltip(tooltip);
                                } else {
                                    row.setStyle("");
                                    Tooltip tooltip = new Tooltip("Эта карточка еще не выучена");
                                    tooltip.setStyle("-fx-font-size: 16px;");
                                    row.setTooltip(tooltip);
                                }
                            }
                        }
                    }
                });
                return row;
            }
        });

        initializeContentMenu();

    }

    private Boolean checkIfCardIsReadyInDeck(Cards newItem) {
        Boolean res = false;

        Decks deck = globalDeckData;

        List<DecksValues> decksValuesFromDeck = deck.getDecksValues();

        if (decksValuesFromDeck.size() > 0) {
            for (Object deckValue : decksValuesFromDeck) {
                if (((DecksValues) deckValue).getCards().getId() == newItem.getId()) {
                    if (((DecksValues) deckValue).getIsReady() == 1) {
                        res = true;
                    }
                }
            }
        }

        return res;
    }

    // Create ContextMenu
    private void initializeContentMenu() {

        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Редактировать");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                switch (selectedItem.getType().getName()) {
                    case ModelsDAO.NOUN:
                        goToPage("editCardNoun.fxml", A_Controller.EDIT_NOUN_PAGE, selectedItem);
                        break;
                    case ModelsDAO.VERB:
                        goToPage("editCardVerb.fxml", A_Controller.EDIT_VERB_PAGE, selectedItem);
                        break;
                    case ModelsDAO.ADJECTIVE:
                        goToPage("editCardAdjective.fxml", A_Controller.EDIT_ADJECTIVE_PAGE, selectedItem);
                        break;
                    case ModelsDAO.ADVERB:
                        goToPage("editCardAdverb.fxml", A_Controller.EDIT_ADVERB_PAGE, selectedItem);
                        break;
                    case ModelsDAO.NUMERAL:
                        goToPage("editCardNumeral.fxml", A_Controller.EDIT_NUMERAL_PAGE, selectedItem);
                        break;
                    case ModelsDAO.PARTICIPLE:
                        goToPage("editCardParticiple.fxml", A_Controller.EDIT_PARTICIPLE_PAGE, selectedItem);
                        break;
                    case ModelsDAO.PRONOUN:
                        goToPage("editCardPronoun.fxml", A_Controller.EDIT_PRONOUN_PAGE, selectedItem);
                        break;
                    default:
                        goToPage("editCardOther.fxml", A_Controller.EDIT_OTHER_PAGE, selectedItem);
                        break;
                }
            }
        });


        MenuItem item2 = new MenuItem("Учить заново/Готово");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                goToPage("modalRemoveIsLearnt.fxml", "Учить заново/Готово", selectedItem);
            }
        });

        MenuItem item3 = new MenuItem("Сделать первой/Убрать из первой");
        item3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                goToPage("modalRemoveAnchor.fxml", "Сделать первой/Убрать из первой", selectedItem);
            }
        });

        contextMenu.getItems().addAll(item1, item2, item3);

        cardsTable.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(cardsTable, event.getScreenX(), event.getScreenY());
            }
        });
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    private void makeTitleOfColumns() {
        tableTopic.setText("Тема");
        tableSpeechPart.setText("Часть речи");
        tableNativeValue.setText("Слово (рус)");
        tableForeignValue.setText("Перевод (нем)");
        tableSounds.setText("Озвучено");
        tableIsAnchor.setText("Первая");
    }

    private void linkToColumns() {
        tableTopic.setCellValueFactory(new PropertyValueFactory<Cards, String>("type"));
        tableTopic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(p.getValue().getCategory().getName());
            }
        });
        tableSpeechPart.setCellValueFactory(new PropertyValueFactory<Cards, String>("category"));
        tableSpeechPart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(p.getValue().getType().getName());
            }
        });
        tableNativeValue.setCellValueFactory(new PropertyValueFactory<Cards, String>("name"));
        tableForeignValue.setCellValueFactory(new PropertyValueFactory<Cards, String>("foreignName"));
        tableSounds.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(getNumberOfSounds(p.getValue()));
            }
        });

        tableIsAnchor.setCellValueFactory(new PropertyValueFactory<Cards, String>("isAnchor"));
        tableIsAnchor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(getIfAnchor(p.getValue()));
            }
        });
    }

    private String getIfAnchor(Cards value) {
        String res = "";

        if (value != null) {
            Decks deck = globalDeckData;

            List<DecksValues> decksValuesFromDeck = deck.getDecksValues();

            if (decksValuesFromDeck.size() > 0) {
                for (Object deckValue : decksValuesFromDeck) {
                    if (value.getId() == ((DecksValues) deckValue).getCards().getId() && ((DecksValues) deckValue).getIsAnchor() == 1
                            && value.getIsVisible() != 0) {
                        res = "Да";
                    }
                }
            }
        }

        return res;
    }

    private String getNumberOfSounds(Cards card) {
        int cnt = 0;

        if (card.getNameVoice() != null && !card.getNameVoice().equals("")) {
            ++cnt;
        }
        if (card.getExampleVoice() != null && !card.getExampleVoice().equals("")) {
            ++cnt;
        }
        if (card.getForeignNameVoice() != null && !card.getForeignNameVoice().equals("")) {
            ++cnt;
        }
        if (card.getForeignExampleVoice() != null && !card.getForeignExampleVoice().equals("")) {
            ++cnt;
        }

        return cnt + "/4";
    }

    public ObservableList<Cards> getDecksValues() {
        return decksValues;
    }

    public void setDecksValues(ObservableList<Cards> decksValues) {
        this.decksValues = decksValues;
    }
}
